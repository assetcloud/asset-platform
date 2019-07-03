package com.asset.controller;

import com.asset.javabean.RespBean;
import com.asset.rec.ProcModelRec;
import com.asset.service.AsProcModelService;
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

    /**
     * 保存功能性节点信息，这里不管是创建还是修改都用同一个接口
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/asproc/model/create" ,method = RequestMethod.POST)
    public RespBean editAsProcModel(@RequestBody ProcModelRec rec) throws JsonProcessingException {
        procModelService.createFormModel(rec);
        return RespBean.ok("");
    }
}
