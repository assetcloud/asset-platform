package com.asset.utils;

import com.asset.exception.FormException;
import com.asset.javabean.FormInstBO;

import java.util.ArrayList;
import java.util.List;

/**
 * 对用户ID进行过滤
 * @author YBY
 */
public class UserIdFilter implements Filter{

    @Override
    public ArrayList<FormInstBO> filtrate(List<FormInstBO> formInstBOList) {
        type1:for (int m = 0;m<formInstBOList.size();m++){

            //对user先进行筛选
            FormInstBO formInstBO = formInstBOList.get(m);
            String[] candidateUser = formInstBO.getCandidateUser();
            for(int i = 0;i<candidateUser.length;i++) {
                if(candidateUser[i].equals(formInstBO.getCurUserId()))
                    continue type1;
            }

            //对组织进行筛选
            String[] candidateGroup = formInstBO.getCandidateGroup();
            String curGroup = getCurGroup(formInstBO.getCurUserId(),formInstBO.getSceneId());
            for(int i = 0;i<candidateUser.length;i++) {
                if(candidateGroup[i].equals(curGroup))
                    continue type1;
            }

            //在上面组织和人员中都没有找到与当前登录用户匹配的信息，说明当前登录用户没有权限去处理这个任务节点
            formInstBOList.remove(m);
            m--;
        }

        return (ArrayList<FormInstBO>) formInstBOList;
    }

    @Override
    public FormInstBO filtrate(FormInstBO formInstBO) throws FormException {
        //对user先进行筛选,如果有找到，那就立即返回即可
        String[] candidateUser = formInstBO.getCandidateUser();
        for(int i = 0;i<candidateUser.length;i++) {
            if(candidateUser[i].equals(formInstBO.getCurUserId()))
                return formInstBO;
        }

        //对组织进行筛选
        String[] candidateGroup = formInstBO.getCandidateGroup();
        String curGroup = getCurGroup(formInstBO.getCurUserId(),formInstBO.getSceneId());
        for(int i = 0;i<candidateUser.length;i++) {
            if(candidateGroup[i].equals(curGroup))
                return formInstBO;
        }

        //人员和组织都找了，都找不到，所以当前用户是没有权限的
        throw new FormException("当前用户权限不够，无法获取当前任务节点信息！");
    }

    /**
     * 获取当前用户在当前场景下是属于哪个部门的
     * @param curUserId
     * @param sceneId
     * @return
     */
    private String getCurGroup(String curUserId, String sceneId) {
        return "部门信息请等待与组织架构串通";
    }
}
