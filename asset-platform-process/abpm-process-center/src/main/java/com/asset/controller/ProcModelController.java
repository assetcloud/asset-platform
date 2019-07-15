package com.asset.controller;

import com.asset.dao.FormAuthorityMapper;
import com.asset.entity.ActAuthority;
import com.asset.javabean.RespBean;
import com.asset.rec.AuthorityItem;
import com.asset.rec.AuthorityRec;
import com.asset.rec.ProcModelRec;
import com.asset.service.AsProcModelService;
import com.asset.utils.Constants;
import com.asset.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcModelController {

    @Autowired
    AsProcModelService procModelService;
    @Autowired
    FormAuthorityMapper authorityMapper;

    /**
     * 保存功能性节点信息，这里不管是创建还是修改都用同一个接口
     *
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/asproc/model/create", method = RequestMethod.POST)
    public RespBean editAsProcModel(@RequestBody ProcModelRec rec) {
        procModelService.createFormModel(rec);
        return RespBean.ok("");
    }

    /**
     * 在流程设计页面，对表单项权限信息进行存储
     */
    @RequestMapping(value = "/asproc/authority/save", method = RequestMethod.POST)
    public RespBean saveAuthority(@RequestBody AuthorityRec authorityRec) {
        if (authorityRec.getProc_model_id().isEmpty())
            return RespBean.error("流程模型数据缺失！");

        for (int i = 0; i < authorityRec.getData().size(); i++) {
            AuthorityItem cur = authorityRec.getData().get(i);

            if (cur.getAct_id().isEmpty() ||
                    cur.getForm_item_key().isEmpty() ||
                    cur.getAuthority() == null)
                return RespBean.error("权限数据缺失！");

            ActAuthority actAuthority = new ActAuthority(authorityRec.getProc_model_id(),
                    cur.getAct_id(),
                    cur.getForm_item_key(),
                    cur.getAuthority());
            int a = authorityMapper.insert(actAuthority);
            if (a == Constants.DATABASE_FAILED)
                return RespBean.error("插入权限数据失败！");
        }

        return RespBean.ok("");
    }
}
