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

    /**
     * 添加员工
     * @param staff
     * @param user
     * @return Map<String, Object>
     */
    public Map<String, Object> addStaff(Staff staff, User user) {
        Map<String, Object> resultMap = new HashMap<>();
        if(staff.getId() == null){
            String newId = idGenerator.generateId();
            staff.setId(newId);
            resultMap.put("staffId",newId);
        }
        staff.setStaffName(user.getRealName());
        staff.setGender(user.getGender());
        staff.setUserId(user.getId());
        staff.setContactPhoneNumber(user.getPhoneNumber());
        staff.setStaffBirthday(user.getUserBirthday());
        staff.setCertificateType(String.valueOf(user.getCertificateType()));
        staff.setCertificateNumber(user.getCertificateNumber());
        staff.setStatus("1");
        staff.setRemoveTag(0);
        staff.setUserCreatedTag("1");
        staff.setAssetStatus("1");
        staff.setFamilyAddress(user.getUserAddress());
        int flag = staffMapper.insert(staff);
        resultMap.put("flag", flag);
        return resultMap;
    }
}
