package org.flowable.ui.modeler.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程节点
 *
 * @author lichao
 */
public class ProcNodeInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String procDefId;
    private String actId;
    private String actName;
    private String actType;
    private String logicChildId;
    private String physicsChildId;
    private Date createTime;
    private String isMultiinstance;

    public Integer getId() {
        return id;
    }

    public void setId_(Integer id) {
        this.id = id;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getLogicChildId() {
        return logicChildId;
    }

    public void setLogicChildId(String logicChildId) {
        this.logicChildId = logicChildId;
    }

    public String getPhysicsChildId() {
        return physicsChildId;
    }

    public void setPhysicsChildId(String physicsChildId) {
        this.physicsChildId = physicsChildId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsMultiinstance() {
        return isMultiinstance;
    }

    public void setIsMultiinstance(String isMultiinstance) {
        this.isMultiinstance = isMultiinstance;
    }
}