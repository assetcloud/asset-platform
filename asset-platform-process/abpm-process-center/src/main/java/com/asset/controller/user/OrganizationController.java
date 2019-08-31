package com.asset.controller.user;

import com.asset.base.BaseController;
import com.asset.service.OrganizationService;
import com.asset.service.IProcRepositoryService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjhu
 */
@Controller
@Api(tags = "终端：组织架构管理")
public class OrganizationController extends BaseController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    IProcRepositoryService IProcRepositoryService;

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
