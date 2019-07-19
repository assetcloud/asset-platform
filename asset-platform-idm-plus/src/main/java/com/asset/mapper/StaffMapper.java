package com.asset.mapper;

import com.asset.bean.Staff;
import org.springframework.stereotype.Component;

@Component
public interface StaffMapper {

    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}
