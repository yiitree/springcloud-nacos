package com.zr.common.tree;

import java.util.List;

public interface DataTree<T> {
	//维护树形关系：元素一
    public Integer getId();
    //维护树形关系：元素二
    public Integer getParentId();
    //子节点数组
    public void setChildren(List<T> children);

    public List<T> getChildren();
}
