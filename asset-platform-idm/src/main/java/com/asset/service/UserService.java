package com.asset.service;

import com.asset.bean.User;
import com.asset.mapper.UserMapper;
import com.asset.mapper.UuidIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UuidIdGenerator idGenerator;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        return user;
    }

    public int userReg(String accountName, String pwd) {
        //如果用户名存在，返回错误
        if (userMapper.loadUserByUsername(accountName) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(pwd);
        return userMapper.userReg(accountName, encode);
    }

    public int insertUser(User user) {
        //如果用户名存在，返回错误
        if (userMapper.loadUserByUsername(user.getAccountName()) != null) {
            return -1;
        }
        if(user.getId() == null){
            user.setId(idGenerator.generateId());
            LOGGER.info("user: {}", user.toString());
        }
        user.setAdmin(0);
        user.setStatus(true);
        user.setCreatedTime(new Date());
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());user.setPwd(encode);
        return userMapper.insert(user);
    }

    public User getUserById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int  updateUserById(User record){
        return userMapper.updateByPrimaryKey(record);
    }

    public List<User> getUsersByRole(Long roleId){
        return userMapper.getUsersByRole(roleId);
    }
}
