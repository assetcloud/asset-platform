package com.asset.controller;

import com.asset.service.ChangeProcService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.ui.modeler.rest.app.ModelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TransProcInstance {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelResource.class);

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    ChangeProcService changeProcService;


    /*回滚流程：
        model存储后，调用智能收发器，返回回滚节点 -》taskToStart
        由model的ID得到processIntanceId -》processInstances
        由回滚节点获得需要取消的ACT_ID -》taskToCancel
        调用createChangeActivityStateBuilder()
        循环处理目标流程*/

    /**
     * 现在是回滚到2号节点，需要判断当前这条流程实例处在哪个位置
     *
     * @return
     */
    @RequestMapping("/rollback")
    public void rollbackStrategy() {

        LOGGER.info("编辑的model是 {fdfsdfsdfdsf} ");

        //这里指定回滚到的节点是userEvent2这个节点，这里本来应该是一个参数传进来，指定某一个节点作为回滚的位置
        String rollbackActivityID = "user2Event";

        //遍历所有流程实例，然后根据每一条流程实例的不同情况，对每一条流程实例进行回滚

        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();

        List<ProcessInstance> lists = query.active().list();

        //遍历所有流程实例,这里没有略过那些其实是从最新版本流程创建出来的流程实例
        for (int i = 0; i < lists.size(); i++) {
            ProcessInstance oldProcInstance = lists.get(i);

            ExecutionQuery newEXE = executionQuery.processInstanceId(oldProcInstance.getProcessInstanceId());

            List<Execution> executions = newEXE.list();
            Execution oldProcExe = null;

            for (int j = 0; j < executions.size(); j++) {
                oldProcExe = executions.get(j);
                if (oldProcExe.getActivityId() == null)
                    continue;
                else
                    break;
            }

            String curActivityID = oldProcExe.getActivityId();

            LOGGER.info("当前的ActivitiID是 {} ", curActivityID);

            //接下来需要判断cur和rollback相对位置，然后决定是1、迁移 2、迁移加回滚 3、不可逆，直接走
            //  迁移：     的操作是当前运行实例的PRO_DEF_ID需要更新为最新的版本，这个可以在act_re_prodef表中找，然后找到最新的那个版本
            //获取它的ID，就是最新版本的DEF_ID，这边好像没有直接修改的方法，需要自己进数据库进行修改
            //注意这里传进去的是是运行在旧的流程定义上的processInstance
            //迁移到最新版本
            if(curActivityID.equals("user1Event"))
                changeProcService.updateProcDef(oldProcInstance, oldProcExe);
            //迁移+回滚
            else if(curActivityID.equals("user2Event"))
            {
                changeProcService.updateProcDef(oldProcInstance, oldProcExe);
                runtimeService.createChangeActivityStateBuilder().processInstanceId(oldProcInstance.getProcessInstanceId())
                        .moveExecutionToActivityId(oldProcExe.getId(), rollbackActivityID).changeState();
                LOGGER.info("流程 {} 回滚完成", oldProcInstance.getProcessInstanceId());
            }
            //  直接走：
            else
                continue;

        }


        ProcessInstanceQuery query2 = runtimeService.createProcessInstanceQuery();
        ExecutionQuery executionQuery2 = runtimeService.createExecutionQuery();

        List<ProcessInstance> lists2 = query.active().list();

        for (int i = 0; i < lists.size(); i++) {
            ProcessInstance oldProcInstance = lists.get(i);
        }

    }

}

