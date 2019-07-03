package com.asset.controller;

import com.asset.rec.AsProcModelRec;
import com.asset.service.AsProcModelService;
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

    @RequestMapping(value = "/asproc/model/create" ,method = RequestMethod.POST)
    public String createAsProcModel(@RequestBody AsProcModelRec rec) throws JsonProcessingException {
        int code = procModelService.createFormModel(rec);
        return JsonUtils.getCodeJson(code);
    }
}
