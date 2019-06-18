package com.asset.mapper;

import com.asset.bean.Role;
import com.asset.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getRolesByUserId(String id);

    List<User> getAllUser(int currentId);

    int deleteByPrimaryKey(String id);

    int userReg(@Param("accountName") String accountName, @Param("pwd") String pwd);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    List<User> getUsersByRole(@Param("roleId") Long roleId);
}
