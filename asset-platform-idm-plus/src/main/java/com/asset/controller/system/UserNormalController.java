package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Scene;
import com.asset.bean.Staff;
import com.asset.bean.User;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.ISceneService;
import com.asset.service.StaffService;
import com.asset.service.UserService;
import com.asset.utils.Func;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hjhu on 2019/5/27.
 */

@RestController
public class UserNormalController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserNormalController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ISceneService sceneService;

    /**
     * 用户登录
     * @return RespBean
     */
    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

    /**
     * 用户注册
     * @param user
     * @return RespBean
     */
    @RequestMapping(value = "/userReg", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册"
            , notes = "用户注册操作（用户信息用json传输,场景信息用query形式）;accountName账号;pwd密码;realName真实姓名;admin是否为平台管理员;" +
            "sceneId场景id;sceneName场景名称;remark场景描述;img场景图片;若选择新建场景,sceneId置为null或\"\""
            ,tags = "用户管理", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "admin", value = "是否为总管理员", required = true, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "注册成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @Transactional
    public RespBean userReg(@RequestBody User user
            , @ApiParam(value = "sceneId") @RequestParam(value = "sceneId") String sceneId
            , @ApiParam(value = "sceneName") @RequestParam(value = "sceneName") String sceneName
            , @ApiParam(value = "remark") @RequestParam(value = "remark") String remark
            , @ApiParam(value = "img") @RequestParam(value = "img")String img){
        LOGGER.info("用户注册：{}", user.toString());
        if (sceneId == null || sceneId.equals("")){
            //用户选择新建场景
            try {
                Scene scene = new Scene(sceneName, remark, img);
                sceneService.addScene4User(scene, user);
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error(e.getMessage());
            }
        } else {
            //用户选择已有场景注册
            try {
                userService.insertUserWithScene(user, sceneId);
            } catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error(e.getMessage());
            }
        }

        return RespBean.ok(SystemConstant.REGISTER_SUCCESS);
    }

    @RequestMapping(value = "/userAudit/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "用户审核", notes = "对注册后的用户进行审核;userId用户id", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String")
    })
    @Transactional
    public RespBean userAudit(@PathVariable("userId") String userId){
        Staff staff = new Staff();
        User user = userService.getUserById(userId);
        Map<String, Object> map = staffService.addStaff(staff, user);
        //成为员工
        int flag = Integer.parseInt(String.valueOf(map.get("flag")));
        if(flag < 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        //更新用户信息
        user.setStage(2);
        user.setStatus(true);
        user.setStaffId(String.valueOf(map.get("staffId")));
        flag = userService.updateUser(user);
        if (flag < 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok("用户审核通过");
    }

    @ApiOperation(value = "获取用户", notes = "根据角色获取用户", tags = "用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/users/{roleId}", method = RequestMethod.GET)
    public List<User> userAudit(@PathVariable("roleId") Long roleId){
        return userService.getUsersByRole(roleId);
    }

    @ApiOperation(value = "选择工作场景", notes = "选择工作场景", tags = "用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/load_scene", method = RequestMethod.POST)
    public RespBean setScene(@RequestBody Scene scene){
        if (Func.hasEmpty(UserUtils.getCurrentUser().getId(), scene.getId())){
            return RespBean.error("系统错误");
        }
        redisTemplate.opsForValue().set(UserUtils.getCurrentUser().getId(), scene.getId());
        return RespBean.ok("工作场景加载成功");
    }

    @ApiOperation(value = "注册用户激活", notes = "用户审核时调用;sceneId场景ID;userId用户ID;组织管理员审核时sceneId置null或\"\"",tags = "用户", httpMethod = "POST")
    @PostMapping(value = "user/active")
    @Transactional
    public RespBean userActivate(@ApiParam(value = "sceneId", required = true)@RequestParam String sceneId
            , @ApiParam(value = "userId", required = true) @RequestParam String userId) {
        if (Func.isNull(userId)){
            return RespBean.error("用户id不能为空");
        }
        if (sceneId == null || sceneId.equals("")) {
            //组织管理员审核
            User user = new User();
            user.setId(userId);
            user.setStatus(true);
            user.setStage(2);
            try {
                userService.updateUserSelective(user);
            } catch (RuntimeException e){
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("系统错误", e.getMessage());
            }
        } else {
            //要求新建场景的情况
            //平台管理员审核
            User user = new User();
            user.setId(userId);
            user.setStatus(true);
            user.setStage(2);
            Scene scene = new Scene();
            scene.setId(sceneId);
            scene.setStatus(1);
            scene.setIsDeleted(0);
            try {
                userService.updateUserSelective(user);
                sceneService.updateSceneSelective(scene);
            } catch (RuntimeException e){
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("系统错误", e.getMessage());
            }
        }
        return RespBean.ok("用户审核通过");
    }

    //TODO：用户登录后请求创建新场景
    @ApiOperation(value = "用户请求创建新场景", notes = "")
    @PostMapping("createScene")
    public RespBean createScene(@RequestBody Scene scene){

        return null;
    }

    //TODO：用户登录后请求绑定其它场景
    @ApiOperation(value = "用户请求绑定其它场景", notes = "")
    @PostMapping("bindScene")
    public RespBean bindScene(@RequestParam(value = "sceneId") String sceneId
            , @RequestParam(value = "userId") String userId){

        return null;
    }
}
