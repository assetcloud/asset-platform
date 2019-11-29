package com.asset.javabean;

import com.asset.exception.ProcException;
import com.asset.service.ProcNodeService;
import com.asset.utils.Constants;
import lombok.Data;

import java.util.HashMap;
import java.util.Stack;

@Data
public class AsTaskStack extends Stack {
    String exeId;

    public void initExeId(String exeId) {
        if (exeId == null)
            exeId = exeId;
    }

    public void fromEndToStart(String startParallelId) {
        while (!this.empty()) {
            AsTask temp = (AsTask) this.pop();
            //找到对应的开始，跳出while循环
            if (temp.getActId().equals(startParallelId))
                break;
        }
    }

    //判断栈顶元素是不是并行网关-结束
    public boolean isTopParallelEnd(HashMap<String, AsParallelNode> parallelNodes) {
        AsTask curPeekTask = (AsTask) this.peek();
        if (curPeekTask.getActType().equals(Constants.FLOWABLE_ACT_TYPE_PARALLEL)) {
            AsParallelNode asParallelNode = parallelNodes.get(curPeekTask.getActId());
            //检查是结束还是开始，结束的话，要一直出栈，只到遇到对应的开始
            if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_end)
                return true;
            else
                return false;
        } else
            return false;
    }


    //判断栈顶元素是不是并行网关-开始
    public boolean isTopParallelStart(HashMap<String, AsParallelNode> parallelNodes) {
        AsTask curPeekTask = (AsTask) this.peek();
        if (curPeekTask.getActType().equals(Constants.FLOWABLE_ACT_TYPE_PARALLEL)) {
            AsParallelNode asParallelNode = parallelNodes.get(curPeekTask.getActId());
            //检查是结束还是开始，结束的话，要一直出栈，只到遇到对应的开始
            if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_start)
                return true;
            else
                return false;
        } else
            return false;
    }

    //判断栈顶元素是不是经办节点
    public boolean isTopApplyTask(ProcNodeService procNodeService, String procModelId) {
        AsTask curPeekTask = (AsTask) this.peek();
        if (curPeekTask.getActType().equals(Constants.FLOWABLE_ACT_TYPE_USERTASK))
            if (procNodeService.getNodeType(procModelId, curPeekTask.getActId()).equals(Constants.AS_NODE_APPLY))
                return true;
            else
                return false;
        else
            return false;
    }

    public boolean isTopStartEvent() {
        AsTask curPeekTask = (AsTask) this.peek();
        if (curPeekTask.getActType().equals(Constants.FLOWABLE_ACT_TYPE_STARTEVENT))
            return true;
        else
            return false;
    }

    public HashMap<String, Object> selectLastApplyTask(HashMap<String, AsParallelNode> parallelNodes, String procModelId, ProcNodeService procNodeService) {
        HashMap<String, Object> hashMap = new HashMap<>();
        while (!this.empty()) {
            //出栈元素是并行网关，记录或者跳过
            if (this.isTopParallelEnd(parallelNodes)) {
                AsTask curPopTask = (AsTask) this.pop();
                //获取top的end并行网关
                AsParallelNode asParallelNode = parallelNodes.get(curPopTask.getActId());
                //把end到start之间的所有节点全部出栈（包括start）
                this.fromEndToStart(asParallelNode.getPeerNodeId());
            } else if (this.isTopParallelStart(parallelNodes)) {
                AsTask curPopTask = (AsTask) this.pop();
                //获取top的start并行网关
                AsParallelNode asParallelNode = parallelNodes.get(curPopTask.getActId());
                hashMap.put("parallel", asParallelNode);
            }
            //出栈元素是经办节点，记录
            else if (this.isTopApplyTask(procNodeService, procModelId)) {
                AsTask curPopTask = (AsTask) this.pop();
                hashMap.put("rollbackTask", curPopTask);
                break;
            } else if (this.isTopStartEvent()) {
                throw new ProcException("当前流程实例无法完成回滚操作！该审批节点前无经办节点！");
            }
        }

        return hashMap;
    }

    /**
     * 是否可以回滚
     * 这个stack还是先找到第一个经办节点，然后看遇到这个经办节点前遇到的最后一个并行网关是不是和pmain是同级的（区分并行网关的开始、结束，其实对
     * 于结束对于系统来说，是没有用的，因为遇到并行网关-结束，是直接跳过其中的内容的，知道遇到相对的那个并行网关-开始），如果是同级的，那么两个经
     * 办节点就一定是同一个，此时该执行序列属于“可回滚”列表中的一员；
     *
     * @return
     */
    public boolean isRollbackEnable(HashMap<String, AsParallelNode> parallelNodes,
                                    AsTask rollbackTask,
                                    AsParallelNode mainExeParallel,
                                    String procModelId,
                                    ProcNodeService procNodeService) {
        AsParallelNode tempParallel = null;

        //一直遍历，只到没东西可以遍历
        while (!this.empty()) {
            //出栈元素是并行网关，记录或者跳过
            if (this.isTopParallelEnd(parallelNodes)) {
                AsTask curPopTask = (AsTask) this.pop();
                //获取top的end并行网关
                AsParallelNode asParallelNode = parallelNodes.get(curPopTask.getActId());
                //把end到start之间的所有节点全部出栈（包括start）
                this.fromEndToStart(asParallelNode.getPeerNodeId());
            } else if (this.isTopParallelStart(parallelNodes)) {
                AsTask curPopTask = (AsTask) this.pop();
                //获取top的start并行网关
                tempParallel = parallelNodes.get(curPopTask.getActId());
            }
            //出栈元素是经办节点，看之前记录的parallel是不是mainExe中记录的parallel,如果是，接着并且都是同一个task，那么就确定下来这两个execution都是可以被回滚到这个节点的
            else if (this.isTopApplyTask(procNodeService, procModelId)) {
                AsTask curPopTask = (AsTask) this.pop();
                if(tempParallel == mainExeParallel && curPopTask.getTaskId().equals(rollbackTask.getTaskId()))
                    return true;
            }
            //直到遍历到开始节点还没有找到与mainExe相同的那个经办节点，那么说明这个execution和mainExe是独立的，属于不可被回滚的
            else if (this.isTopStartEvent()) {
                return false;
            }
        }

        return false;

    }
}
