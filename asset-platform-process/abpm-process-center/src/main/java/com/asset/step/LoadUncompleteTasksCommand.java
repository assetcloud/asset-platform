package com.asset.step;

import com.asset.service.ProcInstService;
import com.asset.utils.ProcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadUncompleteTasksCommand {
    @Autowired
    ProcInstService procInstService;

    /**
     * 加载新的没有
     * @return
     */
    public String[] loadUncompleteTasks(String procInstId,String formModelId){
        // 回滚后，该实例下会生成新的任务节点信息（在act_hi_actinst表中可以找到，相同inst_id且END_time为空的条目）
        // 新的任务节点信息将其封装在as_form_inst表中，这里先不用saveRollbackTask()，saveUnCompleteTask()应该就可以满足要求了
        // saveRollbackTask(procInstID, dto.getForm_model_id());
        procInstService.saveUnCompleteTask(
                procInstId,
                formModelId);
        String[] taskIDs = ProcUtils.getTaskIDs(procInstId);
        return taskIDs;
    }
}
