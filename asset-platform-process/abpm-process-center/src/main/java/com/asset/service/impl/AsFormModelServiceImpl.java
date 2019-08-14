package com.asset.service.impl;

import com.asset.entity.AsFormModel;
import com.asset.dao.AsFormModelMapper;
import com.asset.entity.FormModelDO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.service.AppGroupService;
import com.asset.service.ApplicationService;
import com.asset.service.FormModelService;
import com.asset.service.IAsFormModelService;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-03
 */
@Service
public class AsFormModelServiceImpl extends ServiceImpl<AsFormModelMapper, AsFormModel> implements IAsFormModelService {

    @Autowired
    AsFormModelMapper modelMapper;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    AppGroupService groupService;

    public List<AdminFormModelVO> listAdminFormModelInfo(QueryWrapper<AsFormModel> queryWrapper){
        List<AsFormModel> DOs = modelMapper.selectList(queryWrapper);
        List<AdminFormModelVO> VOs = new ArrayList<>();

        for(int i=0;i<DOs.size();i++)
        {
            AdminFormModelVO vo = new AdminFormModelVO();
            BeanUtils.copyProperties(DOs.get(i),vo);

            vo.setCreatedTime(DOs.get(i).getCreatedTime().getTime());
            vo.setAppName(applicationService.getNameById(vo.getAppId()));
            if(vo.getGroupId()!=-1)
                vo.setGroupName(groupService.getGroupName(vo.getGroupId()));

            VOs.add(vo);
        }

        return VOs;
    }
//    public IPage<AdminFormModelVO> listAdminFormModelInfo(Page query, QueryWrapper<AsFormModel> queryWrapper){
//        IPage<AsFormModel> DOIPage = modelMapper.selectPage(query, queryWrapper);
//        List<AsFormModel> DOs = DOIPage.getRecords();
//        List<AdminFormModelVO> VOs = new ArrayList<>();
//
//        for(int i=0;i<DOs.size();i++)
//        {
//            AdminFormModelVO vo = new AdminFormModelVO();
//            BeanUtils.copyProperties(DOs.get(i),vo);
//
//            vo.setCreatedTime(DOs.get(i).getCreatedTime().getTime());
//
//            VOs.add(vo);
//        }
//
//        IPage<AdminFormModelVO> VOIpage = new Page<>(DOIPage.getCurrent(),DOIPage.getSize(),DOIPage.getTotal());
//        VOIpage.setRecords(VOs);
//
//        return VOIpage;
//    }




    public List<AdminFormModelVO> getFormModels(String appId) {
        List<FormModelDO> DOList = formModelService.getFormModelDOs(appId, Constants.GROUP_ALL,Constants.FORM_MODEL_ALL);
        List<AdminFormModelVO> VOList = new ArrayList<>();

        VOList = transDOtoVO(DOList,VOList);

        return VOList;
    }

    private List<AdminFormModelVO> transDOtoVO(List<FormModelDO> DOList, List<AdminFormModelVO> VOList) {
        for(int i=0;i<DOList.size();i++)
        {
            AdminFormModelVO vo = new AdminFormModelVO();
            BeanUtils.copyProperties(DOList.get(i),vo);

            vo.setCreatedTime(DOList.get(i).getCreatedTime().getTime());

            VOList.add(vo);
        }
        return VOList;
    }

}
