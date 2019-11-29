package com.asset.command;

import com.asset.exception.FormException;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.FormInstBO;
import com.asset.javabean.LoginUser;
import com.asset.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选的命令
 */
@Component
public class CandidateFiltrateCommand {

    public void filtrate(List<AsRunningTask> asRunningTasks, LoginUser loginUser) {
        type1:
        for (int m = 0; m < asRunningTasks.size(); m++) {
            AsRunningTask curTask = asRunningTasks.get(m);


            //对user先进行筛选
            String[] candidateUser = curTask.getCandidateUser();
            if(candidateUser!=null)
                for (int i = 0; i < candidateUser.length; i++) {
                    if(candidateUser[i].equals(Constants.CANDIDATE_INITIATOR)
                            && curTask.getCommitter().equals(loginUser.getUserId())){
                        //找到当前实例的committer，和当前用户进行判断，如果相等，那么说明当前用户就是当初的那个发起者，可以获取当前任务
                        continue type1;
                    }
                    if (candidateUser[i].equals(loginUser.getUserId()))
                        continue type1;
                }

            //既然不是候选用户组里的，现在看看当前用户是不是候选组织里的
            String[] candidateGroup = curTask.getCandidateGroup();
            String committerSectionId = getCommitterSectionId(curTask.getCommitter(),loginUser.getSceneId());

            if (candidateGroup != null)
                for (int i = 0; i < candidateGroup.length; i++) {
                    //如果候选组是“当前部门”，需要动态去获取当前遍历到的流程的申请人是谁，然后去组织架构模块获取当前申请人所处的部门Id
                    if(candidateGroup[i].equals(Constants.CANDIDATE_GROUP_CUR_SECTION))
                    {
                        if(committerSectionId.equals(loginUser.getSectionId()))
                            continue type1;
                    }
                    else {
                        if (candidateGroup[i].equals(loginUser.getSectionId()))
                            continue type1;

                    }
                }

            //在上面组织和人员中都没有找到与当前登录用户匹配的信息，说明当前登录用户没有权限去处理这个任务节点
            asRunningTasks.remove(m);
            m--;
        }

    }

    //这里应该是向组织架构模块发起请求
    private String getCommitterSectionId(String committer,String sceneId) {
        return "hahahah";
    }

    public FormInstBO shareLinkFiltrate(FormInstBO formInstBO,
                                        String sectionId) throws FormException {
        //对user先进行筛选,如果有找到，那就立即返回即可
        String[] candidateUser = formInstBO.getCandidateUser();
        if (candidateUser != null)
            for (int i = 0; i < candidateUser.length; i++) {
                if (candidateUser[i].equals(formInstBO.getCurUserId()))
                    return formInstBO;
            }

        //对组织进行筛选
        String[] candidateGroup = formInstBO.getCandidateGroup();
        if (candidateGroup == null)
            throw new FormException("当前用户权限不够，无法获取当前任务节点信息！");


        for (int i = 0; i < candidateGroup.length; i++) {
            if (candidateGroup[i].equals(sectionId))
                return formInstBO;
        }

        //人员和组织都找了，都找不到，所以当前用户是没有权限的
        throw new FormException("当前用户权限不够，无法获取当前任务节点信息！");
    }
}
