package com.asset.config;


import com.asset.exception.DatabaseException;
import com.asset.service.FormModelService;
import com.asset.utils.Constants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 系统初始化的时候加载注册审批流程,注意是不需要事先在数据库中创建流程模型的！已经存在本地了！！
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
//        FormModelService formModelService = context.getBean(FormModelService.class);
//        if(!formModelService.checkRegisterFormContain(Constants.REGISTER_FORM_ID,context)){
//            try {
//                formModelService.initRegisterFormModel(Constants.REGISTER_FORM_ID);
//            } catch (DatabaseException e) {
//                e.printStackTrace();
//            }
//        }
//        if(!formModelService.checkRegisterFormContain(Constants.SCENE_SELECT_FORM_ID,context)){
//            try {
//                formModelService.initSceneSelectFormModel(Constants.SCENE_SELECT_FORM_ID);
//            } catch (DatabaseException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
