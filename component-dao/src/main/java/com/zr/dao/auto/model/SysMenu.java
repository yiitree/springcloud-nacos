package com.zr.dao.auto.model;

import java.io.Serializable;

/**
 * sys_menu
 * @author
 */
public class SysMenu implements Serializable {
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer menuPid;

    /**
     * 当前菜单所有父菜单
     */
    private String menuPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Boolean isLeaf;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 跳转URL
     */
    private String url;

    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 是否禁用，0:启用(否）,1:禁用(是)
     */
    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Integer menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuPids() {
        return menuPids;
    }

    public void setMenuPids(String menuPids) {
        this.menuPids = menuPids;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysMenu other = (SysMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenuPid() == null ? other.getMenuPid() == null : this.getMenuPid().equals(other.getMenuPid()))
            && (this.getMenuPids() == null ? other.getMenuPids() == null : this.getMenuPids().equals(other.getMenuPids()))
            && (this.getIsLeaf() == null ? other.getIsLeaf() == null : this.getIsLeaf().equals(other.getIsLeaf()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenuPid() == null) ? 0 : getMenuPid().hashCode());
        result = prime * result + ((getMenuPids() == null) ? 0 : getMenuPids().hashCode());
        result = prime * result + ((getIsLeaf() == null) ? 0 : getIsLeaf().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuPid=").append(menuPid);
        sb.append(", menuPids=").append(menuPids);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", menuName=").append(menuName);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", sort=").append(sort);
        sb.append(", level=").append(level);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
