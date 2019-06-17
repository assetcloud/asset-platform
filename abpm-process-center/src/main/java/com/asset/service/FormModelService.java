package com.asset.service;

import com.asset.entity.FormModelInfo;
import com.asset.rec.*;

import java.util.List;

/**
 * 发生到FormModelController的请求由这个Service执行
 * @author yby
 * @time 190523 0945
 * @version 1.1_190603 0911
 */
public interface FormModelService {

    FormModelInfo createFormModel(FormModelCreateRec rec);

    public int editFormModel(FormModelEditRec rec);

    public int bindFormModel(FormModelBindRec rec);

    List<FormModelInfo> getFormModels(String oappID);

    int saveFormModelAuthority(FormAuthorityRec formModelAuthority);

    int formBindDeploy(FormDeployBindRec formDeployBindRec);

    int setFormGroup(FormGroupRec rec);
}
