package com.zr.aservice.rbac.model;


import com.zr.common.tree.DataTree;
import com.zr.dao.auto.model.SysApi;

import java.util.List;

public class SysApiNode extends SysApi implements DataTree<SysApiNode> {

    private List<SysApiNode> children;

    @Override
    public Integer getParentId() {
        return super.getApiPid();
    }

    @Override
    public void setChildren(List<SysApiNode> children) {
        this.children = children;
    }

    @Override
    public List<SysApiNode> getChildren() {
        return this.children;
    }
}
