package com.asset.service;

import com.asset.bean.User;
import com.asset.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        return user;
    }

    public int userReg(String accountName, String pwd) {
        //如果用户名存在，返回错误
        if (userMapper.loadUserByUsername(accountName) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(pwd);
        return userMapper.userReg(accountName, encode);
    }

    public int insertUser(User user) {
        //如果用户名存在，返回错误
        if (userMapper.loadUserByUsername(user.getAccountName()) != null) {
            return -1;
        }
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());user.setPwd(encode);
        return userMapper.insert(user);
    }

    /*public List<Hr> getHrsByKeywords(String keywords) {
        return hrMapper.getHrsByKeywords(keywords);
    }

    public int updateHr(Hr hr) {
        return hrMapper.updateHr(hr);
    }

    public int updateHrRoles(Long hrId, Long[] rids) {
        int i = hrMapper.deleteRoleByHrId(hrId);
        return hrMapper.addRolesForHr(hrId, rids);
    }

    public Hr getHrById(Long hrId) {
        return hrMapper.getHrById(hrId);
    }

    public int deleteHr(Long hrId) {
        return hrMapper.deleteHr(hrId);
    }

    public List<Hr> getAllHrExceptAdmin() {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId());
    }
    public List<Hr> getAllHr() {
        return hrMapper.getAllHr(null);
    }*/
}
