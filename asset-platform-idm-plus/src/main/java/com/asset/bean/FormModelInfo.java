package com.asset.bean;

import com.asset.mapper.UuidIdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 与数据库交互的表单模型entity
 * @author hjhu
 * @version 1.0
 */
@Data
@ApiModel(value = "表单资源类", description = "将表单资源存储至权限时需要")
public class FormModelInfo implements Serializable {

    private Long id;

    @ApiModelProperty(value = "表单模型id")
    private String formModelId;  //数据库表中ID是int类型，自增，这里暂时用不到

    @ApiModelProperty(value = "表单名称")
    private String formName;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createdTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "对应父应用")
    private String applicationId;

    @ApiModelProperty(value = "最后更新时间")
    private Timestamp lastUpdatedTime;

    @ApiModelProperty(value = "最后更新者")
    private String lastUpdatedBy;

    @ApiModelProperty(value = "模型版本")
    int version ;

    @ApiModelProperty(value = "表单源数据")
    private String modelJson;
    @ApiModelProperty(value = "表单模型分组id")
    private String groupId;
    @ApiModelProperty(value = "表单模型图标")
    private String iconCls;
    @ApiModelProperty(value = "表单模型状态")
    private int status;
    @ApiModelProperty(value = "表单流程关联id")
    private String procModelId;
    @ApiModelProperty(value = "表单模型分组名称")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFormModelId() {
        return formModelId;
    }

    public void setFormModelId(String formModelId) {
        this.formModelId = formModelId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getModelJson() {
        return modelJson;
    }

    public void setModelJson(String modelJson) {
        this.modelJson = modelJson;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProcModelId() {
        return procModelId;
    }

    public void setProcModelId(String procModelId) {
        this.procModelId = procModelId;
    }

    @Override
    public String toString() {
        return "FormModelInfo{" +
                "formModelId='" + formModelId + '\'' +
                ", formName='" + formName + '\'' +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", applicationId=" + applicationId +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", version=" + version +
                ", modelJson='" + modelJson + '\'' +
                ", groupId='" + groupId + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", status=" + status +
                ", procModelId='" + procModelId + '\'' +
                '}';
    }
}
