package com.asset.service.impl;

import com.asset.dto.SeqConditionDTO;
import com.asset.entity.ApplicationDO;
import com.asset.entity.AsProcModelDO;
import com.asset.dao.AsProcModelMapper;
import com.asset.entity.AsTempletDeModelDO;
import com.asset.exception.DatabaseException;
import com.asset.service.FormModelService;
import com.asset.service.IAsProcModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 负责对流程模型整体属性进行处理，as_proc_model
 * 节点信息业务找ProcNodeService
 * @author YBY
 * @since 2019-08-07
 */
@Service
public class AsProcModelService extends ServiceImpl<AsProcModelMapper, AsProcModelDO> implements IAsProcModelService {

    @Autowired
    AsProcModelMapper asProcModelMapper;
    @Autowired
    FormModelService formModelService;

    @Override
    public void saveProcNodeNum(String procModelId,Integer procNodeNum) throws Exception{
        AsProcModelDO asProcModelDO = new AsProcModelDO.Builder()
                .id(procModelId)
                .nodeNum(procNodeNum).build();
        int flag = asProcModelMapper.insert(asProcModelDO);
        if(flag == Constants.DATABASE_FAILED)
            throw new DatabaseException("插入数据失败！");
    }

    @Override
    public void updateProcNodeNum(String procModelId, Integer procNodeNum)  throws Exception{
        AsProcModelDO asProcModelDO = new AsProcModelDO.Builder()
                .id(procModelId)
                .nodeNum(procNodeNum).build();
        int flag = asProcModelMapper.updateById(asProcModelDO);
        if(flag == Constants.DATABASE_FAILED)
            throw new DatabaseException("更新数据失败！");
    }

    @Override
    public void saveSeqCondition(SeqConditionDTO dto)throws Exception {
        String procModelId = dto.getProc_model_id();
        String seqConditions = dto.getSeq_condition();
        AsProcModelDO asProcModelDO = new AsProcModelDO.Builder()
                .id(procModelId)
                .seqCondition(seqConditions).build();

        int flag = Constants.DATABASE_FAILED;

        if(contain(procModelId))
            flag = asProcModelMapper.updateById(asProcModelDO);
        else
            flag = asProcModelMapper.insert(asProcModelDO);

        formModelService.updateSeqCondition(procModelId);

        if(flag == Constants.DATABASE_FAILED)
            throw new DatabaseException("更新数据失败！");
    }

    private boolean contain(String procModelId) {
        return asProcModelMapper.selectById(procModelId)!=null?true:false;
    }

    @Override
    public AsProcModelDO getProcModelById(String procModelId) {
        return asProcModelMapper.selectById(procModelId);
    }

    @Override
    public int getProcNodeNum(String procModelId) {
        return asProcModelMapper.selectById(procModelId).getNodeNum();
    }

    @Override
    public String getBindFormSheet(String procModelId) {
        return formModelService.getBindFormSheet(procModelId);
    }


    public void insertTempletResource(AsTempletDeModelDO asTempletDeModelDO,
                                      String generateModelId) throws DatabaseException{
        AsProcModelDO asProcModelDO = new AsProcModelDO.Builder()
                .id(generateModelId)
                .nodeNum(asTempletDeModelDO.getNodeNum())
                .seqCondition(asTempletDeModelDO.getSeqCondition())
                .build();
        int flag = asProcModelMapper.insert(asProcModelDO);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("导入流程模型资源失败！");
    }

    public AsProcModelDO selectOne(String procModelId) {
        return asProcModelMapper.selectById(procModelId);
    }
}
