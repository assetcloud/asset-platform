package com.asset.service.impl;

import com.asset.dao.AsTempletProcNodeMapper;
import com.asset.entity.ApplicationDO;
import com.asset.entity.AsTempletApplicationDO;
import com.asset.dao.AsTempletApplicationMapper;
import com.asset.entity.AsTempletFormModelDO;
import com.asset.entity.AsTempletProcNodeDO;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsTempletApplicationService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
@Service
public class AsTempletApplicationService extends ServiceImpl<AsTempletApplicationMapper, AsTempletApplicationDO> implements IAsTempletApplicationService {

    AsTempletApplicationMapper mapper;

    public AsTempletApplicationDO selectTempletResource(String id) {
        return mapper.selectById(id);
    }

    public String insertTempletResource(ApplicationDO applicationDO) throws DatabaseException{
        AsTempletApplicationDO doo = new AsTempletApplicationDO();
        BeanUtils.copyProperties(applicationDO,doo,new String[]{"id"});
        int flag = mapper.insert(doo);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("发布表单模型失败！");
        return doo.getId();
    }
}
