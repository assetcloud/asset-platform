package com.asset.controller;

import com.asset.base.BaseController;
import com.asset.entity.AsProcInst;
import com.asset.entity.User;
import com.asset.form.FormJson;
import com.asset.javabean.RespBean;
import com.asset.rec.RegisterRec;
import com.asset.service.FormInstService;
import com.asset.service.UserService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author hjhu
 */
@RestController
public class UserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    FormInstService formInstService;

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("/userList")
    public String procDefIndex() {
        return "pages/identity/user_list";
    }

    /**
     * 查询用户数据
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    @RequestMapping("/queryUsers")
    @ResponseBody
    public PageGrids queryUsers(@RequestParam("page") Integer pageNum,
                                @RequestParam("rows") Integer pageSize,
                                @RequestParam String id,
                                @RequestParam String displayName) {

        if(pageNum==null ||pageNum<=0){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = userService.getUsers(pageNum, pageSize, id, displayName);
        return pageGrids;
    }

    /**
     * 添加用户
     * @param displayName
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestParam String displayName,
                          @RequestParam String phoneNumber,
                          @RequestParam String email) {
        User user = new User();
        user.setId("2");
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setAdmin(true);
        user.setStatus(true);
        user.setPwd("123456");
        user.setPhoneNumber(phoneNumber);
        user.setCreatedTime(new Date());
        int flag = userService.addUser(user);
        if (flag > 0){
            return "pages/identity/user_list";
        }else {
            return "index";
        }
    }

    /**
     * 前端发起注册，生成一个固定表单样式，然后发起一个流程
     * @param rec
     * @return
     */
    @RequestMapping(value = "/user/register",method = RequestMethod.GET)
    public RespBean register(@RequestBody RegisterRec rec){
        String registerProcModelID =  "088c97ce-9bdd-11e9-b8c8-ba15de269d3a";
        ProcessInstance procInst = userService.createRegisterTask(registerProcModelID);
        String[] taskIDs = formInstService.getTaskIDs(procInst.getProcessInstanceId());

        //注册页面绑定的表单模型ID，需要在系统创建之后自动生成
                String registerFormModelID = "0af6e7db-98d5-11e9-993a-0215444863d4";
        userService.createRegisterFormInst(rec.getModel_json(),
                rec.getResiter_value(),
                procInst.getProcessInstanceId(),
                rec.getEditor(),
                registerFormModelID,
                taskIDs[0]);

        //3、持久化流程实例
        AsProcInst asProcInst = new AsProcInst(
                procInst.getProcessInstanceId(),
                registerProcModelID,
                "",
                "",
                rec.getEditor());
        userService.insertProcInst(asProcInst);


        formInstService.completeCurTask(taskIDs[0]);
        formInstService.saveUnCompleteTask(
                procInst.getProcessInstanceId(),
                registerFormModelID,
                rec.getResiter_value());

        return RespBean.ok("");
    }


}
