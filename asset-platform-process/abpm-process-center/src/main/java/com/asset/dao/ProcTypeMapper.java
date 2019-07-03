package com.asset.dao;

import com.asset.entity.ProcTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProcTypeMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(ProcTypeEntity record);

	List<ProcTypeEntity> selectAll();

	int insertSelective(ProcTypeEntity record);

	ProcTypeEntity selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProcTypeEntity record);

	int updateByPrimaryKey(ProcTypeEntity record);
}