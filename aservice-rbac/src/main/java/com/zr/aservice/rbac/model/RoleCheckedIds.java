package com.zr.aservice.rbac.model;

import java.util.List;

public class RoleCheckedIds {

  private Integer roleId;

  private List<Integer> checkedIds;

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public List<Integer> getCheckedIds() {
    return checkedIds;
  }

  public void setCheckedIds(List<Integer> checkedIds) {
    this.checkedIds = checkedIds;
  }
}
