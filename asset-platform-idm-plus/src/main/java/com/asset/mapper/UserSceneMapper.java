package com.asset.mapper;

import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;


@Component
public interface UserSceneMapper extends BaseMapper<UserScene> {

    UserScene hasScene(String userId, String sceneId);
}
