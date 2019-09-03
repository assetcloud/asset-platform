package com.asset.filter;

import com.asset.exception.FormException;
import com.asset.javabean.FormInstBO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 对当前用户选择的工作场景与获取到的任务节点的工作场景进行筛选
 */
public class SceneFilter {

    public ArrayList<FormInstBO> filtrate(List<FormInstBO> formInstBOList,  String selectSceneId) {
        for (int m = 0; m < formInstBOList.size(); m++) {
            FormInstBO formInstBO = formInstBOList.get(m);

            if(!formInstBO.getSceneId().equals(selectSceneId))
            {
                formInstBOList.remove(m);
                m--;
            }

        }

        return (ArrayList<FormInstBO>) formInstBOList;
    }

}
