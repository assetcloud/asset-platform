package com.asset.service;

import com.asset.bean.Application;
import com.asset.mapper.ApplicationMapper;
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

    public int addApplication(Application record){
        return applicationMapper.insert(record);
    }

    public List<Application> getAppList(){
        return applicationMapper.getAppList();
    }

    public int updateApplication(Application record){
        return applicationMapper.updateByPrimaryKey(record);
    }

    public Application getById(Long id){
        Application application = applicationMapper.selectByPrimaryKey(id);
        return application;
    }

    public int deleteApp(Long id){
        return applicationMapper.deleteByPrimaryKey(id);
    }
}
