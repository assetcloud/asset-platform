package com.asset.service;

import com.asset.bean.Resource;
import com.asset.common.UserUtils;
import com.asset.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hjhu on 2018/5/28.
 */
@Service
@Transactional
//@CacheConfig(cacheNames = "menus_cache")
public class ResourceService {
    @Autowired
    ResourceMapper resourceMapper;

//    @Cacheable(key = "#root.methodName")
    public List<Resource> getAllResource(){
        return resourceMapper.getAllResource();
    }

    public List<Resource> getResourcesByUserId() {
        return resourceMapper.getResourcesByUserId(UserUtils.getCurrentUser().getId());
    }

    public List<Resource> resourceTree() {
        return resourceMapper.resourceTree();
    }

    public List<Integer> getResourcesByRid(Integer rid) {
        return resourceMapper.getResourcesByRid(rid);
    }
}
