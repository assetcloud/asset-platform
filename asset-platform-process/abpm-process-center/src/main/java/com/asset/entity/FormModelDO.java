package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import com.asset.dto.FormModelEditDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FormModelDO implements Serializable {
    private String id;
    private String formName;
    private Date createdTime;
    private String createdBy;
    private Date lastUpdatedTime;
    private String lastUpdatedBy;
    private Integer version;
    private Integer groupId;
    private String iconCls;
    private String procModelId;
    private String modelSheet;
    private String sceneId;
    private String appId;
    private Integer isBinded = 0;  //表单模型是否绑定流程模型，0否，1是
    private Integer isBindAuthority = 0;   //表单项权限数据是否添加，0否，1是
    private Integer isAddNodeInfo = 0;   //是否正确增加节点信息，0否，1是
    private Integer isAddSeqCondition = 0;   //是否增加seqCondition，0否，1是

    public FormModelDO() {
    }

    public FormModelDO(String id, String formName, String createdBy, Integer version, Integer groupId, Integer isBinded, String procModelId, String modelSheet) {
        this.id = id;
        this.formName = formName;
        this.createdBy = createdBy;
        this.version = version;
        this.groupId = groupId;
        this.isBinded = isBinded;
        this.procModelId = procModelId;
        this.modelSheet = modelSheet;
    }

    public FormModelDO(String formName,
                       String createdBy,
                       String iconCls,
                       Integer isBinded,
                       String modelSheet) {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
        this.formName = formName;
        this.createdBy = createdBy;
        this.iconCls = iconCls;
        this.isBinded = isBinded;
        this.modelSheet = modelSheet;
    }


    public FormModelDO(FormModelEditDTO rec) {
        this.id = rec.getForm_model_id();
        this.formName = rec.getForm_name();
        this.iconCls = rec.getIcon_cls();
        String jsonString = JSONObject.toJSONString(rec.getForm_sheet());
        this.modelSheet = jsonString;
        this.groupId = rec.getGroup_id();
    }

    public FormModelDO(String id, String procModelId) {
        this.id = id;
        this.procModelId = procModelId;
    }

    public FormModelDO(String id, Integer groupId) {
        this.id = id;
        this.groupId = groupId;
    }

    private FormModelDO(Builder builder) {
        setId(builder.id);
        setFormName(builder.formName);
        setCreatedTime(builder.createdTime);
        setCreatedBy(builder.createdBy);
        setLastUpdatedTime(builder.lastUpdatedTime);
        setLastUpdatedBy(builder.lastUpdatedBy);
        setVersion(builder.version);
        setGroupId(builder.groupId);
        setIconCls(builder.iconCls);
        setProcModelId(builder.procModelId);
        setModelSheet(builder.modelSheet);
        setSceneId(builder.sceneId);
        setAppId(builder.appId);
        setIsBinded(builder.isBinded);
        setIsBindAuthority(builder.isBindAuthority);
        setIsAddNodeInfo(builder.isAddNodeInfo);
        setIsAddSeqCondition(builder.isAddSeqCondition);
    }


    public static final class Builder {
        private String id;
        private String sceneId;
        private String formName;
        private Date createdTime;
        private String createdBy;
        private Date lastUpdatedTime;
        private String lastUpdatedBy;
        private Integer version;
        private Integer groupId;
        private String iconCls;
        private String procModelId;
        private String modelSheet;
        private String appId;
        private Integer isBinded = 0 ;
        private Integer isBindAuthority =0;
        private Integer isAddNodeInfo =0;
        private Integer isAddSeqCondition =0;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder sceneId(String val) {
            sceneId = val;
            return this;
        }

        public Builder formName(String val) {
            formName = val;
            return this;
        }

        public Builder createdTime(Date val) {
            createdTime = val;
            return this;
        }

        public Builder createdBy(String val) {
            createdBy = val;
            return this;
        }

        public Builder lastUpdatedTime(Date val) {
            lastUpdatedTime = val;
            return this;
        }

        public Builder lastUpdatedBy(String val) {
            lastUpdatedBy = val;
            return this;
        }

        public Builder version(Integer val) {
            version = val;
            return this;
        }

        public Builder groupId(Integer val) {
            groupId = val;
            return this;
        }

        public Builder iconCls(String val) {
            iconCls = val;
            return this;
        }

        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder modelSheet(String val) {
            modelSheet = val;
            return this;
        }

        public Builder appId(String val) {
            appId = val;
            return this;
        }

        public Builder isBinded(Integer val) {
            isBinded = val;
            return this;
        }

        public Builder isBindAuthority(Integer val) {
            isBindAuthority = val;
            return this;
        }

        public Builder isAddNodeInfo(Integer val) {
            isAddNodeInfo = val;
            return this;
        }

        public Builder isAddSeqCondition(Integer val) {
            isAddSeqCondition = val;
            return this;
        }

        public FormModelDO build() {
            IDGenerator generator = new UuidIdGenerator();
            id = generator.generateID();
            return new FormModelDO(this);
        }
    }
}
