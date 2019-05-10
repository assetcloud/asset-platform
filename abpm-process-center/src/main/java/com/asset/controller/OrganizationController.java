package com.asset.controller;

import com.asset.base.BaseController;
import com.asset.service.OrganizationService;
import com.asset.service.ProcRepositoryService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import com.asset.utils.Parametermap;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hjhu
 */
@Controller
public class OrganizationController extends BaseController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    ProcRepositoryService procRepositoryService;

    /**
     * 流程定义页面
     * @return
     */
    @RequestMapping("/organizationList")
    public String procDefIndex() {
        return "pages/identity/organization_list";
    }

    /**
     * 查询流程定义分页数据
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    @RequestMapping("/queryOrganization")
    @ResponseBody
    public PageGrids queryProcDef(@RequestParam("page") Integer pageNum,
                                  @RequestParam("rows") Integer pageSize) {
        if(pageNum==null ||pageNum<=0){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = organizationService.getOrganizationList(pageNum,pageSize);
        return pageGrids;
    }
}
