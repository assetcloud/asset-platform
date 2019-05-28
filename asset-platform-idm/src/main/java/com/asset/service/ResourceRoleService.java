package com.asset.service;

import com.asset.mapper.ResourceRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hjhu on 2019/5/28.
 */
@Service
@Transactional
public class ResourceRoleService {

    @Autowired
    ResourceRoleMapper resourceRoleMapper;

    public int updateMenuRole(Integer rid, Integer[] mids) {
        resourceRoleMapper.deleteResourceByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        return resourceRoleMapper.addResource(rid, mids);
    }
}
