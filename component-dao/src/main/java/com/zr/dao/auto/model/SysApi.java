package com.zr.dao.auto.model;

import java.io.Serializable;

/**
 * sys_api
 * @author
 */
public class SysApi implements Serializable {
    private Integer id;

    /**
     * 接口父ID(即接口分组)
     */
    private Integer apiPid;

    /**
     * 当前接口的所有上级id(即所有上级分组)
     */
    private String apiPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Boolean isLeaf;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 跳转URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 层级，1：接口分组，2：接口
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

    public Integer getApiPid() {
        return apiPid;
    }

    public void setApiPid(Integer apiPid) {
        this.apiPid = apiPid;
    }

    public String getApiPids() {
        return apiPids;
    }

    public void setApiPids(String apiPids) {
        this.apiPids = apiPids;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        SysApi other = (SysApi) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getApiPid() == null ? other.getApiPid() == null : this.getApiPid().equals(other.getApiPid()))
            && (this.getApiPids() == null ? other.getApiPids() == null : this.getApiPids().equals(other.getApiPids()))
            && (this.getIsLeaf() == null ? other.getIsLeaf() == null : this.getIsLeaf().equals(other.getIsLeaf()))
            && (this.getApiName() == null ? other.getApiName() == null : this.getApiName().equals(other.getApiName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getApiPid() == null) ? 0 : getApiPid().hashCode());
        result = prime * result + ((getApiPids() == null) ? 0 : getApiPids().hashCode());
        result = prime * result + ((getIsLeaf() == null) ? 0 : getIsLeaf().hashCode());
        result = prime * result + ((getApiName() == null) ? 0 : getApiName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
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
        sb.append(", apiPid=").append(apiPid);
        sb.append(", apiPids=").append(apiPids);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", apiName=").append(apiName);
        sb.append(", url=").append(url);
        sb.append(", sort=").append(sort);
        sb.append(", level=").append(level);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
