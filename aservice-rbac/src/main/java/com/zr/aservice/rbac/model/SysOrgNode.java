package com.zr.aservice.rbac.model;


import com.zr.common.tree.DataTree;
import com.zr.dao.auto.model.SysOrg;

import java.util.List;

public class SysOrgNode extends SysOrg implements DataTree<SysOrgNode> {

    private List<SysOrgNode> children;

    @Override
    public Integer getParentId() {
        return super.getOrgPid();
    }

    @Override
    public void setChildren(List<SysOrgNode> children) {
        this.children = children;
    }

    @Override
    public List<SysOrgNode> getChildren() {
        return this.children;
    }
}
