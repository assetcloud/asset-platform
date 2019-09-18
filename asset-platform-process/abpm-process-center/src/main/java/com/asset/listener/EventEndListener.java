package com.asset.listener;

import com.asset.service.FlowableService;
import com.asset.service.FormInstService;
import com.asset.service.ProcInstService;
import com.asset.utils.Constants;
import com.asset.utils.ProcUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEngineEventImpl;
import org.flowable.engine.FlowableEngineAgenda;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 全局监听器，判断流程是不是运行到了最后一个EndEvent
 * @author: YBY
 * @create: 2019-05-04 20:51
 **/
@Component
public class EventEndListener implements FlowableEventListener {

    @Autowired
    FlowableService flowableService;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FormInstService formInstService;

    @Override
    public void onEvent(FlowableEvent event) {
        // 当前节点任务实体,
        TaskEntity taskEntity = (TaskEntity) ((FlowableEntityEventImpl) event).getEntity();
        String taskId = taskEntity.getId();
        String curActId = flowableService.getNodeId(taskId);
        String procDefId = ProcUtils.getProcessDefinitionByTaskId(taskEntity.getId()).getId();
        String procInstId = flowableService.getProcInstId(taskId);

        //对当前执行完的task进行判断，看是否是审批节点任务，同时被拒绝了的话就设定流程状态
        if(formInstService.isRejected(taskId))
        {
            //如果是 拒绝审批，不完成接下来的节点（这里需要在获取流程实例那里进行处理，只获取处于运行状态的流程）
            procInstService.updateProcStatus(procInstId,Constants.PROC_INST_REJECTED);
//            ProcUtils.completeInst(procInstId);
            return;
        }
        else{
            Process process = ProcessDefinitionUtil.getProcess(procDefId);
            //遍历整个process,找到endEventId是什么，与当前taskId作对比
            List<FlowElement> flowElements = (List<FlowElement>) process.getFlowElements();
            for (FlowElement flowElement : flowElements) {
                if (flowElement instanceof SequenceFlow) {
                    SequenceFlow flow = (SequenceFlow) flowElement;
                    FlowElement sourceFlowElement = flow.getSourceFlowElement();
                    FlowElement targetFlowElement = flow.getTargetFlowElement();
                    //如果当前边的下一个节点是endEvent，那么获取当前边
                    if(targetFlowElement instanceof EndEvent && sourceFlowElement.getId().equals(curActId))
                    {
                        int flag = procInstService.updateProcStatus(procInstId,Constants.PROC_INST_FINISHED);
                    }
                }
            }
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
