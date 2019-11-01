package com.asset.service.impl;

import com.asset.entity.*;
import com.asset.dao.AsTempletMapper;
import com.asset.exception.DatabaseException;
import com.asset.javabean.AdminTempletVO;
import com.asset.service.*;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YBY
 * @since 2019-08-17
 */
@Service
public class AsTempletServiceImpl extends ServiceImpl<AsTempletMapper, AsTempletDO> implements IAsTempletService {

    @Autowired
    AsTempletMapper asTempletMapper;
    @Autowired
    AsTempletDeModelServiceImpl asTempletDeModelService;
    @Autowired
    AsTempletFormModelServiceImpl asTempletFormModelService;
    @Autowired
    AsTempletApplicationService asTempletApplicationService;
    @Autowired
    AsTempletFormAuthorityServiceImpl asTempletFormAuthorityService;
    @Autowired
    AsTempletProcNodeServiceImpl asTempletProcNodeService;


    @Autowired
    ActDeModelService actDeModelService;
    @Autowired
    AsProcModelService asProcModelService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    AuthorityService authorityService;


    public List<AsTempletDO> listAll() {
        List<AsTempletDO> asTempletDOS = asTempletMapper.selectAll();
        return asTempletDOS;
    }

    /**
     * 导入选中的应用模板
     * 1、导入流程模型（包含流程模型sequenceFlow条件、nodeNum数据）
     * 2、导入应用资源
     * 3、导入表单模型
     * 4、导入模型节点信息
     * 5、导入表单项权限信息
     * @param templetId
     */
    public void leadIntoTemplet(String templetId,
                                String userId,
                                String sceneId) throws RuntimeException {
        //1、导入流程模型资源
        AsTempletDO curAppTemplet = asTempletMapper.selectById(templetId);
        //获取流程模型资源
        AsTempletDeModelDO asTempletDeModelDO = asTempletDeModelService.selectTempletResource(curAppTemplet.getLinkProcModelId());
        //流程模型资源存入act_de_model表中
        String generateProcModelId = actDeModelService.insertTempletResource(asTempletDeModelDO,userId);
        //流程模型资源存入as_proc_model
        asProcModelService.insertTempletResource(asTempletDeModelDO,generateProcModelId);

        //2、导入应用资源
        AsTempletApplicationDO asTempletApplicationDO = asTempletApplicationService.selectTempletResource(curAppTemplet.getLinkAppId());
        String generateAppId = applicationService.insertTempletResource(asTempletApplicationDO);

        //3、导入表单模型资源
        //获取表单模型资源
        AsTempletFormModelDO asTempletFormModelDO = asTempletFormModelService.selectTempletResource(curAppTemplet.getLinkFormModelId());
        //表单模型资源存入as_form_model中
        formModelService.insertTempletResource(asTempletFormModelDO,sceneId,userId,generateProcModelId,generateAppId);

        //4、导入模型节点信息
        //获取节点资源列表，注意这里传进去的是流程模型资源的Id
        List<AsTempletProcNodeDO> asTempletProcNodeDOs = asTempletProcNodeService.selectTempletResource(asTempletDeModelDO.getId());
        //节点资源存入as_proc_node中
        procNodeService.insertTempletResource(asTempletProcNodeDOs,generateProcModelId);

        //5、导入表单项权限信息
        //获取表单项权限信息,注意这里传进去的是流程模型资源的Id
        List<AsTempletFormAuthorityDO> asTempletFormAuthorityDOs = asTempletFormAuthorityService.selectTempletResource(asTempletDeModelDO.getId());
        authorityService.insertTempletResource(asTempletFormAuthorityDOs,generateProcModelId);
    }

    /**
     * 选择表单流程模型，发布为新的模板
     * 1、表单模型资源
     * 2、流程模型资源
     * 3、再存节点资源
     * 4、权限资源
     * 5、应用资源
     * 6、最后再存模板
     * @param userId
     * @param formModelId
     */
    public void createTemplet(String userId,
                              String formModelId,
                              String templetName,
                              String iconCls) throws Exception {
        //1、存表单模型资源
        //从as_form_model表中读取
        FormModelDO formModelDO = formModelService.selectFormModelDO(formModelId);
        String templetFormModelId = asTempletFormModelService.insertTempletResource(formModelDO);

        //2、流程模型资源
        ActDeModelDO actDeModelDO = actDeModelService.selectModelDO(formModelDO.getProcModelId());
        String templetProcModelId = asTempletDeModelService.insertTempletResource(actDeModelDO);

        //2.5 sequenceFlow条件、nodeNum数据
        AsProcModelDO asProcModelInfoDO = asProcModelService.selectOne(actDeModelDO.getId());
        asTempletDeModelService.updateProcModelInfo(asProcModelInfoDO);

        //3、存节点资源
        List<ProcNodeDO> nodes = procNodeService.selectNodes(formModelDO.getProcModelId());
        asTempletProcNodeService.insertTempletResource(nodes,templetProcModelId);

        //4、权限资源
        List<FormAuthorityDO> authorityDOs = authorityService.selectList(formModelDO.getProcModelId());
        asTempletFormAuthorityService.insertTempletResource(authorityDOs,templetProcModelId);

        //5、应用资源
        ApplicationDO applicationDO = applicationService.selectOne(formModelDO.getAppId());
        String templetAppId = asTempletApplicationService.insertTempletResource(applicationDO);

        //6、最后再存模板
        AsTempletDO doo = new AsTempletDO.Builder()
                .templetName(templetName)
                .iconCls(iconCls)
                .linkAppId(templetAppId)
                .linkFormModelId(templetFormModelId)
                .linkProcModelId(templetProcModelId)
                .status(Constants.TEMPLET_ENABLE)
                .createTime(new Date())
                .build();
        int flag = asTempletMapper.insert(doo);
        if(flag== Constants.DATABASE_FAILED)
            throw new DatabaseException("发布表单模型失败！");
    }

    /**
     * 返回给控制端模板信息列表
     * @param queryWrapper
     * @return
     */
    public List<AdminTempletVO> selectAdminList(QueryWrapper<AsTempletDO> queryWrapper) {
        List<AsTempletDO> DOs = asTempletMapper.selectList(queryWrapper);
        List<AdminTempletVO> VOs = new ArrayList<>();

        for(int i=0;i<DOs.size();i++){
            AdminTempletVO vo = new AdminTempletVO();
            BeanUtils.copyProperties(DOs.get(i),vo);
            VOs.add(vo);
        }
        return VOs;
    }
}
