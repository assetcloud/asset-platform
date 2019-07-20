package com.asset.javabean;

import com.asset.entity.FormInst;
import com.asset.utils.Constants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class FormInstPlus extends FormInst {
    int nodeType;

    public FormInstPlus(FormInst formInst) {
        if (!StringUtils.isEmpty(formInst.getId()))
            setId(formInst.getId());
        if (!StringUtils.isEmpty(formInst.getFormModelId()))
            setFormModelId(formInst.getFormModelId());
        if (!StringUtils.isEmpty(formInst.getProcInstId()))
            setProcInstId(formInst.getProcInstId());
        if (!StringUtils.isEmpty(formInst.getExecutionId()))
            setExecutionId(formInst.getExecutionId());
        if (!StringUtils.isEmpty(formInst.getTaskId()))
            setTaskId(formInst.getTaskId());
        if (formInst.getCreatedTime()!=null)
            setCreatedTime(formInst.getCreatedTime());
        if (!StringUtils.isEmpty(formInst.getCreatedBy()))
            setCreatedBy(formInst.getCreatedBy());
        if (!StringUtils.isEmpty(formInst.getFormInstJson()))
            setFormInstJson(formInst.getFormInstJson());
        if (!StringUtils.isEmpty(formInst.getFormValue()))
            setFormValue(formInst.getFormValue());
        if (formInst.getStatus()!= Constants.EMPTY);
            setStatus(formInst.getStatus());
    }
}
