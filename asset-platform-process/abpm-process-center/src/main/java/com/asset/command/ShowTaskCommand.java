package com.asset.command;

import com.asset.entity.AsTaskDO;
import com.asset.exception.SizeNullException;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.AsSimpleTask;
import com.asset.javabean.AsTaskVO;
import com.asset.javabean.LoginUser;
import com.asset.mapper.AsTaskMapper;
import com.asset.step.SelectRunningTaskStep;
import com.asset.step.SelectSimpleTaskStep;
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
    JointFiltrateCommand jointFiltrateCommand;
    @Autowired
    SceneFiltrateCommand sceneFiltrateCommand;
    @Autowired
    VOTranslateCommand voTranslateCommand;

    @Autowired
    AsTaskMapper asTaskMapper;

    /**
     * 获取用户分配的任务信息
     * @param taskType
     * @param userId
     * @param sceneId
     * @param sectionId
     * @return
     */
    public ArrayList<AsTaskVO> showTasks(int taskType,
                                         String userId,
                                         String sceneId,
                                         String sectionId,
                                         String curSectionUsers){
        LoginUser loginUser = LoginUser.builder()
                .userId(userId)
                .sceneId(sceneId)
                .sectionId(sectionId).build();

        //找到指定类型的任务节点
        List<AsSimpleTask> asSimpleTasks = selectSimpleTaskStep.selectSimpleTasksByTaskType(taskType);
        if (asSimpleTasks.size() == 0)
            return new ArrayList<>();

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
        candidateFiltrateCommand.filtrate(asRunningTasks,loginUser,curSectionUsers);
        jointFiltrateCommand.filtrate(asRunningTasks,loginUser);
        sceneFiltrateCommand.filtrate(asRunningTasks,loginUser);


        if(asRunningTasks.size()==0)
            return new ArrayList<>();

        List<AsTaskDO> asTaskDOs = new ArrayList<>();

        for (AsRunningTask asRunningTask : asRunningTasks) {
            AsTaskDO taskDO = asTaskMapper.selectById(asRunningTask.getTaskId());
            selectRunningTaskStep.wrapWithFormValue2(taskDO);
            selectRunningTaskStep.wrapWithSheet2(taskDO);
            asTaskDOs.add(taskDO);
        }


        //返回给前台的对象，需要加上一定的修饰
        ArrayList<AsTaskVO> asTaskVOS = voTranslateCommand.toVO(asTaskDOs, loginUser);

        return asTaskVOS;
    }
}
