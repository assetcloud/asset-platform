package com.asset.service;

import com.asset.bean.AppTemplate;
import com.asset.bean.Application;
import com.asset.mapper.AppTemplateMapper;
import com.asset.mapper.ApplicationMapper;
import com.asset.mapper.UuidIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    AppTemplateMapper appTemplateMapper;

    @Autowired
    UuidIdGenerator idGenerator;

    public int addApplication(Application record){
        if (record.getId() == null){
            record.setId(idGenerator.generateId());
        }
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

    public int appPublish(AppTemplate record){
        if(record.getId() == null){
            record.setId(idGenerator.generateId());
        }
        return appTemplateMapper.insert(record);
    }
}
