package com.asset.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class Menu {

    @ApiModelProperty(value = "菜单id")
    private Long id;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    @ApiModelProperty(value = "菜单代号" ,notes = "若为app或form则直接访问app/{code}或form/{code}，若为func则访问对应的path即可")
    private String code;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单图标")
    private String iconCls;
    @ApiModelProperty(value = "访问路径")
    private String path;
    @ApiModelProperty(value = "菜单级别")
    private Integer level;
    @ApiModelProperty(value = "菜单排序")
    private Integer sort;
    @ApiModelProperty(value = "操作类别，分别为1，2，3")
    private Integer category;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "1:被删除;0:未删除")
    private Integer isDeleted;
    @ApiModelProperty(value = "添加时间")
    private Date addTime;
    @ApiModelProperty(value = "删除时间")
    private Date removeTime;
    @ApiModelProperty(value = "表单分组id")
    private String groupId;
    @ApiModelProperty(value = "表单分组名称")
    private String groupName;


    private List<FormGroup> group;
    private List<Menu> children;

    public Menu(String code, String name, String iconCls, String path, Integer level, Integer sort, Integer category) {
        this.code = code;
        this.name = name;
        this.iconCls = iconCls;
        this.path = path;
        this.level = level;
        this.sort = sort;
        this.category = category;
    }

    public Menu() {
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public List<FormGroup> getGroup() {
        return group;
    }

    public void setGroup(List<FormGroup> group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

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

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", path='" + path + '\'' +
                ", level=" + level +
                ", sort=" + sort +
                ", category=" + category +
                ", remark='" + remark + '\'' +
                ", isDeleted=" + isDeleted +
                ", addTime=" + addTime +
                ", removeTime=" + removeTime +
                ", children=" + children.toString() +
                '}';
    }
}
