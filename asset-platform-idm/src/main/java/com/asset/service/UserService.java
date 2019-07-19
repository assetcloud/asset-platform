package com.asset.service;

import com.asset.bean.PlatMenuRole;
import com.asset.bean.PlatRole;
import com.asset.bean.User;
import com.asset.common.SystemConstant;
import com.asset.mapper.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService{

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    UuidIdGenerator idGenerator;

    @Autowired
    private PlatRoleMapper platRoleMapper;

    @Autowired
    private PlatMenuMapper platMenuMapper;

    @Autowired
    private SceneMapper sceneMapper;


    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        return user;
    }*/

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (Func.isNull(user)) {
            throw new UsernameNotFoundException("用户名错误");
        }
//        List<PlatRole> roleAlias = userMapper.
        return user;
    }

    public int updateUserSelective(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<String> getPlatRoles(String userId){
//        return userMapper.;
        return null;
    }

    /**
     * 用户注册
     * @param user
     * @return int
     */
    public int insertUser(User user) {
        User var = userMapper.findUserByUsername(user.getAccountName());
        //如果用户名存在，返回错误
        if (var != null) {
            if (!var.getStatus() && var.getStage() == 1){
                throw new RuntimeException("用户已注册，处于待审核阶段");
            } else if (var.getStatus() && var.getStage() == 2){
                throw new RuntimeException("用户已存在");
            }
        }
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

    public User getUserById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int  updateUserById(User record){
        return userMapper.updateByPrimaryKey(record);
    }

    public int  updateUser(User record){
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public List<User> getUsersByRole(Long roleId){
        return userMapper.getUsersByRole(roleId);
    }

    /**
     * 用户注册
     * @param user
     * @return int
     */
    public int insertUserWithScene(User user, String sceneId) throws Exception {
        if (sceneMapper.selectByPrimaryKey(sceneId) == null){
            throw new RuntimeException("该场景不存在");
        }
        User var = userMapper.findUserByUsername(user.getAccountName());
        //如果用户名存在，返回错误
        if (var != null) {
            if (!var.getStatus() && var.getStage() == 1){
                throw new Exception("用户已注册，处于待审核阶段");
            } else if (var.getStatus() && var.getStage() == 2){
                throw new Exception("用户已存在");
            }
        }
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

        //指定为默认角色
        PlatRole platRole = new PlatRole(sceneId, SystemConstant.SCENE_DEFAULT_CH, SystemConstant.SCENE_DEFAULT);
        platRoleMapper.insert(platRole);
        //为默认角色配置权限
        PlatMenuRole platMenuRole = new PlatMenuRole(1, platRole.getId(), sceneId);
        List<PlatMenuRole> objects = new ArrayList<>();
        objects.add(platMenuRole);
        platMenuMapper.batchAddPlatMenuRole(objects);
        //用户与场景关联
        sceneMapper.userSceneBind(sceneId, user.getId(), platRole.getId());
        return userMapper.insertSelective(user);
    }
}
