package com.zr.aservice.rbac.service;


import com.zr.aservice.rbac.model.SysMenuNode;
import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.common.tree.DataTreeUtil;
import com.zr.dao.auto.mapper.SysMenuMapper;
import com.zr.dao.auto.mapper.SysRoleMenuMapper;
import com.zr.dao.auto.model.SysMenu;
import com.zr.dao.auto.model.SysMenuExample;
import com.zr.dao.auto.model.SysRoleMenuExample;
import com.zr.dao.rbac.mapper.SystemMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysmenuService {

  @Resource
  private SystemMapper systemMapper;
  @Resource
  private SysMenuMapper sysMenuMapper;
  @Resource
  private SysRoleMenuMapper sysRoleMenuMapper;

  public List<SysMenuNode> getMenuTree(String menuNameLike,
                                       Boolean menuStatus) {
    //查找level=1的菜单节点，即：根节点
    SysMenuExample sysMenuExample = new SysMenuExample();
    sysMenuExample.createCriteria().andLevelEqualTo(1);
    List<SysMenu> sysMenus = sysMenuMapper.selectByExample(sysMenuExample);

    if (sysMenus.size() > 0) {
      Integer rootMenuId = sysMenus.get(0).getId();

      List<SysMenu> sysOrgs
        = systemMapper.selectMenuTree(rootMenuId, menuNameLike, menuStatus);

      List<SysMenuNode> sysOrgNodes = sysOrgs.stream().map(item -> {
        SysMenuNode bean = new SysMenuNode();
        BeanUtils.copyProperties(item, bean);
        return bean;
      }).collect(Collectors.toList());

      if (!StringUtils.isEmpty(menuNameLike) || menuStatus != null) {
        return sysOrgNodes;//根据菜单名称查询，返回平面列表
      } else {//否则返回树型结构列表
        return DataTreeUtil.buildTree(sysOrgNodes, rootMenuId);
      }

    } else {
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "查询参数用户名组织id不能为空");
    }
  }


  public void updateMenu(SysMenu sysmenu){
    if(sysmenu.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysMenuMapper.updateByPrimaryKeySelective(sysmenu);
    }
  }

  @Transactional
  public void addMenu(SysMenu sysmenu){
    setMenuIdsAndLevel(sysmenu);

    sysmenu.setIsLeaf(true); //新增的菜单节点都是子节点，没有下级
    SysMenu parent = new SysMenu();
    parent.setId(sysmenu.getMenuPid());
    parent.setIsLeaf(false);//更新父节点为非子节点。
    sysMenuMapper.updateByPrimaryKeySelective(parent);

    sysmenu.setStatus(false);//设置是否禁用，新增节点默认可用
    sysMenuMapper.insert(sysmenu);
  }


  @Transactional
  public void deleteMenu(SysMenu sysMenu){
    //查找被删除节点的子节点
    SysMenuExample sysMenuExample = new SysMenuExample();
    sysMenuExample.createCriteria().andMenuPidsLike("%["+ sysMenu.getId() +"]%");
    List<SysMenu> myChilds = sysMenuMapper.selectByExample(sysMenuExample);
    if(myChilds.size() > 0){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "不能删除含有下级菜单的菜单");
    }

    //查找被删除节点的父节点
    sysMenuExample = new SysMenuExample();
    sysMenuExample.createCriteria().andMenuPidsLike("%["+ sysMenu.getMenuPid() +"]%");
    List<SysMenu> myFatherChilds = sysMenuMapper.selectByExample(sysMenuExample);

    //我的父节点只有我这一个子节点，而我还要被删除，更新父节点为叶子节点。
    if(myFatherChilds.size() == 1){
      SysMenu parent = new SysMenu();
      parent.setId(sysMenu.getMenuPid());
      parent.setIsLeaf(true);//更新父节点为叶子节点。
      sysMenuMapper.updateByPrimaryKeySelective(parent);
    }
    //删除节点
    sysMenuMapper.deleteByPrimaryKey(sysMenu.getId());
  }

  //设置某子节点的所有祖辈id
  private void setMenuIdsAndLevel(SysMenu child){
    List<SysMenu> allMenus = sysMenuMapper.selectByExample(null);
    for(SysMenu sysMenu: allMenus){
      //从组织列表中找到自己的直接父亲
      if(sysMenu.getId().equals(child.getMenuPid())){
        //直接父亲的所有祖辈id + 直接父id = 当前子节点的所有祖辈id
        //爸爸的所有祖辈 + 爸爸 = 孩子的所有祖辈
        child.setMenuPids(sysMenu.getMenuPids() + ",[" + child.getMenuPid() + "]" );
        child.setLevel(sysMenu.getLevel() + 1);
      }
    }
  }

  public List<Integer> getCheckedKeys(Integer roleId){
    return systemMapper.selectMenuCheckedKeys(roleId);
  }
  public List<Integer> getExpandedKeys(){
    return systemMapper.selectMenuExpandedKeys();
  }
  @Transactional
  public void saveCheckedKeys(Integer roleId,List<Integer> checkedIds){
    SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
    sysRoleMenuExample.createCriteria().andRoleIdEqualTo(roleId);
    sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);

    systemMapper.insertRoleMenuIds(roleId,checkedIds);
  }


  public List<SysMenuNode> getMenuTreeByUsername(String username) {
    List<SysMenu> sysMenus = systemMapper.selectMenuByUsername(username);

    if (sysMenus.size() > 0) {
      Integer rootMenuId = sysMenus.get(0).getId();

      List<SysMenuNode> sysOrgNodes =
      sysMenus.stream().map(item -> {
        SysMenuNode bean = new SysMenuNode();
        BeanUtils.copyProperties(item, bean);
        return bean;
      }).collect(Collectors.toList());
      return DataTreeUtil.buildTreeWithoutRoot(sysOrgNodes, rootMenuId);
    }
    return new ArrayList<>();
  }
}
