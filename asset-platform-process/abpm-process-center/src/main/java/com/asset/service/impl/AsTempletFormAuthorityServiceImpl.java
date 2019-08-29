package com.asset.service.impl;

import com.asset.dao.AsTempletProcNodeMapper;
import com.asset.entity.*;
import com.asset.dao.AsTempletFormAuthorityMapper;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsTempletFormAuthorityService;
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
public class AsTempletFormAuthorityServiceImpl extends ServiceImpl<AsTempletFormAuthorityMapper, AsTempletFormAuthorityDO> implements IAsTempletFormAuthorityService {

    AsTempletFormAuthorityMapper mapper;

    public List<AsTempletFormAuthorityDO> selectTempletResource(String templetProcModelId) {
        QueryWrapper<AsTempletFormAuthorityDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTempletFormAuthorityDO::getTempletProcModelId,templetProcModelId);

        return mapper.selectList(queryWrapper);
    }

    public void insertTempletResource(List<FormAuthorityDO> authorityDOs, String templetProcModelId) throws DatabaseException{
        for(int i = 0;i<authorityDOs.size();i++)
        {
            FormAuthorityDO cur = authorityDOs.get(i);
            AsTempletFormAuthorityDO doo = new AsTempletFormAuthorityDO.Builder()
                    .templetProcModelId(templetProcModelId)
                    .build();
            BeanUtils.copyProperties(cur,doo,new String[]{"id"});
            int flag = mapper.insert(doo);
            if(flag== Constants.DATABASE_FAILED)
                throw new DatabaseException("发布流程模型失败！");
        }
    }
}
