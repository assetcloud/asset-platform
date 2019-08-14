package com.asset.service.impl;

import com.asset.bean.OrganScene;
import com.asset.mapper.OrganSceneMapper;
import com.asset.service.IOrganSceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-23
 */
@Service
public class OrganSceneServiceImpl extends ServiceImpl<OrganSceneMapper, OrganScene> implements IOrganSceneService {

    @Autowired
    OrganSceneMapper organSceneMapper;

    @Override
    public boolean batchRemove(List<String> nodeIds, String sceneId) {
        return organSceneMapper.batchDeleteByNodeId(nodeIds, sceneId) > 0;
    }

    @Override
    public boolean batchRemoveMembers(List<String> userIds, String sceneId) {
        return organSceneMapper.batchDeleteByUserId(userIds, sceneId) > 0;
    }
}
