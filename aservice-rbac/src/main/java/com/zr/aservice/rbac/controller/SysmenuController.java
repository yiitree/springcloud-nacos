package com.zr.aservice.rbac.controller;


import com.zr.aservice.rbac.model.RoleCheckedIds;
import com.zr.aservice.rbac.model.SysMenuNode;
import com.zr.aservice.rbac.service.SysmenuService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysMenu;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysmenu")
public class SysmenuController {

  @Resource
  private SysmenuService sysmenuService;

  @PostMapping(value = "/tree")
  public List<SysMenuNode> tree(@RequestParam("menuNameLike") String menuNameLike,
                                @RequestParam("menuStatus") Boolean menuStatus) {

    return sysmenuService.getMenuTree(menuNameLike, menuStatus);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysMenu sysMenu) {
    sysmenuService.updateMenu(sysMenu);
    return AjaxResponse.success("更新菜单项成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysMenu sysMenu) {
    sysmenuService.addMenu(sysMenu);
    return AjaxResponse.success("新增菜单项成功！");
  }


  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestBody SysMenu sysMenu) {
    sysmenuService.deleteMenu(sysMenu);
    return AjaxResponse.success("删除菜单项成功!");
  }

  @PostMapping(value = "/checkedtree")
  public Map<String,Object> checkedtree(@RequestParam Integer roleId) {
    Map<String,Object> ret = new HashMap<>();
    ret.put("tree",sysmenuService.getMenuTree("",null));
    ret.put("expandedKeys",sysmenuService.getExpandedKeys());
    ret.put("checkedKeys",sysmenuService.getCheckedKeys(roleId));
    return ret;
  }

  @PostMapping(value = "/savekeys")
  public AjaxResponse savekeys(@RequestBody RoleCheckedIds roleCheckedIds) {
    sysmenuService.saveCheckedKeys(roleCheckedIds.getRoleId(),roleCheckedIds.getCheckedIds());
    return AjaxResponse.success("保存菜单权限成功!");
  }

  @PostMapping(value = "/tree/user")
  public List<SysMenuNode> usertree(@RequestParam("username") String username) {
    return sysmenuService.getMenuTreeByUsername(username);
  }
}
