package com.asset.step;

import com.asset.javabean.AsExecution;
import com.asset.javabean.AsTask;
import com.asset.javabean.AsTaskStack;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ConstructStacksStep {
    //其中
    public HashMap<String, Object> constructStacks(HashMap<String, AsExecution> allExes, String curTaskId) {
        List<AsTaskStack> stacks = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        int mainIndex = 0;
        int temp = 0;

        for (String key : allExes.keySet()) {
            AsTaskStack stack = new AsTaskStack();
            ArrayList<AsTask> executions = allExes.get(key).getExecution();
            for (AsTask task : executions) {
                if (task.getTaskId().equals(curTaskId))
                    mainIndex = temp;
                stack.push(task);
                stack.initExeId(allExes.get(key).getExeId());
            }

            stacks.add(stack);
            temp++;
        }

        hashMap.put("stacks", stacks);
        hashMap.put("main", mainIndex);

        return hashMap;
    }
}
