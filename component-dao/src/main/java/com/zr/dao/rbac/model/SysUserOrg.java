package com.zr.dao.rbac.model;


import com.zr.dao.auto.model.SysUser;

public class SysUserOrg extends SysUser {

  private String orgName;

  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
