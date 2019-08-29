package com.asset.service.impl;

import com.asset.entity.AsTempletDeModelDO;
import com.asset.entity.AsTempletFormModelDO;
import com.asset.dao.AsTempletFormModelMapper;
import com.asset.entity.FormModelDO;
import com.asset.exception.DatabaseException;
import com.asset.service.IAsTempletFormModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AsTempletFormModelServiceImpl extends ServiceImpl<AsTempletFormModelMapper, AsTempletFormModelDO> implements IAsTempletFormModelService {

    @Autowired
    AsTempletFormModelMapper modelMapper;

    public AsTempletFormModelDO selectTempletResource(String linkFormModelId) {
        return modelMapper.selectById(linkFormModelId);
    }

    public String insertTempletResource(FormModelDO formModelDO) throws DatabaseException{
        AsTempletFormModelDO doo = new AsTempletFormModelDO();
        BeanUtils.copyProperties(formModelDO,doo,new String[]{"id"});
        int flag = modelMapper.insert(doo);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("发布表单模型失败！");

        return doo.getId();
    }
}
