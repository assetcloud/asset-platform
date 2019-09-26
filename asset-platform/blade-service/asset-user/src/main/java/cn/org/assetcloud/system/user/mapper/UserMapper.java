package cn.org.assetcloud.system.user.mapper;

import cn.org.assetcloud.system.entity.UserPageParam;
import cn.org.assetcloud.system.user.entity.SceneRole;
import cn.org.assetcloud.system.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(String username);

    User getUser(String accountName, String pwd);

    User findUserByUsername(@Param("username") String username);

    List<SceneRole> getRolesByUserId(String id);

    List<User> getAllUser(int currentId);

    int deleteByPrimaryKey(String id);

    int userReg(@Param("accountName") String accountName, @Param("pwd") String pwd);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    List<User> getUsersByRole(@Param("roleId") Long roleId);

    List<User> getUsersByScene(String sceneId);

    List<User> userExists(String accountName);

    List<User> usersWithoutScene(String accountName, String realName, String email, String sceneId);

    List<User> users(UserPageParam userPageParam);
}
