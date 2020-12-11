package com.zr.aservice.rbac.service;


import com.zr.aservice.rbac.model.SysApiNode;
import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.common.tree.DataTreeUtil;
import com.zr.dao.auto.mapper.SysApiMapper;
import com.zr.dao.auto.mapper.SysRoleApiMapper;
import com.zr.dao.auto.model.SysApi;
import com.zr.dao.auto.model.SysApiExample;
import com.zr.dao.auto.model.SysRoleApiExample;
import com.zr.dao.rbac.mapper.SystemMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysapiService {

  @Resource
  private SystemMapper systemMapper;
  @Resource
  private SysApiMapper sysApiMapper;
  @Resource
  private SysRoleApiMapper sysRoleApiMapper;

  public List<SysApiNode> getApiTreeById(String apiNameLike,
                                         Boolean apiStatus) {
    //查找level=1的菜单节点，即：根节点
    SysApiExample sysApiExample = new SysApiExample();
    sysApiExample.createCriteria().andLevelEqualTo(1);
    List<SysApi> sysApis = sysApiMapper.selectByExample(sysApiExample);

    if (sysApis.size() > 0) {
      Integer rootApiId = sysApis.get(0).getId();

      List<SysApi> sysOrgs
        = systemMapper.selectApiTree(rootApiId, apiNameLike, apiStatus);

      List<SysApiNode> sysOrgNodes = sysOrgs.stream().map(item -> {
        SysApiNode bean = new SysApiNode();
        BeanUtils.copyProperties(item, bean);
        return bean;
      }).collect(Collectors.toList());

      if (!StringUtils.isEmpty(apiNameLike) || apiStatus != null) {
        return sysOrgNodes;//根据菜单名称查询，返回平面列表
      } else {//否则返回树型结构列表
        return DataTreeUtil.buildTree(sysOrgNodes, rootApiId);
      }

    } else {
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "查询参数用户名组织id不能为空");
    }
  }


  public void updateApi(SysApi sysapi){
    if(sysapi.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysApiMapper.updateByPrimaryKeySelective(sysapi);
    }
  }

  @Transactional
  public void addApi(SysApi sysapi){
    setApiIdsAndLevel(sysapi);

    sysapi.setIsLeaf(true); //新增的菜单节点都是子节点，没有下级
    SysApi parent = new SysApi();
    parent.setId(sysapi.getApiPid());
    parent.setIsLeaf(false);//更新父节点为非子节点。
    sysApiMapper.updateByPrimaryKeySelective(parent);

    sysapi.setStatus(false);//设置是否禁用，新增节点默认可用
    sysApiMapper.insert(sysapi);
  }


  @Transactional
  public void deleteApi(SysApi sysApi){
    //查找被删除节点的子节点
    SysApiExample sysApiExample = new SysApiExample();
    sysApiExample.createCriteria().andApiPidsLike("%["+ sysApi.getId() +"]%");
    List<SysApi> myChilds = sysApiMapper.selectByExample(sysApiExample);
    if(myChilds.size() > 0){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "不能删除含有下级菜单的菜单");
    }

    //查找被删除节点的父节点
    sysApiExample = new SysApiExample();
    sysApiExample.createCriteria().andApiPidsLike("%["+ sysApi.getApiPid() +"]%");
    List<SysApi> myFatherChilds = sysApiMapper.selectByExample(sysApiExample);

    //我的父节点只有我这一个子节点，而我还要被删除，更新父节点为叶子节点。
    if(myFatherChilds.size() == 1){
      SysApi parent = new SysApi();
      parent.setId(sysApi.getApiPid());
      parent.setIsLeaf(true);//更新父节点为叶子节点。
      sysApiMapper.updateByPrimaryKeySelective(parent);
    }
    //删除节点
    sysApiMapper.deleteByPrimaryKey(sysApi.getId());
  }

  //设置某子节点的所有祖辈id
  private void setApiIdsAndLevel(SysApi child){
    List<SysApi> allApis = sysApiMapper.selectByExample(null);
    for(SysApi sysApi: allApis){
      //从组织列表中找到自己的直接父亲
      if(sysApi.getId().equals(child.getApiPid())){
        //直接父亲的所有祖辈id + 直接父id = 当前子节点的所有祖辈id
        //爸爸的所有祖辈 + 爸爸 = 孩子的所有祖辈
        child.setApiPids(sysApi.getApiPids() + ",[" + child.getApiPid() + "]" );
        child.setLevel(sysApi.getLevel() + 1);
      }
    }
  }

  public List<Integer> getCheckedKeys(Integer roleId){
    return systemMapper.selectApiCheckedKeys(roleId);
  }
  public List<Integer> getExpandedKeys(){
    return systemMapper.selectApiExpandedKeys();
  }

  @Transactional
  public void saveCheckedKeys(Integer roleId,List<Integer> checkedIds){

    SysRoleApiExample sysRoleApiExample = new SysRoleApiExample();
    sysRoleApiExample.createCriteria().andRoleIdEqualTo(roleId);
    sysRoleApiMapper.deleteByExample(sysRoleApiExample);

    systemMapper.insertRoleApiIds(roleId,checkedIds);
  }
}
