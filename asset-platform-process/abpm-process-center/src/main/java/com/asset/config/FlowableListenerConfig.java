package com.asset.config;

import com.asset.listener.EventEndListener;
import com.google.common.collect.Maps;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 对flowable全局监听器进行配置（添加自定义全局监听器）
 */
@Configuration
public class FlowableListenerConfig {

    // flowable监听级别参照 {@link FlowableEngineEventType} org.flowable.common.engine.api.delegate.event
    /**
     * 任务节点前置监听
     */
    private static final String FLOWABLE_TASK_NODE_REAR_LISTENER = "TASK_COMPLETED";

    // 自己建立监听类实现FlowableEventListener接口
    /**
     * 任务节点前置监听
     */
    private final EventEndListener taskBeforeListener;


    @Autowired
    public FlowableListenerConfig(EventEndListener taskBeforeListener) {
        this.taskBeforeListener = taskBeforeListener;
    }

    /**
     * 将自定义监听器纳入flowable监听
     * @author: Lu Yang
     * @date: 2019/5/4 21:05
     * @param
     * @return org.flowable.spring.boot.EngineConfigurationConfigurer<org.flowable.spring.SpringProcessEngineConfiguration>
     */
    @Bean
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> globalListenerConfigurer () {
        return engineConfiguration -> {
            engineConfiguration.setTypedEventListeners(this.customFlowableListeners());
        };
    }

    /**
     * @author: Lu Yang
     * @date: 2019/5/4 20:58
     * @param
     * @return java.util.Map<java.lang.String,java.util.List<org.flowable.common.engine.api.delegate.event.FlowableEventListener>>
     */
    private Map<String, List<FlowableEventListener>> customFlowableListeners () {
        Map<String, List<FlowableEventListener>> listenerMap = Maps.newHashMap();
        listenerMap.put(FLOWABLE_TASK_NODE_REAR_LISTENER, new ArrayList<>(Collections.singletonList(getTaskBeforeListener())));
        return listenerMap;
    }

    public EventEndListener getTaskBeforeListener() {
        return taskBeforeListener;
    }
}
