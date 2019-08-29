package com.asset.service.impl;

import com.asset.entity.AsApplicationDO;
import com.asset.javabean.AdminAppInfoVO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.dao.AsApplicationMapper;
import com.asset.service.AdminFormModelService;
import com.asset.service.ApplicationService;
import com.asset.service.IAsApplicationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-04
 */
@Service
@AllArgsConstructor
public class AsApplicationService extends ServiceImpl<AsApplicationMapper, AsApplicationDO> implements IAsApplicationService {

    ApplicationService applicationService;
    AdminFormModelService adminFormModelService;
    AsApplicationMapper asApplicationMapper;

    /**
     * 先获取对应分页数的应用条目，然后去找相应应用下的表单模型信息（这里就是前面获取表单模型列表相同的操作），然后在组装成一个整体、返回
     * @return
     */
    public List<AdminAppInfoVO> getAppInfos(QueryWrapper<AsApplicationDO> queryWrapper) {
        //先获取应用列表
        List<AsApplicationDO> applicationDOs = asApplicationMapper.selectList(queryWrapper);
        List<AdminAppInfoVO> VOs= new ArrayList<>();

        for(int i=0;i<applicationDOs.size();i++)
        {
            AdminAppInfoVO vo = new AdminAppInfoVO();
            BeanUtils.copyProperties(applicationDOs.get(i),vo);

            //获取
            List<AdminFormModelVO> formModels = adminFormModelService.getApplicationFormModels(applicationDOs.get(i).getId());
            vo.setAdminFormModelVOs(formModels);

            VOs.add(vo);
        }

        return VOs;
    }



}
