package com.asset.service.test;

import com.asset.FlowableApplication;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class TempTest {

    @Test
    public void testInComing() {
         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
         RepositoryService repositoryService = processEngine.getRepositoryService();
         RuntimeService runtimeService = processEngine.getRuntimeService();
         TaskService taskService = processEngine.getTaskService();

         String defId = "testExeKey:1:9c267343-af54-11e9-a0fd-2e15a8856301";

        BpmnModel bpmnModel = repositoryService.getBpmnModel(defId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask ) {
                UserTask u = (UserTask) flowElement;
                if(u.getId().equals("u2.2.1"))
                {
                    List<SequenceFlow> incomingFlows = u.getIncomingFlows();
                    for(SequenceFlow s :incomingFlows){
                        System.out.println("source:" + s.getSourceFlowElement().getId());
                    }
                }
            }

        }

    }
}
