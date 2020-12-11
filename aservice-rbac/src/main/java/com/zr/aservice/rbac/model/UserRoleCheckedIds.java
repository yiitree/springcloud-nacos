package com.zr.aservice.rbac.model;

import java.util.List;

public class UserRoleCheckedIds {

  private Integer userId;

  private List<Integer> checkedIds;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public List<Integer> getCheckedIds() {
    return checkedIds;
  }

  public void setCheckedIds(List<Integer> checkedIds) {
    this.checkedIds = checkedIds;
  }
}
