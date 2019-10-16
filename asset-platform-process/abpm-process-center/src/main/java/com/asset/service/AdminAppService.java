package com.asset.service;

import com.asset.entity.ApplicationDO;
import com.asset.javabean.AdminAppInfoVO;
import com.asset.javabean.AdminFormModelVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.flowable.spring.boot.app.App;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YBY
 */
@Service
public class AdminAppService {

    @Autowired
    ApplicationService applicationService;
    @Autowired
    AdminFormModelService adminFormModelService;

    /**
     * 先获取对应分页数的应用条目，然后去找相应应用下的表单模型信息（这里就是前面获取表单模型列表相同的操作），然后在组装成一个整体、返回
     * @return
     */
    public List<AdminAppInfoVO> getAppInfos() {
        //先获取应用列表
        List<ApplicationDO> applicationDOs = applicationService.getAppList();
        List<AdminAppInfoVO> VOs= new ArrayList<>();

        for(int i=0;i<applicationDOs.size();i++)
        {
            AdminAppInfoVO vo = new AdminAppInfoVO();
            BeanUtils.copyProperties(applicationDOs.get(i),vo);

            List<AdminFormModelVO> formModels = adminFormModelService.getFormModels(applicationDOs.get(i).getId());
            vo.setAdminFormModelVOs(formModels);

            VOs.add(vo);
        }

        return VOs;
    }
}
