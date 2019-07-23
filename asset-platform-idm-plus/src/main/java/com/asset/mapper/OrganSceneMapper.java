package com.asset.mapper;

import com.asset.bean.OrganScene;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjhu
 * @since 2019-07-23
 */
public interface OrganSceneMapper extends BaseMapper<OrganScene> {
    /**
     * 获取一个场景下的根部门（除了顶级节点以外）
     * @param sceneId
     * @return
     */
    OrganScene getRootNode(String sceneId);
}
