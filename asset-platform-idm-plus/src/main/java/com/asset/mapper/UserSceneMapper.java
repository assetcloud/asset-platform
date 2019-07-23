package com.asset.mapper;

import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public interface UserSceneMapper extends BaseMapper<UserScene> {

    /**
     * 判断是否用户-场景关联是否存在
     * @param userId
     * @param sceneId
     * @return
     */
    UserScene hasScene(String userId, String sceneId);
    /**
     * 根据场景和用户id更新 用户-场景关联信息
     * @param userId
     * @param sceneId
     * @return
     */
    int updateByUserAndScene(String userId, String sceneId);
}
