package com.asset.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.*;
import com.asset.exception.ProcException;
import com.asset.javabean.FormInstVO;
import com.asset.javabean.RespBean;
import com.asset.dto.*;
import com.asset.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 表单实例的创建、修改，表单项值在流程引擎中的使用
 * @author YBY
 * @time 190610_1040
 * @
 */
@RestController
@Api(tags = "实例创建、查询；任务查询")
public class FormInstController {

    Logger logger = LoggerFactory.getLogger(FormInstController.class);

    @Autowired
    FormInstService formInstService;

    /**
     * 应用下选中一个表单之后，右侧再点击 新增 按钮，此时表单内容需要向服务器请求（即第一个节点所需要展示的表单内容）
     * @return
     */
    @RequestMapping(value = "/form_inst/form_sheet",method = RequestMethod.GET)
    public RespBean showNewFormSheet(@RequestParam(value = "form_model_id") String formModelId){
        JSONObject object = formInstService.showNewFormSheet(formModelId);

        return RespBean.ok("",object);
    }

    /**
     * 用户登录应用界面，填写表单，发起一个新的表单流程
     * 这边根据表单模型需要：
     * 1、创建流程实例
     * 2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
     *
     * 注意：表单的填写内容对流程的执行没有影响！只需要展示即可！对流程执行产生影响的只有“同意”“拒绝”这两个操作！
     * @param dto
     * @return
     */
    @ApiOperation(value = "在用户的应用界面，选择该应用下一个表单，点新增之后，填写完表单，接着点击 提交 发送的请求" ,
            notes = "" , httpMethod = "POST")
    @RequestMapping(value = "/form_inst/save", method = RequestMethod.POST)
    public RespBean commitNewFormInst(@RequestBody FormInstRecCreate dto){
        String[] urls = new String[0];
        try {
            urls = formInstService.commitNewFormInst(dto);
        } catch (DocumentException e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("",urls);
    }

    /**
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     * 这里的全部节点信息还包含了历史的处理信息，这里先不考虑
     * @param userID
     * @param taskType
     * @return
     */
    @RequestMapping(value = "/form_inst/show",method = RequestMethod.GET)
    public RespBean listFormInst(@RequestParam(value = "user_id") String userID,
                              @RequestParam(value = "task_type") Integer taskType)
    {
        List<FormInstVO> formInstVOs = null;
        try {
            formInstVOs = formInstService.listFormInst(userID, taskType);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("", formInstVOs);
    }

    /**
     * 用户登录系统，对审批节点进行处理，点击 同意 或 拒绝
     * 审批节点对当前节点进行操作
     * 1、点“同意” 即代表当前任务节点会向后流转，同时当前填写的表单实例需要加上这个处理意见，给下一个节点过目
     * 2、点“拒绝” 那么就是流程需要回滚到申请节点那个位置（这边的处理逻辑变成 当前节点直接不可用）
     *
     */
    @RequestMapping(value = "/form_inst/approval_node",method = RequestMethod.POST)
    public RespBean approveNode(@RequestBody FormInstRecApprove rec)
    {
        try {
            formInstService.approveNode(rec);
        } catch (ProcException e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("");
    }


    /**
     * 用户登录系统，对经办节点进行处理，填写表单，然后提交
     */
    @RequestMapping(value = "/form_inst/apply_node",method = RequestMethod.POST)
    public RespBean applyNode(@RequestBody FormInstRecHandle rec)
    {
        formInstService.applyNode(rec);
        return RespBean.ok("");
    }

    /**
     * 待阅节点，点击 已阅
     */
    @RequestMapping(value = "/form_inst/pending_node",method = RequestMethod.POST)
    public RespBean pendingNode(@RequestBody FormInstRecReadle rec)
    {
        formInstService.pendingNode(rec);
        return RespBean.ok("");
    }

    /**
     * 返回当前用户有多少的待办任务，待阅任务
     * @param userID
     * @return
     */
    @GetMapping(value = "/form_inst/all/count")
    public RespBean formInstsCount(@RequestParam(value = "user_id")String userID){
        List<TaskCount> taskCounts = null;
        try {
            taskCounts = formInstService.getFormInstsCounts(userID);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("",taskCounts);
    }

    /**
     * 用户点击分享链接，显示当前分配到的节点任务信息
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/form_inst/share_link",method = RequestMethod.GET)
    public RespBean getShareLinkTask(@RequestParam(value = "task_id") String taskId,
                                     @RequestParam(value = "user_id") String userId){
        FormInstVO shareLinkTask = null;
        try {
            shareLinkTask = formInstService.getShareLinkTask(taskId,userId);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("",e.getMessage());
        }
        return RespBean.ok("",shareLinkTask);
    }

}
