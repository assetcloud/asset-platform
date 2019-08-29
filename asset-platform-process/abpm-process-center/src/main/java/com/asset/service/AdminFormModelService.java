package com.asset.service;

import com.asset.entity.FormModelDO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.utils.Constants;
import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制端表单模型的服务层
 * @author YBY
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminFormModelService {

    @Autowired
    FormModelService formModelService;


    public Page<AdminFormModelVO> getFormModels() {
        List<FormModelDO> DOList = formModelService.getFormModels();
        Page<AdminFormModelVO> VOList = new Page<>();

        VOList = (Page<AdminFormModelVO>) transDOtoVO(DOList,VOList);

        return VOList;
    }


    public List<AdminFormModelVO> getFormModels(String appId) {
        //这里是不加筛选，全部取出来
        List<FormModelDO> DOList = formModelService.listAllFormModelDO(appId, Constants.GROUP_ALL);
        List<AdminFormModelVO> VOList = new ArrayList<>();

        VOList = transDOtoVO(DOList,VOList);

        return VOList;
    }

    /**
     * 控制台端口获取应用下所有表单模型内容
     * @param appId
     * @return
     */
    public List<AdminFormModelVO> getApplicationFormModels(String appId) {
        List<FormModelDO> DOList = formModelService.getAdminApplicationFormModel(appId);
        if(DOList  == null)
            return null;
        List<AdminFormModelVO> VOList = new ArrayList<>();

        VOList = transDOtoVO(DOList,VOList);

        return VOList;
    }

    private List<AdminFormModelVO> transDOtoVO(List<FormModelDO> DOList, List<AdminFormModelVO> VOList) {
        for(int i=0;i<DOList.size();i++)
        {
            AdminFormModelVO vo = new AdminFormModelVO();
            BeanUtils.copyProperties(DOList.get(i),vo);

            vo.setCreatedTime(DOList.get(i).getCreatedTime().getTime());

            VOList.add(vo);
        }
        return VOList;
    }


}
