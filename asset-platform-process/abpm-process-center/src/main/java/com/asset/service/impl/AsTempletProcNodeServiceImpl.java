package com.asset.service.impl;

import com.asset.entity.AsTempletDeModelDO;
import com.asset.entity.AsTempletProcNodeDO;
import com.asset.dao.AsTempletProcNodeMapper;
import com.asset.entity.ProcNodeDO;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsTempletProcNodeService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
@Service
@AllArgsConstructor
public class AsTempletProcNodeServiceImpl extends ServiceImpl<AsTempletProcNodeMapper, AsTempletProcNodeDO> implements IAsTempletProcNodeService {

    AsTempletProcNodeMapper mapper;

    public List<AsTempletProcNodeDO> selectTempletResource(String templetProcModelId) {
        QueryWrapper<AsTempletProcNodeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTempletProcNodeDO::getTempletProcModelId,templetProcModelId);
        return mapper.selectList(queryWrapper);
    }

    public void insertTempletResource(List<ProcNodeDO> nodes,String templetProcModelId) throws DatabaseException {
        for(int i = 0;i<nodes.size();i++)
        {
            ProcNodeDO cur = nodes.get(i);
            AsTempletProcNodeDO doo = new AsTempletProcNodeDO.Builder()
                    .templetProcModelId(templetProcModelId)
                    .build();
            BeanUtils.copyProperties(cur,doo,new String[]{"id"});
            int flag = mapper.insert(doo);
            if(flag== Constants.DATABASE_FAILED)
                throw new DatabaseException("发布流程模型失败！");
        }
    }
}
