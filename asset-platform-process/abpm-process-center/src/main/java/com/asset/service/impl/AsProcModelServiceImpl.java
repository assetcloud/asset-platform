package com.asset.service.impl;

import com.asset.entity.AsProcModel;
import com.asset.dao.AsProcModelMapper;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsProcModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 负责对流程模型整体属性进行处理，as_proc_model
 * 节点信息业务找ProcNodeService
 * @author YBY
 * @since 2019-08-07
 */
@Service
@AllArgsConstructor
public class AsProcModelServiceImpl extends ServiceImpl<AsProcModelMapper, AsProcModel> implements IAsProcModelService {

    AsProcModelMapper asProcModelMapper;

    @Override
    public void saveProcNodeNum(String procModelId,Integer procNodeNum) throws Exception{
        AsProcModel asProcModel = new AsProcModel.Builder()
                .id(procModelId)
                .nodeNum(procNodeNum).build();
        int flag = asProcModelMapper.insert(asProcModel);
        if(flag == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    @Override
    public int getProcNodeNum(String procModelId) {
        return asProcModelMapper.selectById(procModelId).getNodeNum();
    }
}
