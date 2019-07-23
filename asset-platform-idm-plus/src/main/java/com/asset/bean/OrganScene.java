package com.asset.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import java.util.List;

@ApiModel(value = "组织场景关联表")
public class OrganScene {

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    private String nodeId;
    private String parentId;
    private String sceneId;
    private Integer status;
    private String unitName;
    private List<OrganScene> children;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public List<OrganScene> getChildren() {
        return children;
    }

    public void setChildren(List<OrganScene> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrganScene{" +
                "id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", sceneId='" + sceneId + '\'' +
                ", status=" + status +
                ", unitName='" + unitName + '\'' +
                ", children=" + children +
                '}';
    }
}
