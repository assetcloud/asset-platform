package com.asset.service;

import com.asset.dao.ProcNodeMapper;
import com.asset.dto.ProcModelDTO;
import com.asset.dto.ProcNodeDTO;
import com.asset.entity.AsTempletProcNodeDO;
import com.asset.entity.FormModelDO;
import com.asset.entity.ProcNodeDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.UnBindFormModelVO;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 存储关于一个流程模型中节点的信息，对接as_proc_node
 * @author YBY
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProcNodeService {
    @Autowired
    ProcNodeMapper procNodeMapper;
    @Autowired
    ModelService modelService;
    @Autowired
    FormModelService formModelService;

    public void saveProcNodes(ProcModelDTO dto) throws DatabaseException {
        //对流程模型中每个节点进行单独存储
        for(int i = 0;i<dto.getProc_node_data().size();i++){
            ProcNodeDTO curNodeDTO = dto.getProc_node_data().get(i);
            ProcNodeDO nodeDO =new ProcNodeDO(curNodeDTO,dto.getProc_model_id());
            int flag = Constants.DATABASE_FAILED;
            //判断当前节点信息有没有在数据库中存在
            if(!contain(nodeDO.getProcModelId(),nodeDO.getNodeId()))
                flag = procNodeMapper.insert(nodeDO);
            else
                flag = procNodeMapper.updateSelective(nodeDO);
            if(flag == Constants.DATABASE_FAILED)
                throw  new DatabaseException("插入数据失败！");
        }
        formModelService.bindNodes(dto.getProc_model_id());

    }

    //检查数据库中是否已经存在这么一条记录
    private boolean contain(String procModelId, String nodeId) {
        return procNodeMapper.getNodeDO(procModelId, nodeId)==null?false:true;
    }

    /**
     * 获取流程模型中第一个节点的ID
     * @param procModelId
     */
    public String getFirstNodeId(String procModelId) {

        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);
        Process mainProcess = bpmnModel.getMainProcess();
        Collection<FlowElement> flowElements = mainProcess.getFlowElements();
        String startEventId = null;
        String firstUserTaskId = null;
        boolean ifGetStartEvent = false;
        boolean bigLoop = true;
        outer:while (bigLoop)
        {
            for (FlowElement e : flowElements) {
                if(!ifGetStartEvent &&
                        e instanceof StartEvent) {
                    startEventId = e.getId();
                    ifGetStartEvent = true;
                    continue;
                }
                if(ifGetStartEvent &&
                        e instanceof SequenceFlow &&
                        ((SequenceFlow) e).getSourceRef().equals(startEventId))
                {
                    firstUserTaskId = ((SequenceFlow) e).getTargetRef();
                    bigLoop = false;
                    break outer;
                }
            }
        }

        return firstUserTaskId;
    }

    public Integer getNodeType(String procModelId, String nodeId) {
        return procNodeMapper.getNodeType(procModelId,nodeId);
    }

    public boolean ifJointSign(String procModelId,String nodeId) {
        QueryWrapper<ProcNodeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ProcNodeDO::getProcModelId, procModelId)
                .eq(ProcNodeDO::getNodeId, nodeId);
        ProcNodeDO nodeDO = procNodeMapper.selectOne(queryWrapper);
        if(nodeDO.getIfJointSign() == Constants.AS_NODE_JOINT_DISABLE)
            return false;
        else
            return true;
    }

    public List<ProcNodeDO> getNodeDOList(String procModelId) {
        return procNodeMapper.listNodes(procModelId);
    }

    public ProcNodeDO getNodeDO(String procModelId,String curNodeId) {
        return procNodeMapper.getNodeDO(procModelId,curNodeId);
    }

    public  List<UnBindFormModelVO> getUnbindFormModels() {
        List<FormModelDO> formModels = formModelService.getUnbindFormModels();
        List<UnBindFormModelVO> unBindFormModelVOs = new ArrayList<>();

        for(int i = 0;i<formModels.size();i++){
            UnBindFormModelVO vo = new UnBindFormModelVO();
            BeanUtils.copyProperties(formModels.get(i),vo);

            unBindFormModelVOs.add(vo);
        }
        return unBindFormModelVOs;
    }

    public void insertTempletResource(List<AsTempletProcNodeDO> asTempletProcNodeDOs,String procModelId) throws DatabaseException{
        //对每个节点进行单独存储
        for(int i = 0;i<asTempletProcNodeDOs.size();i++){
            AsTempletProcNodeDO templet = asTempletProcNodeDOs.get(i);
            ProcNodeDO nodeDO = new ProcNodeDO.Builder()
                    .procModelId(procModelId)
                    .build();
            BeanUtils.copyProperties(templet,nodeDO,new String[]{"id"});

            int flag = procNodeMapper.insert(nodeDO);
            if(flag == Constants.DATABASE_FAILED)
                throw  new DatabaseException("插入数据失败！");
        }
    }

    public List<ProcNodeDO> selectNodes(String procModelId) throws Exception{
        return procNodeMapper.selectNodes(procModelId);
    }

    public void updateCandidateGroup(String procModelId, String nodeId, String candiadteGroupIds) {
        ProcNodeDO procNodeDO = new ProcNodeDO.Builder()
                .candidateGroup(candiadteGroupIds)
                .build();
        UpdateWrapper<ProcNodeDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(ProcNodeDO::getProcModelId,procModelId)
                .eq(ProcNodeDO::getNodeId,nodeId);

        int flag = procNodeMapper.update(procNodeDO,updateWrapper);
        if(flag ==Constants.DATABASE_FAILED)
            throw new DatabaseException("更新候选人信息失败！");
    }
}
