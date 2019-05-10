package com.asset.service.impl;

import com.asset.dao.UserMapper;
import com.asset.entity.User;
import com.asset.service.UserService;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public PageGrids getUsers(Integer pageNum, Integer pageSize, String id, String displayName) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.getUsers(id, displayName);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
