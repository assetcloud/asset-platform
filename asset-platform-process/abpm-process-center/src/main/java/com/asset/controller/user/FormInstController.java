package com.asset.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.*;
import com.asset.exception.ProcException;
import com.asset.javabean.FormInstVO;
import com.asset.dto.*;
import com.asset.service.*;
import com.asset.utils.R;
import io.swagger.annotations.*;
import org.dom4j.DocumentException;
import org.flowable.common.engine.api.FlowableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 表单实例的创建、修改，表单项值在流程引擎中的使用
 *
 * @author YBY
 * @time 190610_1040
 * @
 */
@RestController
@Api(tags = "终端：实例管理")
public class FormInstController {

    Logger logger = LoggerFactory.getLogger(FormInstController.class);

    @Autowired
    FormInstService formInstService;

    /**
     * 应用下选中一个表单之后，右侧再点击 新增 按钮，此时表单内容需要向服务器请求（即第一个节点所需要展示的表单内容）
     *
     * @return
     */
    @ApiOperation(value = "请求第一个节点要填写的表单内容", notes = "在用户的应用界面，选择该应用下一个表单，点新增之后，展示给用户的第一个要填写的任务节点表单信息", httpMethod = "GET")
    @RequestMapping(value = "/form_inst/form_sheet", method = RequestMethod.GET)
    public R<JSONObject> showNewFormSheet(@RequestParam(value = "form_model_id") String formModelId) {
        JSONObject object = formInstService.showNewFormSheet(formModelId);

        return R.data(object);
    }

    /**
     * 用户登录应用界面，填写表单，发起一个新的表单流程
     * 这边根据表单模型需要：
     * 1、创建流程实例
     * 2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
     * <p>
     * 注意：表单的填写内容对流程的执行没有影响！只需要展示即可！对流程执行产生影响的只有“同意”“拒绝”这两个操作！
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "发起实例", notes = "实例发起时，用户填写完表单，接着点击“提交”发送的请求", httpMethod = "POST")
    @RequestMapping(value = "/form_inst/save", method = RequestMethod.POST)
    public R<String[]> commitNewFormInst(@ApiParam(value = "实例发起实体类", required = true) @RequestBody FormInstRecCreate dto) {
        String[] urls;
        try {
            urls = formInstService.commitNewFormInst(dto);
        } catch (DocumentException e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        } catch (FlowableException e) {
            e.printStackTrace();
            return R.fail(e.getMessage() + "  请检查流程模型元素是否有误！");
        }
        return R.data(urls);
    }

    /**
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     * 这里的全部节点信息还包含了历史的处理信息，这里先不考虑
     *
     * @param userID
     * @param taskType
     * @return
     */
    @ApiOperation(value = "获取分配到的任务信息", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/form_inst/show", method = RequestMethod.GET)
    public R listFormInst(
            @ApiParam(value = "当前用户Id", required = true)
            @RequestParam(value = "user_id") String userID,
            @ApiParam(value = "当前要查看的任务节点类型,0——待办任务,1——待阅任务,2——全部任务", required = true, allowableValues = "0,1,2")
            @RequestParam(value = "task_type") Integer taskType,
            @ApiParam(value = "当前用户登录时选择的工作场景Id", required = true)
            @RequestParam(value = "scene_id") String sceneId,
            @ApiParam(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
            @RequestParam(value = "section_id") String sectionId) {
        List<FormInstVO> formInstVOs = null;
        try {
            formInstVOs = formInstService.listFormInst(userID, taskType, sceneId, sectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.data( formInstVOs);
    }

    /**
     * 返回当前用户有多少的待办任务，待阅任务
     *
     * @param userID
     * @return
     */
    @ApiOperation(value = "获取分配到的任务数目", notes = "在首页展示当前当前用户分配到的任务数目", httpMethod = "GET")
    @GetMapping(value = "/form_inst/all/count")
    public R<List<TaskCount>> formInstsCount(
            @ApiParam(value = "当前用户Id", required = true)
            @RequestParam(value = "user_id") String userID,
            @ApiParam(value = "当前用户登录时选择的工作场景Id", required = true)
            @RequestParam(value = "scene_id") String sceneId,
            @ApiParam(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
            @RequestParam(value = "section_id") String sectionId) {
        List<TaskCount> taskCounts = null;
        try {
            taskCounts = formInstService.getFormInstsCounts(userID, sceneId, sectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.data(taskCounts);
    }

    /**
     * 用户登录系统，对审批节点进行处理，点击 同意 或 拒绝
     * 同时还可以对表单内容进行填写
     * 审批节点对当前节点进行操作
     * 1、点“同意” 即代表当前任务节点会向后流转，同时当前填写的表单实例需要加上这个处理意见，给下一个节点过目
     * 2、点“拒绝” 那么就是流程需要回滚到申请节点那个位置（这边的处理逻辑变成 当前节点直接不可用）
     */
    @ApiOperation(value = "处理待审阅任务", notes = "对待审阅任务进行处理", httpMethod = "POST")
    @RequestMapping(value = "/form_inst/approval_node", method = RequestMethod.POST)
    public R<String[]> approveNode(
            @ApiParam(value = "对当前待审阅任务进行处理的实体类", required = true)
            @RequestBody FormInstRecApprove rec) {

        String[] urls;
        try {
            urls = formInstService.approveNode(rec);
        } catch (ProcException e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        } catch (FlowableException e) {
            e.printStackTrace();
            return R.fail(e.getMessage() + "  请检查流程模型元素是否有误！");
        }

        return R.data(urls);
    }


    /**
     * 用户登录系统，对经办节点进行处理，填写表单，然后提交
     */
    @ApiOperation(value = "处理经办任务", notes = "对经办任务进行处理，需要填写表单", httpMethod = "POST")
    @RequestMapping(value = "/form_inst/apply_node", method = RequestMethod.POST)
    public R applyNode(
            @ApiParam(value = "对当前经办任务进行处理的实体类", required = true)
            @RequestBody FormInstRecHandle rec) {
        String[] urls;
        try {
            urls = formInstService.applyNode(rec);
        } catch (FlowableException e) {
            e.printStackTrace();
            return R.fail(e.getMessage() + "  请检查流程模型元素是否有误！");
        }
        return R.data(urls);
    }

    /**
     * 待阅节点，点击 已阅
     */
    @ApiOperation(value = "处理待阅任务", notes = "对待阅任务进行处理", httpMethod = "POST")
    @RequestMapping(value = "/form_inst/pending_node", method = RequestMethod.POST)
    public R pendingNode(
            @ApiParam(value = "对当前待阅任务进行处理的实体类", required = true)
            @RequestBody FormInstRecReadle rec) {
        String[] urls;
        try {
            urls = formInstService.pendingNode(rec);
        } catch (FlowableException e) {
            e.printStackTrace();
            return R.fail(e.getMessage() + "  请检查流程模型元素是否有误！");
        }
        return R.data(urls);
    }

    /**
     * 用户点击分享链接，显示当前分配到的节点任务信息
     *
     * @param taskId
     * @return
     */
    @ApiOperation(value = "外链任务处理", notes = "用户点击外链，自动跳转到相应的任务处理页面，对分配到自己的任务进行快速处理", httpMethod = "POST")
    @RequestMapping(value = "/form_inst/share_link", method = RequestMethod.GET)
    public R getShareLinkTask(
            @ApiParam(value = "外链中包含的TaskId", required = true)
            @RequestParam(value = "task_id") String taskId,
            @ApiParam(value = "当前用户Id", required = true)
            @RequestParam(value = "user_id") String userId,
//                                     @RequestParam(value = "scene_id") String sceneId,   //这里点击外链，不需要对工作场景进行筛选
            @ApiParam(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
            @RequestParam(value = "section_id") String sectionId) {
        FormInstVO shareLinkTask = null;
        try {
            shareLinkTask = formInstService.getShareLinkTask(taskId, userId, sectionId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.data(shareLinkTask);
    }


}
