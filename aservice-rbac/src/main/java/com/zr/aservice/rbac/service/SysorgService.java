package com.zr.aservice.rbac.service;


import com.zr.aservice.rbac.model.SysOrgNode;
import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.common.tree.DataTreeUtil;
import com.zr.dao.auto.mapper.SysOrgMapper;
import com.zr.dao.auto.model.SysOrg;
import com.zr.dao.auto.model.SysOrgExample;
import com.zr.dao.rbac.mapper.SystemMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysorgService {

  @Resource
  private SystemMapper systemMapper;
  @Resource
  private SysOrgMapper sysOrgMapper;

  public List<SysOrgNode> getOrgTreeById(Integer rootOrgId,
                                         String orgNameLike,
                                         Boolean orgStatus) {
    if (rootOrgId != null) {
      List<SysOrg> sysOrgs
        = systemMapper.selectOrgTree(rootOrgId, orgNameLike, orgStatus);

      List<SysOrgNode> sysOrgNodes = sysOrgs.stream().map(item -> {
        SysOrgNode bean = new SysOrgNode();
        BeanUtils.copyProperties(item, bean);
        return bean;
      }).collect(Collectors.toList());

      if (!StringUtils.isEmpty(orgNameLike)
         || orgStatus != null) {
        return sysOrgNodes;//根据组织名称查询，返回平面列表
      } else {//否则返回树型结构列表
        return DataTreeUtil.buildTree(sysOrgNodes, rootOrgId);
      }

    } else {
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "查询参数用户名组织id不能为空");
    }
  }


  public void updateOrg(SysOrg sysorg){
    if(sysorg.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysOrgMapper.updateByPrimaryKeySelective(sysorg);
    }
  }

  @Transactional
  public void addOrg(SysOrg sysorg){
    setOrgIdsAndLevel(sysorg);

    sysorg.setIsLeaf(true); //新增的组织节点都是子节点，没有下级
    SysOrg parent = new SysOrg();
    parent.setId(sysorg.getOrgPid());
    parent.setIsLeaf(false);//更新父节点为非子节点。
    sysOrgMapper.updateByPrimaryKeySelective(parent);

    sysorg.setStatus(false);//设置是否禁用，新增节点默认可用
    sysOrgMapper.insert(sysorg);
  }


  @Transactional
  public void deleteOrg(SysOrg sysOrg){
    //查找被删除节点的子节点
    SysOrgExample sysOrgExample = new SysOrgExample();
    sysOrgExample.createCriteria().andOrgPidsLike("%["+ sysOrg.getId() +"]%");
    List<SysOrg> myChilds = sysOrgMapper.selectByExample(sysOrgExample);
    if(myChilds.size() > 0){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "不能删除有下级组织的组织机构");
    }

    //查找被删除节点的父节点
    sysOrgExample = new SysOrgExample();
    sysOrgExample.createCriteria().andOrgPidsLike("%["+ sysOrg.getOrgPid() +"]%");
    List<SysOrg> myFatherChilds = sysOrgMapper.selectByExample(sysOrgExample);

    //我的父节点只有我这一个子节点，而我还要被删除，更新父节点为叶子节点。
    if(myFatherChilds.size() == 1){
      SysOrg parent = new SysOrg();
      parent.setId(sysOrg.getOrgPid());
      parent.setIsLeaf(true);//更新父节点为叶子节点。
      sysOrgMapper.updateByPrimaryKeySelective(parent);
    }
    //删除节点
    sysOrgMapper.deleteByPrimaryKey(sysOrg.getId());
  }

  //设置某子节点的所有祖辈id
  private void setOrgIdsAndLevel(SysOrg child){
    List<SysOrg> allOrgs = sysOrgMapper.selectByExample(null);
    for(SysOrg sysOrg: allOrgs){
      //从组织列表中找到自己的直接父亲
      if(sysOrg.getId().equals(child.getOrgPid())){
        //直接父亲的所有祖辈id + 直接父id = 当前子节点的所有祖辈id
        //爸爸的所有祖辈 + 爸爸 = 孩子的所有祖辈
        child.setOrgPids(sysOrg.getOrgPids() + ",[" + child.getOrgPid() + "]" );
        child.setLevel(sysOrg.getLevel() + 1);
      }
    }
  }
}
