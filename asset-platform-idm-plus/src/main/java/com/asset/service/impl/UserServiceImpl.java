package com.asset.service.impl;

import com.asset.bean.User;
import com.asset.bean.UserRole;
import com.asset.common.SystemConstant;
import com.asset.common.model.UserPageParam;
import com.asset.mapper.UserMapper;
import com.asset.mapper.UuidIdGenerator;
import com.asset.service.IRoleService;
import com.asset.service.IUserRoleService;
import com.asset.service.IUserService;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService, IUserService {

    UserMapper userMapper;

    UuidIdGenerator idGenerator;

    IUserRoleService userRoleService;

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
    public boolean insertUser(User user) {
        if(user.getId() == null){
            user.setId(idGenerator.generateId());
        }
        //待审核状态
        user.setStage(1);
        user.setStatus(0);
        user.setCreatedTime(new Date());
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPwd(encode);
        UserRole userRole = new UserRole();
        userMapper.insert(user);
        userRole.setUid(user.getId());
        userRole.setCreatedTime(new Date());
        userRole.setRoleId(user.getRoleId());
        return userRoleService.save(userRole);
    }

    public List<User> getUsersByRole(Integer roleId){
        return userMapper.getUsersByRole(roleId);
    }

    public boolean userExists(String accountName){
        List<User> userList = userMapper.userExists(accountName);
        return userList.size() > 0;
    }

    public boolean enableUser(String userId){
        User user = userMapper.selectById(userId);
        user.setId(userId);
        user.setStatus(1);
        user.setStage(2);
        user.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
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

    @Override
    public List<User> allUsers(UserPageParam userPageParam) {
        return userMapper.users(userPageParam);
    }

    @Override
    public boolean saveUser(User record) {
        record.setCreatedTime(new Date());
        record.setStage(2);
        record.setEnableTime(new Date());
        record.setPwd(new BCryptPasswordEncoder().encode(record.getPwd()));
        UserRole userRole = new UserRole();
        if (record.getRoleId() == 1){
            record.setAdmin(1);
        } else {
            record.setAdmin(0);
        }
        userMapper.insert(record);
        userRole.setUid(record.getId());
        userRole.setCreatedTime(new Date());
        userRole.setRoleId(record.getRoleId());
        return userRoleService.save(userRole);
    }

    @Override
    public boolean removeUser(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setStatus(0);
        user.setDisableTime(new Date());
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean resetPassword(String userId) {
        User user = new User();
        user.setId(userId);
        user.setPwd(new BCryptPasswordEncoder().encode(SystemConstant.DEFAULT_PASSWORD));
        return userMapper.updateById(user) > 0;
    }
}
