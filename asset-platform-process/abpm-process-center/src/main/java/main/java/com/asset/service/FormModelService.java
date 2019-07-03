package com.asset.service;

import com.asset.entity.AsFormModel;
import com.asset.rec.*;

/**
 * 发生到FormModelController的请求由这个Service执行
 * @author yby
 * @time 190523 0945
 * @version 1.1_190603 0911
 */
public interface FormModelService {

    AsFormModel createFormModel(FormModelCreateRec rec);

    public int editFormModel(FormModelEditRec rec);

    public int bindFormModel(FormModelBindRec rec);

//    int saveFormModelAuthority(FormAuthorityRec formModelAuthority);

    int setFormGroup(FormGroupRec rec);

    String getProcModelID(String formModelID);
}
