package com.asset.service.impl;

import com.asset.entity.ActDeModel;
import com.asset.dao.ActDeModelMapper;
import com.asset.entity.AsFormModel;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.AdminProcModelVO;
import com.asset.service.IActDeModelService;
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
public class ActDeModelServiceImpl extends ServiceImpl<ActDeModelMapper, ActDeModel> implements IActDeModelService {

    ActDeModelMapper actDeModelMapper;

    public List<AdminProcModelVO> listAdminProcModelInfo(QueryWrapper<ActDeModel> queryWrapper){
        List<ActDeModel> DOs = actDeModelMapper.selectList(queryWrapper);
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
}
