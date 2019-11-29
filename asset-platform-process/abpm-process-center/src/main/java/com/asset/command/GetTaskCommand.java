package com.asset.command;

import com.asset.entity.AsTaskDO;
import com.asset.exception.FormException;
import com.asset.exception.ProcException;
import com.asset.javabean.*;
import com.asset.mapper.AsTaskMapper;
import com.asset.step.SelectRunningTaskStep;
import com.asset.step.SelectSimpleTaskStep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取某个任务的接口
 */
@Component
public class GetTaskCommand {

    @Autowired
    SelectSimpleTaskStep selectSimpleTaskStep;
    @Autowired
    SelectRunningTaskStep selectRunningTaskStep;
    @Autowired
    JointFiltrateCommand jointFiltrateCommand;
    @Autowired
    SceneFiltrateCommand sceneFiltrateCommand;
    @Autowired
    VOTranslateCommand voTranslateCommand;
    @Autowired
    CandidateFiltrateCommand candidateFiltrateCommand;

    @Autowired
    AsTaskMapper asTaskMapper;

    /**
     * 入口方法
     * 点击外链之后,显示当前待执行节点的表单sheet
     *
     * @param taskId
     */
    public AsTaskVO getShareLinkTask(String taskId,
                                     String userId,
                                     String sectionId) throws ProcException, FormException {
        LoginUser loginUser = LoginUser.builder()
                .userId(userId)
                .sectionId(sectionId).build();

        AsSimpleTask simpleTask = selectSimpleTaskStep.selectSimpleTaskByTaskId(taskId);

        if(simpleTask == null)
            return null;
        ArrayList<AsRunningTask> asRunningTasks = new ArrayList<>();
        AsRunningTask asRunningTask = new AsRunningTask();
        BeanUtils.copyProperties(simpleTask,asRunningTask);
        asRunningTasks.add(asRunningTask);

        //SimpleTask需要从数据库中加载更多信息,sheet和formValue最后加载
        selectRunningTaskStep.wrapWithProcModelId(asRunningTask);
        selectRunningTaskStep.wrapWithFormModelId(asRunningTask);
        selectRunningTaskStep.wrapWithCandidate(asRunningTask);
        selectRunningTaskStep.wrapWithIfJointSign(asRunningTask);
        selectRunningTaskStep.wrapWithCommitter(asRunningTask);
        selectRunningTaskStep.wrapWithSceneId(asRunningTask);

        //接着对candidate、会签节点进行筛选
        candidateFiltrateCommand.filtrate(asRunningTasks,loginUser);
        jointFiltrateCommand.filtrate(asRunningTasks,loginUser);

        if(asRunningTasks.size()==0)
            return null;

        AsTaskDO taskDO = asTaskMapper.selectById(asRunningTask.getTaskId());
        selectRunningTaskStep.wrapWithFormValue2(taskDO);
        selectRunningTaskStep.wrapWithSheet2(taskDO);

        List<AsTaskDO> asTaskDOs = new ArrayList<>();
        asTaskDOs.add(taskDO);

        //返回给前台的对象，需要加上一定的修饰
        ArrayList<AsTaskVO> asTaskVOS = voTranslateCommand.toVO(asTaskDOs, loginUser);

        return asTaskVOS.get(0);
    }


}
