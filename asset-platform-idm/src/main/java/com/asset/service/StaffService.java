package com.asset.service;

import com.asset.bean.Staff;
import com.asset.bean.User;
import com.asset.mapper.StaffMapper;
import com.asset.mapper.UserMapper;
import com.asset.mapper.UuidIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
public class StaffService {

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    UuidIdGenerator idGenerator;

    public Map<String, Object> addStaff(Staff staff) {
        Map<String, Object> resultMap = new HashMap<>();
        if(staff.getId() == null){
            String newId = idGenerator.generateId();
            staff.setId(newId);
            resultMap.put("staffId",newId);
        }
        int flag = staffMapper.insert(staff);
        resultMap.put("flag", flag);
        return resultMap;
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
