package com.asset.service.impl;

import com.asset.bean.UserScene;
import com.asset.mapper.UserSceneMapper;
import com.asset.service.IUserSceneService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserSceneServiceImpl extends ServiceImpl<UserSceneMapper, UserScene> implements IUserSceneService {
}
