package com.asset.service;

import com.asset.bean.User;
import com.asset.common.model.UserPageParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    /**
     * 用户注册
     * @param user
     * @return int
     */
    int insertUser(User user) ;

    List<User> getUsersByRole(Long roleId);

    boolean userExists(String accountName);

    boolean enableUser(String userId);

    /**
     * 获取不在某个场景中的用户
     * @param accountName
     * @param realName
     * @param email
     * @param sceneId
     * @return
     */
    List<User> getUsersWithoutScene(String accountName, String realName, String email, String sceneId);

    /**
     * 获取所有用户
     * @return
     */
    List<User> allUsers(UserPageParam userPageParam);
    /**
     * 控制台端添加用户
     * @param user
     * @return
     */
    boolean saveUser(User user);
    /**
     * 控制台删除用户
     * @param userId
     * @return
     */
    boolean removeUser(String userId);
}
