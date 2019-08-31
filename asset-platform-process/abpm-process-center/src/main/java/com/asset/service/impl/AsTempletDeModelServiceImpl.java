package com.asset.service.impl;

import com.asset.entity.ActDeModelDO;
import com.asset.entity.AsProcModelDO;
import com.asset.entity.AsTempletDeModelDO;
import com.asset.dao.AsTempletDeModelMapper;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsTempletDeModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模板中所属的流程模型信息（除了flowable设计的内容之外还包含为了设计界面额外增加的字段——node_num和seq_condition） 服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
@Service
public class AsTempletDeModelServiceImpl extends ServiceImpl<AsTempletDeModelMapper, AsTempletDeModelDO> implements IAsTempletDeModelService {

    @Autowired
    AsTempletDeModelMapper asTempletDeModelMapper;

    /**
     *
     * @param linkProcModelId
     * @return 返回生成的actDeModel信息
     */
    public AsTempletDeModelDO selectTempletResource(String linkProcModelId) {
        return asTempletDeModelMapper.selectById(linkProcModelId);
    }

    public String insertTempletResource(ActDeModelDO actDeModelDO)  throws DatabaseException{
        AsTempletDeModelDO doo = new AsTempletDeModelDO();
        BeanUtils.copyProperties(actDeModelDO,doo,new String[]{"id"});
        int flag = asTempletDeModelMapper.insert(doo);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("发布流程模型失败！");

        return doo.getId();
    }

    /**
     * 这也属于发布流程模型的内容，存的是ndoeNum和seqCondition的内容
     * @param asProcModelInfoDO
     */
    public void updateProcModelInfo(AsProcModelDO asProcModelInfoDO) throws DatabaseException{
        AsTempletDeModelDO doo = new AsTempletDeModelDO();
        doo.setNodeNum(asProcModelInfoDO.getNodeNum());
        doo.setSeqCondition(asProcModelInfoDO.getSeqCondition());
        doo.setId(asProcModelInfoDO.getId());

        UpdateWrapper<AsTempletDeModelDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",doo.getId());
        int flag = asTempletDeModelMapper.update(doo,updateWrapper);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("发布流程模型节点信息、序列条件失败！");
    }
}
