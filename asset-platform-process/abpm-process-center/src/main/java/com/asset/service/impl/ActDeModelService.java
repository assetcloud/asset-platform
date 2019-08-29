package com.asset.service.impl;

import com.asset.entity.ActDeModelDO;
import com.asset.dao.ActDeModelMapper;
import com.asset.entity.AsTempletDeModelDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.AdminProcModelVO;
import com.asset.service.IActDeModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对act_de_model表进行操作
 *
 * @author YBY
 * @since 2019-08-04
 */
@Service
@AllArgsConstructor
public class ActDeModelService extends ServiceImpl<ActDeModelMapper, ActDeModelDO> implements IActDeModelService {

    ActDeModelMapper actDeModelMapper;

    public List<AdminProcModelVO> listAdminProcModelInfo(QueryWrapper<ActDeModelDO> queryWrapper){
        List<ActDeModelDO> DOs = actDeModelMapper.selectList(queryWrapper);
        List<AdminProcModelVO> VOs = new ArrayList<>();

        for(int i=0;i<DOs.size();i++)
        {
            AdminProcModelVO vo = new AdminProcModelVO();
            BeanUtils.copyProperties(DOs.get(i),vo);

            if(DOs.get(i).getCreated()!=null)
                vo.setCreated(DOs.get(i).getCreated().getTime());
            if(DOs.get(i).getLastUpdated()!=null)
                vo.setLastUpdated(DOs.get(i).getLastUpdated().getTime());

            VOs.add(vo);
        }

        return VOs;
    }

    /**
     * 把模板资源插入act_de_model表中
     * as_proc_model中的资源由另外的service操作
     * @param asTempletDeModelDO
     */
    public String insertTempletResource(AsTempletDeModelDO asTempletDeModelDO,String userId) throws DatabaseException{
        ActDeModelDO actDeModelDO = new ActDeModelDO.Builder()
                .created(new Date())
                .createdBy(userId)
                .version(1)
                .build();
        BeanUtils.copyProperties(asTempletDeModelDO, actDeModelDO,new String[]{"id"});
        int flag = actDeModelMapper.insert(actDeModelDO);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("导入流程模型资源失败！");
        return actDeModelDO.getId();
    }

    public ActDeModelDO selectModelDO(String procModelId) {
        return actDeModelMapper.selectById(procModelId);
    }
}
