package com.asset.bean;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(value = "表单分组类", description = "表单资源以分组形式展现时需要")
public class FormGroup {
    private String groupId;
    private String groupName;
    private List<Menu> children;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
