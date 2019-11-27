package com.asset.mapper;

import com.asset.entity.ProcTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface ProcTypeMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(ProcTypeEntity record);

	List<ProcTypeEntity> selectAll();

	int insertSelective(ProcTypeEntity record);

	ProcTypeEntity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProcTypeEntity record);

	int updateByPrimaryKey(ProcTypeEntity record);
}
