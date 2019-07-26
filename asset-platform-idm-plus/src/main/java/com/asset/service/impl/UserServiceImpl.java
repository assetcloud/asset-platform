package com.asset.service.impl;

import com.asset.bean.User;
import com.asset.common.SystemConstant;
import com.asset.mapper.UserMapper;
import com.asset.mapper.UuidIdGenerator;
import com.asset.service.IUserService;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService, IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UuidIdGenerator idGenerator;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (Func.isNull(user)) {
            throw new UsernameNotFoundException("用户名错误");
        }
        return user;
    }

    /**
     * 用户注册
     * @param user
     * @return int
     */
    public int insertUser(User user) {
        if(user.getId() == null){
            user.setId(idGenerator.generateId());
        }
        //待审核状态
        user.setStage(1);
        user.setStatus(false);
        user.setCreatedTime(new Date());
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPwd(encode);
        return userMapper.insertSelective(user);
    }

    public List<User> getUsersByRole(Long roleId){
        return userMapper.getUsersByRole(roleId);
    }

    public boolean userExists(String accountName){
        List<User> userList = userMapper.userExists(accountName);
        return userList.size() > 0;
    }

    public boolean enableUser(String userId){
        User user = userMapper.selectById(userId);
        user.setId(userId);
        user.setStatus(true);
        user.setStage(2);
        user.setRoleId(SystemConstant.SYSTEM_DEFAULT_USER);
        return userMapper.updateById(user) > 0;
    }

    /**
     * 获取不在某个场景中的用户
     * @param accountName
     * @param realName
     * @param email
     * @param sceneId
     * @return
     */
    public List<User> getUsersWithoutScene(String accountName, String realName, String email, String sceneId){
        return userMapper.usersWithoutScene(accountName, realName, email, sceneId);
    }
}
