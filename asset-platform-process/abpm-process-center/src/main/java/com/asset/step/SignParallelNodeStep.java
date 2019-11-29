package com.asset.step;

import com.asset.javabean.AsParallelNode;
import com.asset.utils.Constants;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ParallelGateway;
import org.flowable.bpmn.model.SequenceFlow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Component
public class SignParallelNodeStep {
    /**
     * 标记并行网关
     *
     * @param flowElements
     */
    public HashMap<String, AsParallelNode> signParallelNode(ArrayList<FlowElement> flowElements) {
        //记录当前模型中出现的并行网关，然后对其中的并行网关进行标记
        HashMap<String, AsParallelNode> parallelNodes = new HashMap<>();
        Stack<String> stack = new Stack<>();

        int i = 0;
        //先从头遍历，构建多条执行序列
        for (; i < flowElements.size(); i++) {
            FlowElement flowElement = flowElements.get(i);
            if (flowElement instanceof ParallelGateway) {
                ParallelGateway gateway = (ParallelGateway) flowElement;
                List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
                List<SequenceFlow> incomingFlows = gateway.getIncomingFlows();

                //出度为1，那么是end
                if (outgoingFlows.size() == 1) {
                    String curPeerStartId = stack.pop();
                    parallelNodes.put(gateway.getId(), new AsParallelNode.Builder()
                            .id(gateway.getId())
                            .type(Constants.AS_NODE_PARALLEL_end)
                            .peerNodeId(curPeerStartId)
                            .outNums(1)
                            .build());
                    AsParallelNode curPeerStartNode = parallelNodes.get(curPeerStartId);
                    curPeerStartNode.setPeerNodeId(gateway.getId());
                } else {
                    stack.push(gateway.getId());
                    parallelNodes.put(gateway.getId(), new AsParallelNode(gateway.getId(), Constants.AS_NODE_PARALLEL_start, outgoingFlows.size()));
                }
            }
        }

        return parallelNodes;
    }

}
