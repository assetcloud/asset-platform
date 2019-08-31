package com.asset.mapper;

import com.asset.bean.Role;
import com.asset.bean.SceneRole;
import com.asset.bean.User;
import com.asset.common.model.UserPageParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(String username);

    User findUserByUsername(@Param("username") String username);

    List<Role> getRolesByUserId(String id);

    List<User> getAllUser(int currentId);

    int deleteByPrimaryKey(String id);

    int userReg(@Param("accountName") String accountName, @Param("pwd") String pwd);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    List<User> getUsersByRole(@Param("roleId") Integer roleId);

    List<User> getUsersByScene(String sceneId);

    List<User> userExists(String accountName);

    List<User> usersWithoutScene(String accountName, String realName, String email, String sceneId);

    List<User> users(UserPageParam userPageParam);
}
