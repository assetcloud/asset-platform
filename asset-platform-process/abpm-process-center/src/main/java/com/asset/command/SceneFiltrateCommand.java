package com.asset.command;

import com.asset.javabean.AsRunningTask;
import com.asset.javabean.LoginUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SceneFiltrateCommand {

    public void filtrate(List<AsRunningTask> asRunningTasks, LoginUser loginUser) {
        for (int m = 0; m < asRunningTasks.size(); m++) {
            AsRunningTask task = asRunningTasks.get(m);

            if(!task.getSceneId().equals(loginUser.getSceneId()))
            {
                asRunningTasks.remove(m);
                m--;
            }

        }

    }
}
