package com.asset.mapper;

import com.asset.entity.ActHiActinst;
import com.asset.entity.ActRuTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YBY
 * @since 2019-11-27
 */
@Mapper
public interface ActHiActinstMapper extends BaseMapper<ActHiActinst> {

    /**
     * 在某个时间点，应该只有一个task拥有某一个exeId待执行
     * @param executionId
     * @return
     */
    ActHiActinst selectUnCompleteTaskByExeId(String executionId);


    List<ActHiActinst> selectUnCompleteTaskByInstId(String instId);
}
