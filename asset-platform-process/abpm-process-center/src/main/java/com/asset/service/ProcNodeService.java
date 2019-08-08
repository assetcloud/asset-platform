package com.asset.service;

import com.asset.dao.ProcNodeMapper;
import com.asset.dto.ProcModelDTO;
import com.asset.dto.ProcNodeDTO;
import com.asset.entity.FormModelDO;
import com.asset.entity.ProcNodeDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.UnBindFormModelVO;
import com.asset.utils.Constants;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public int saveProcNode(ProcNodeDO procNodeDO) {
        return procNodeMapper.insert(procNodeDO);
    }

    public void saveProcModelInfo(ProcModelDTO dto) throws DatabaseException {
        for(int i = 0;i<dto.getProc_node_data().size();i++){
            ProcNodeDTO curNodeDTO = dto.getProc_node_data().get(i);
            ProcNodeDO nodeDO =new ProcNodeDO(curNodeDTO,dto.getProc_model_id());
            int a = saveProcNode(nodeDO);
            if(a== Constants.DATABASE_FAILED)
                throw  new DatabaseException("插入数据失败！");
        }
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

    public Integer[] checkIsContainJointSign(String procModelId) {
        return procNodeMapper.getIfJointSign(procModelId);
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
}
