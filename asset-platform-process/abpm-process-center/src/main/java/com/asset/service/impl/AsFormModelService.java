package com.asset.service.impl;

import com.asset.entity.AsFormModelDO;
import com.asset.dao.AsFormModelMapper;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.FormModelBO;
import com.asset.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-03
 */
@Service
public class AsFormModelService extends ServiceImpl<AsFormModelMapper, AsFormModelDO> implements IAsFormModelService {

    @Autowired
    AsFormModelMapper modelMapper;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    AppGroupService groupService;
    @Autowired
    FormInstService formInstService;


    public List<AdminFormModelVO> listAdminFormModelInfo(QueryWrapper<AsFormModelDO> queryWrapper) {
        List<AsFormModelDO> DOs = modelMapper.selectList(queryWrapper);
        List<AdminFormModelVO> VOs = new ArrayList<>();

        for (int i = 0; i < DOs.size(); i++) {
            AdminFormModelVO vo = new AdminFormModelVO();
            BeanUtils.copyProperties(DOs.get(i), vo);

            vo.setCreatedTime(DOs.get(i).getCreatedTime().getTime());
            vo.setAppName(applicationService.getNameById(vo.getAppId()));
            if (vo.getGroupId() != -1)
                vo.setGroupName(groupService.getGroupName(vo.getGroupId()));

            VOs.add(vo);
        }

        return VOs;
    }

    /**
     * 入口方法
     * @param appId
     * @param groupId
     * @param formStatus 0——所有表单模型,1——未绑定流程模型,2——已绑定流程模型，且其他的内容也都已经添加(不对分支条件进行筛选)
     * @return
     */
    @Override
    public List<FormModelBO> listFormModelBOs(String appId, int groupId, int formStatus) {
        //根据formStatus采取不同的取值策略
        QueryWrapper<AsFormModelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormModelDO::getGroupId, groupId)
                .eq(AsFormModelDO::getAppId, appId)
                .eq(AsFormModelDO::getIsDeleted, 0);
        List<AsFormModelDO> formModelDOs = null;
        switch (formStatus) {
            //取所有表单模型
            case 0:
                formModelDOs = modelMapper.selectList(queryWrapper);
                break;
            //取未绑定流程模型
            case 1:
                queryWrapper.lambda()
                        .eq(AsFormModelDO::getIsBinded, 0);
                formModelDOs = modelMapper.selectList(queryWrapper);
                break;
            //取可以被发起的表单模型
            case 2:
                queryWrapper.lambda()
                        .eq(AsFormModelDO::getIsBinded, 1)
                        .eq(AsFormModelDO::getIsBindAuthority, 1)
                        .eq(AsFormModelDO::getIsAddNodeInfo, 1);
                formModelDOs = modelMapper.selectList(queryWrapper);
                break;
        }

        if(formModelDOs.size()==0)
            return null;

        List<FormModelBO> boList = new ArrayList<>();
        for (int i = 0; i < formModelDOs.size(); i++) {
            FormModelBO bo = new FormModelBO();
            BeanUtils.copyProperties(formModelDOs.get(i), bo);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public String getFormModelSceneId(String taskId) {
        String formModelId = formInstService.getFormModelId(taskId);
        return modelMapper.selectById(formModelId).getSceneId();
    }


}
