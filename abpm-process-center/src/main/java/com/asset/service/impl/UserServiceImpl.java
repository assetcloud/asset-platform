package com.asset.service.impl;

import com.asset.dao.UserMapper;
import com.asset.entity.User;
import com.asset.service.UserService;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.getUserByName(s);
        User user = userMapper.selectByPrimaryKey(s);
        if(user == null){
            throw new UsernameNotFoundException("用户名错误");
        }
        return user;
    }

}
