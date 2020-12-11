package com.zr.aservice.rbac.controller;


import com.zr.aservice.rbac.model.UserRoleCheckedIds;
import com.zr.aservice.rbac.service.SysroleService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysRole;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysrole")
public class SysroleController {

  @Resource
  private SysroleService sysroleService;

  @PostMapping(value = "/query")
  public List<SysRole> query(@RequestParam("roleLike") String roleLike) {
    return sysroleService.queryRoles(roleLike);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysRole sysRole) {
    sysroleService.updateRole(sysRole);
    return AjaxResponse.success("更新角色成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysRole sysRole) {
    sysroleService.addRole(sysRole);
    return AjaxResponse.success("新增角色成功！");
  }

  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestParam Integer roleId) {
    sysroleService.deleteRole(roleId);
    return AjaxResponse.success("删除角色成功!");
  }

  @PostMapping(value = "/checkedroles")
  public Map<String,Object> checkedroles(@RequestParam Integer userId) {
    return sysroleService.getRolesAndChecked(userId);
  }


  @PostMapping(value = "/savekeys")
  public AjaxResponse savekeys(@RequestBody UserRoleCheckedIds userRoleCheckedIds) {
    sysroleService.saveCheckedKeys(userRoleCheckedIds.getUserId(),userRoleCheckedIds.getCheckedIds());
    return AjaxResponse.success("保存用户角色成功!");
  }
}
