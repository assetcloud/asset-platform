package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface StaffMapper {

    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}
