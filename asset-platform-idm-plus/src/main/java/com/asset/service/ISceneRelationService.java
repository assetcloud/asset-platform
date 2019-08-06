package com.asset.service;

import com.asset.bean.SceneRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  用户-业务角色关联
 * </p>
 *
 * @author hjhu
 * @since 2019-07-29
 */
public interface ISceneRelationService extends IService<SceneRelation> {
    /**
     * 批量新增业务角色-用户关联信息
     * @param rid
     * @param userIds
     * @return
     */
    boolean saveBatch(Long rid, List<String> userIds);

    /**
     * 批量删除角色成员
     * @param rid
     * @param userIds
     * @return
     */
    boolean removeBatch(Long rid, List<String> userIds);

}
