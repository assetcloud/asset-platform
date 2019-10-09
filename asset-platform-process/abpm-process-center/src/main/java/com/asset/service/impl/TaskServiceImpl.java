package com.asset.service.impl;

import com.asset.entity.AsFormInstDO;
import com.asset.dao.AsFormInstMapper;
import com.asset.javabean.AdminTaskVO;
import com.asset.service.FormModelService;
import com.asset.service.ITaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-04
 */
@Service
@AllArgsConstructor
public class TaskServiceImpl extends ServiceImpl<AsFormInstMapper, AsFormInstDO> implements ITaskService {

    AsFormInstMapper formInstMapper;
    FormModelService formModelService;
    /**
     * 在控制台端获取流程任务节点信息
     *
     * @return
     */
    public List<AdminTaskVO> listAdminProcTaskInfo(QueryWrapper<AsFormInstDO> queryWrapper) {
        List<AsFormInstDO> formInstDOs = formInstMapper.selectList(queryWrapper);
        List<AdminTaskVO> VOs = new ArrayList<>();
        for (int i = 0; i < formInstDOs.size(); i++) {
            AdminTaskVO vo = new AdminTaskVO();
            BeanUtils.copyProperties(formInstDOs.get(i), vo);

            //手动赋值
            vo.setFormInstId(formInstDOs.get(i).getId());
            if (formInstDOs.get(i).getExecuteTime() != null)
                vo.setExecutorTime(formInstDOs.get(i).getExecuteTime().getTime());
            vo.setInstName(formModelService.getFormName(formInstDOs.get(i).getFormModelId()));

            VOs.add(vo);
        }
        return VOs;
    }


}
