package com.asset.service;

import com.asset.entity.AsFormModelDO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.FormModelBO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-03
 */
public interface IAsFormModelService extends IService<AsFormModelDO> {

    public List<AdminFormModelVO> listAdminFormModelInfo(QueryWrapper<AsFormModelDO> queryWrapper);


    List<FormModelBO> listFormModelBOs(String appId, int groupId, int formStatus);

    String getFormModelSceneId(String taskId);
}
