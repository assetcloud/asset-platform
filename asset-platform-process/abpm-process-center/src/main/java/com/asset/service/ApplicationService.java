package com.asset.service;


import com.asset.dao.AppFormBindMapper;
import com.asset.dao.ApplicationMapper;
import com.asset.dao.FormModelMapper;
import com.asset.entity.Application;
import com.asset.entity.FormModel;
import com.asset.javabean.UuidIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
public class ApplicationService {

    final static Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    AppFormBindMapper appFormBindMapper;
    @Autowired
    FormModelMapper formModelMapper;

    UuidIdGenerator idGenerator = new UuidIdGenerator();

    public int addApplication(Application record){
        if (record.getId() == null){
            record.setId(idGenerator.generateId());
        }
        record.setCreatedTime(new Date());
        record.setStatus(1);
        record.setIsPublished(0);
        return applicationMapper.insert(record);
    }

    public List<Application> getAppList(){
        return applicationMapper.getAppList();
    }

    public int updateApplication(Application record){
        return applicationMapper.updateByPrimaryKey(record);
    }

    public Application getById(String id){
        Application application = applicationMapper.selectByPrimaryKey(id);
        return application;
    }

    /**
     * 获取一个 App应用下 所有表单模型数据
     * @return
     */
    public List<FormModel> getFormModels(String appID) {
        //先从asset_app_form表中找到所有该App应用下 所有绑定的表单模型ID
        List<String> formModelIDs = appFormBindMapper.getFormModelIDs(appID);

        return formModelMapper.getFormModels(formModelIDs);
    }

    public List<Application> getPublishedApp() {
        return applicationMapper.getPublishedApp();
    }
}
