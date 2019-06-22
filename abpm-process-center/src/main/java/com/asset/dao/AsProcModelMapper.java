package com.asset.dao;


import com.asset.entity.AsProcModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AsProcModelMapper {
    int deleteByPrimaryKey(String procModelId);

    int insert(AsProcModel record);

    int insertSelective(AsProcModel record);

    AsProcModel selectByPrimaryKey(String procModelId);

    int updateByPrimaryKeySelective(AsProcModel record);

    int updateByPrimaryKey(AsProcModel record);

    /**
     * 这里对数据库表进行写入操作，如果存在主键相同的数据项，就在原来基础上添加
     * @param model
     */
    void editProcModel(AsProcModel model);
}
