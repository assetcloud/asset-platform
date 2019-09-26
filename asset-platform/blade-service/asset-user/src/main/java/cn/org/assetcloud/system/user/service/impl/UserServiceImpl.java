package cn.org.assetcloud.system.user.service.impl;


import cn.org.assetcloud.system.entity.UserPageParam;
import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.user.mapper.UserMapper;
import cn.org.assetcloud.system.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.SystemConstant;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by sang on 2017/12/28.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return int
     */
    public int insertUser(User user) {
        //待审核状态
        user.setStage(1);
        user.setStatus(0);
        user.setCreatedTime(new Date());
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPwd());
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
        user.setStatus(1);
        user.setStage(2);
//        user.setRoleId(SystemConstant.SYSTEM_DEFAULT_USER);
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
        return userMapper.insert(record) > 0;
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

	@Override
	public User getUser(String accountName, String password) {
		User user = baseMapper.findUserByUsername(accountName);
		if (BCrypt.checkpw(password, user.getPwd())){
			return user;
		}
		return null;
	}
}
