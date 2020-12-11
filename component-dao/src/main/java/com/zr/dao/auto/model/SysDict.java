package com.zr.dao.auto.model;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_dict
 * @author
 */
public class SysDict implements Serializable {
    private Integer id;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组编码
     */
    private String groupCode;

    /**
     * 字典项名称
     */
    private String itemName;

    /**
     * 字典项Value
     */
    private String itemValue;

    /**
     * 字典项描述
     */
    private String itemDesc;

    /**
     * 字典项创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        SysDict other = (SysDict) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getGroupCode() == null ? other.getGroupCode() == null : this.getGroupCode().equals(other.getGroupCode()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getItemValue() == null ? other.getItemValue() == null : this.getItemValue().equals(other.getItemValue()))
            && (this.getItemDesc() == null ? other.getItemDesc() == null : this.getItemDesc().equals(other.getItemDesc()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getGroupCode() == null) ? 0 : getGroupCode().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemValue() == null) ? 0 : getItemValue().hashCode());
        result = prime * result + ((getItemDesc() == null) ? 0 : getItemDesc().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupName=").append(groupName);
        sb.append(", groupCode=").append(groupCode);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemValue=").append(itemValue);
        sb.append(", itemDesc=").append(itemDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
