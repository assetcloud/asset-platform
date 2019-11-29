package com.asset.command;

import com.asset.exception.SizeNullException;
import com.asset.filter.SceneFilter;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.AsSimpleTask;
import com.asset.javabean.FormInstVO;
import com.asset.javabean.LoginUser;
import com.asset.step.SelectRunningTaskStep;
import com.asset.step.SelectSimpleTaskStep;
import javafx.scene.Scene;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ShowTaskCommand {
    @Autowired
    SelectSimpleTaskStep selectSimpleTaskStep;
    @Autowired
    SelectRunningTaskStep selectRunningTaskStep;
    @Autowired
    CandidateFiltrateCommand candidateFiltrateCommand;
    @Autowired
    JointCheckCommand jointCheckCommand;
    @Autowired
    SceneFiltrateCommand sceneFiltrateCommand;
    @Autowired
    VOTranslateCommand voTranslateCommand;

    public ArrayList<FormInstVO> ShowTaskCommand(int taskType,
                                String userId,
                                String sceneId,
                                String sectionId){
        LoginUser loginUser = LoginUser.builder()
                .userId(userId)
                .sceneId(sceneId)
                .sectionId(sectionId).build();

        //找到指定类型的任务节点
        List<AsSimpleTask> asSimpleTasks = selectSimpleTaskStep.selectSimpleTasksByTaskType(taskType);
        if (asSimpleTasks.size() == 0)
            throw new SizeNullException("表单实例为空");

        List<AsRunningTask> asRunningTasks = new ArrayList<>();
        for(int i = 0;i<asSimpleTasks.size();i++){
            AsRunningTask task = new AsRunningTask();
            BeanUtils.copyProperties(asSimpleTasks.get(i),task);
            asRunningTasks.add(task);
        }

        //SimpleTask需要从数据库中加载更多信息,sheet和formValue最后加载
        selectRunningTaskStep.wrapWithProcModelId(asRunningTasks);
        selectRunningTaskStep.wrapWithFormModelId(asRunningTasks);
        selectRunningTaskStep.wrapWithCandidate(asRunningTasks);
        selectRunningTaskStep.wrapWithIfJointSign(asRunningTasks);
        selectRunningTaskStep.wrapWithCommitter(asRunningTasks);
        selectRunningTaskStep.wrapWithSceneId(asRunningTasks);


        //接着对candidate、会签节点、选择的工作场景进行筛选
        candidateFiltrateCommand.filtrate(asRunningTasks,loginUser);
        jointCheckCommand.filtrate(asRunningTasks,loginUser);
        sceneFiltrateCommand.filtrate(asRunningTasks,loginUser);


        //加载formValue和sheet信息
        selectRunningTaskStep.wrapWithFormValue(asRunningTasks);
        selectRunningTaskStep.wrapWithSheet(asRunningTasks);

        //返回给前台的对象，需要加上一定的修饰
        ArrayList<FormInstVO> formInstVOS = voTranslateCommand.toVO(asRunningTasks, loginUser);
        return formInstVOS;
    }
}
