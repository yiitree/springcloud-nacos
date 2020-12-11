package com.zr.aservice.rbac.model;

import com.zr.common.tree.DataTree;
import com.zr.dao.auto.model.SysMenu;

import java.util.List;

public class SysMenuNode extends SysMenu implements DataTree<SysMenuNode> {

    private List<SysMenuNode> children;

    private String path;
    private String name;

    public String getPath() {
      return this.getUrl();
    }

    public String getName() {
      return this.getMenuName();
    }

    @Override
    public Integer getParentId() {
        return super.getMenuPid();
    }

    @Override
    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }

    @Override
    public List<SysMenuNode> getChildren() {
        return this.children;
    }
}
