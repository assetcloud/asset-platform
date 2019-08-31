package com.asset.service;


import com.asset.dao.ApplicationMapper;
import com.asset.dao.FormModelMapper;
import com.asset.entity.ApplicationDO;
import com.asset.entity.AsTempletApplicationDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.UuidIdGenerator;
import com.asset.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ApplicationService {

    final static Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
            FormModelService formModelService;

    UuidIdGenerator idGenerator = new UuidIdGenerator();

    public String addApplication(ApplicationDO record) throws Exception{
        if (record.getId() == null){
            record.setId(idGenerator.generateId());
        }
        record.setCreatedTime(new Date());
        record.setStatus(1);
        record.setIsPublished(0);
        int flag = applicationMapper.insert(record);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
        return record.getId();
    }

    public List<ApplicationDO> getAppList(){
        return applicationMapper.getAppList();
    }

    public int updateApplication(ApplicationDO record){
        return applicationMapper.updateByPrimaryKey(record);
    }

    public ApplicationDO getById(String id){
        ApplicationDO application = applicationMapper.selectByPrimaryKey(id);
        return application;
    }

    public List<ApplicationDO> getPublishedApp() {
        return applicationMapper.getPublishedApp();
    }

    public String getNameById(String appId) {
        return applicationMapper.selectByPrimaryKey(appId).getApplicationName();
    }

    public boolean checkFormContain(String id) {
        List<String> formModelIds = formModelService.getFormModels(id);
        if(formModelIds == null || formModelIds.size() == 0 )
            return false;
        else
            return true;
    }

    public String insertTempletResource(AsTempletApplicationDO asTempletApplicationDO) throws DatabaseException{
        ApplicationDO applicationDO = new ApplicationDO.Builder()
                .status(Constants.AS_APPLICATION_ENABLE)
                .isPublished(Constants.AS_APPLICATION_PUBLISHED)
                .createdTime(new Date())
                .build();
        BeanUtils.copyProperties(asTempletApplicationDO,applicationDO,new String[]{"id"});
        int flag = applicationMapper.insertSelective(applicationDO);
        if(flag==Constants.DATABASE_FAILED)
            throw new DatabaseException("插入表单模型资源失败！");
        return applicationDO.getId();
    }

    public ApplicationDO selectOne(String appId) {
        return applicationMapper.selectOne(appId);
    }
}
