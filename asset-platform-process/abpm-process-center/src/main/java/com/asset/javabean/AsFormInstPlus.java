package com.asset.javabean;

import com.asset.entity.AsFormInst;
import com.asset.utils.Constants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class AsFormInstPlus extends AsFormInst {
    int nodeType;

    public AsFormInstPlus(AsFormInst asFormInst) {
        if (!StringUtils.isEmpty(asFormInst.getId()))
            setId(asFormInst.getId());
        if (!StringUtils.isEmpty(asFormInst.getFormModelId()))
            setFormModelId(asFormInst.getFormModelId());
        if (!StringUtils.isEmpty(asFormInst.getProcInstId()))
            setProcInstId(asFormInst.getProcInstId());
        if (!StringUtils.isEmpty(asFormInst.getExecutionId()))
            setExecutionId(asFormInst.getExecutionId());
        if (!StringUtils.isEmpty(asFormInst.getTaskId()))
            setTaskId(asFormInst.getTaskId());
        if (asFormInst.getCreatedTime()!=null)
            setCreatedTime(asFormInst.getCreatedTime());
        if (!StringUtils.isEmpty(asFormInst.getCreatedBy()))
            setCreatedBy(asFormInst.getCreatedBy());
        if (!StringUtils.isEmpty(asFormInst.getFormInstJson()))
            setFormInstJson(asFormInst.getFormInstJson());
        if (!StringUtils.isEmpty(asFormInst.getFormValue()))
            setFormValue(asFormInst.getFormValue());
        if (asFormInst.getStatus()!= Constants.EMPTY);
            setStatus(asFormInst.getStatus());
    }
}
