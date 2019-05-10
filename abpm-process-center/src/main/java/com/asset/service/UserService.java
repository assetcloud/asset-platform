package com.asset.service;


import com.asset.entity.User;
import com.asset.utils.PageGrids;

/**
 * @author lichao
 */
public interface UserService {


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
}
