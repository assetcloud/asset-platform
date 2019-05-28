package com.asset.service;


import com.asset.entity.User;
import com.asset.utils.PageGrids;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lichao
 */
public interface UserService extends UserDetailsService {

    /**
     * 获取用户列表
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    PageGrids getUsers(Integer pageNum, Integer pageSize, String id, String displayName);

    /**
     * 添加用户
     * @param user
     * @return int
     */
    int addUser(User user);

    /**
     * 通过id获取用户
     * @param id
     * @return User
     */
    User getUserById(String id);
}
