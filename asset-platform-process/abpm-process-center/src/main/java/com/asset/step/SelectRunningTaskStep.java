package com.asset.step;

import com.asset.converter.FormConverter;
import com.asset.entity.ProcNodeDO;
import com.asset.exception.ProcException;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.AsSimpleTask;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.FormSheet;
import com.asset.mapper.FlowableMapper;
import com.asset.service.AuthorityService;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.service.ProcNodeService;
import com.asset.utils.Constants;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 相比SelectSimpleTaskStep，可以获取到更多信息
 */
@Component
public class SelectRunningTaskStep {

    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    GetAuthorityStep getAuthorityStep;
    @Autowired
    AuthorityService authorityService;





    /**
     * 增加formSheet信息
     *
     * @param tasks
     * @return
     */
    public List<AsRunningTask> wrapWithIfJointSign(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            if (task.getProcModelId().isEmpty())
                wrapWithProcModelId(task);

            boolean i = procNodeService.ifJointSign(task.getProcModelId(), task.getNodeId());
            if (i)
                task.setIfJointSign(Constants.AS_NODE_JOINT_PARRAL);
            else
                task.setIfJointSign(Constants.AS_NODE_JOINT_DISABLE);
        }

        return tasks;
    }


    public void wrapWithProcModelId(AsRunningTask task) {
        task.setProcModelId(procInstService.getProcModelId(task.getProcInstId()));
    }


    public List<AsRunningTask> wrapWithProcModelId(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithProcModelId(task);
        }
        return tasks;
    }


    public void wrapWithSceneId(AsRunningTask task) {
        if (task.getFormModelId().isEmpty())
            wrapWithFormModelId(task);
        task.setSceneId(formModelService.getSceneId(task.getFormModelId()));
    }


    public List<AsRunningTask> wrapWithSceneId(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithSceneId(task);
        }
        return tasks;
    }


    public void wrapWithCommitter(AsRunningTask task) {
        task.setCommitter(procInstService.getCommitter(task.getTaskId()));
    }


    public List<AsRunningTask> wrapWithCommitter(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithCommitter(task);
        }
        return tasks;
    }

    public void wrapWithFormModelId(AsRunningTask task) {
        if (task.getProcModelId().isEmpty())
            wrapWithProcModelId(task);

        task.setFormModelId(formModelService.getFormModelId(task.getProcModelId()));
    }

    public List<AsRunningTask> wrapWithFormModelId(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithFormModelId(task);
        }
        return tasks;
    }


    public void wrapWithSheet(AsRunningTask task) {
        if (task.getFormModelId().isEmpty())
            wrapWithFormModelId(task);
        task.setFormSheet(formModelService.getModelSheetStr(task.getFormModelId()));
    }

    /**
     * 增加formSheet信息
     *
     * @param tasks
     * @return
     */
    public List<AsRunningTask> wrapWithSheet(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithSheet(task);
        }
        return tasks;
    }


    public void wrapWithFormValue(AsRunningTask task) {
        task.setFormValue(procInstService.getFormInstAllValue(task.getProcInstId()));
    }

    /**
     * 增加FormValue信息
     *
     * @param tasks
     * @return
     */
    public List<AsRunningTask> wrapWithFormValue(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithFormValue(task);
        }
        return tasks;
    }


    public void wrapWithCandidate(AsRunningTask task) {
        if(task.getProcModelId().isEmpty())
            wrapWithProcModelId(task);

        ProcNodeDO nodeDO = procNodeService.getNodeDO(task.getProcModelId(), task.getNodeId());
        String candidateUser = nodeDO.getCandidateUser();
        String candidateGroup = nodeDO.getCandidateGroup();

        if (!StringUtils.isEmpty(candidateUser))
            task.setCandidateUser(candidateUser.split("\\|"));
        if (!StringUtils.isEmpty(candidateGroup))
            task.setCandidateGroup(candidateGroup.split("\\|"));
    }

    /**
     * 增加Candidate信息
     *
     * @param tasks
     * @return
     */
    public List<AsRunningTask> wrapWithCandidate(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithCandidate(task);
        }
        return tasks;
    }

    public void wrapWithFormItemAuthority(AsRunningTask task) {
        if( task.getProcModelId().isEmpty())
            wrapWithProcModelId(task);
        if(task.getFormSheet().isEmpty())
            wrapWithSheet(task);

        String procModelId = task.getProcModelId();
        String modelSheetStr = task.getFormSheet();
        FormSheet formSheet = FormConverter.jsonToEntity(modelSheetStr);

        List<FormItem> items = formSheet.getList();

        for (int i = 0; i <items.size(); i++) {
            //获取当前Authority
            Integer curAuthority = getAuthorityStep.getCurAuthority(procModelId,
                    task.getNodeId(),
                    items.get(i).getKey());
            if(curAuthority==null)
                throw new ProcException("当前流程模型："+procModelId+" 表单项权限信息丢失！");
            //添加权限信息
            authorityService.handleItemAuthority(items.get(i),curAuthority);
        }

        modelSheetStr = FormConverter.entityToJson(formSheet);

        task.setFormSheet(modelSheetStr);
    }

    /**
     * 对其中的表单项权限信息进行读取、设置
     *
     * @param tasks
     * @return
     */
    public List<AsRunningTask> wrapWithFormItemAuthority(List<AsRunningTask> tasks) {
        for (AsRunningTask task : tasks) {
            wrapWithFormItemAuthority(task);
        }
        return tasks;
    }


}
