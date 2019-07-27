package com.asset.controller.system;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.common.model.UserPageParam;
import com.asset.service.*;
import com.asset.utils.Func;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by hjhu on 2019/5/27.
 */

@RestController
public class UserNormalController {

    @Autowired
    private IOrganService organService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private IRoleGroupService roleGroupService;

    @Autowired
    private IUserRoleService userRoleService;

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
            , notes = "（已完成）用户注册操作（用户信息用json传输,场景信息用query形式）;accountName账号;pwd密码;realName真实姓名;admin是否为平台管理员;" +
            "sceneId场景id;sceneName场景名称;remark场景描述;img场景图片;若选择新建场景,sceneId置为null或\"\""
            ,tags = "用户管理", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "admin", value = "是否为总管理员", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "nodeId", value = "节点id的数组", required = true, dataTypeClass = java.lang.String.class)
    })
    @Transactional
    public RespBean userReg(@RequestBody User user
            , @RequestParam(value = "sceneId") String sceneId
            , @RequestParam(value = "sceneName") String sceneName
            , @RequestParam(value = "nodeId") String nodeIds){
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("sceneId", "");
        if (Func.isNull(nodeIds)){
            return RespBean.paramError();
        }
        if (Func.hasEmpty(sceneId)){
            if (userService.userExists(user.getAccountName())){
                return RespBean.error("用户名已被占用，请更换后重试");
            }
            //用户设置并新增
            userService.insertUser(user);
            jsonMap.put("userId", user.getId());
            if (sceneService.getSceneByName(sceneName).size() > 0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("场景名称已被占用，请更换后重试");
            }
            Scene scene = new Scene();
            scene.setSceneName(sceneName);
            if (Func.hasEmpty(nodeIds)){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("至少选择一个组织节点");
            }
            sceneService.addScene4Reg(scene, Func.toStrList(",", nodeIds));
            jsonMap.put("sceneId", scene.getId());
            //新增角色组
            RoleGroup roleGroup = new RoleGroup(SystemConstant.DEFAULT_GROUP_NAME, 0, new Date(), scene.getId());
            roleGroupService.save(roleGroup);
            //在场景中新增两个默认角色
            SceneRole roleAdmin = new SceneRole(scene.getId(), SystemConstant.SCENE_ADMIN_CH, SystemConstant.SCENE_ADMIN);
            SceneRole roleDefault = new SceneRole(scene.getId(), SystemConstant.SCENE_DEFAULT_CH, SystemConstant.SCENE_DEFAULT);
            List<SceneRole> list = new ArrayList<>();
            roleAdmin.setGroupId(roleGroup.getId());
            roleAdmin.setStatus(false);
            roleAdmin.setCreatedTime(new Date());
            roleAdmin.setEnableTime(new Date());
            roleDefault.setGroupId(roleGroup.getId());
            roleDefault.setStatus(false);
            roleDefault.setRoleDefault(1);
            roleDefault.setCreatedTime(new Date());
            roleDefault.setEnableTime(new Date());
            list.add(roleAdmin);
            list.add(roleDefault);
            sceneRoleService.addDefaultRole4Reg(scene.getId(), list);
            //将该用户设置为组织管理员
            sceneService.userSceneBind(scene.getId(), user.getId(), roleAdmin.getId());
        } else {
            jsonMap.put("sceneId", sceneId);
            if (!sceneService.sceneAvailable(sceneId)){
                return RespBean.error("目标场景不存在");
            }
            if (userService.userExists(user.getAccountName())){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return RespBean.error("用户名已被占用，请更换后重试");
            }
            //用户设置并新增
            user.setStage(1);
            user.setStatus(false);
            user.setCreatedTime(new Date());
            userService.insertUser(user);
            jsonMap.put("userId", user.getId());
            //获取该场景下的默认角色
            SceneRole sceneRole = sceneRoleService.getDefaultRole(sceneId);
            //绑定场景与用户
            sceneService.userSceneBind(sceneId, user.getId(), sceneRole.getId());
        }

        return RespBean.data(jsonMap);
    }

//    @RequestMapping(value = "/userAudit/{userId}", method = RequestMethod.POST)
//    @ApiOperation(value = "用户审核", notes = "对注册后的用户进行审核;userId用户id", httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String")
//    })
//    @Transactional
//    public RespBean userAudit(@PathVariable("userId") String userId){
//        Staff staff = new Staff();
//        User user = userService.getUserById(userId);
//        Map<String, Object> map = staffService.addStaff(staff, user);
//        //成为员工
//        int flag = Integer.parseInt(String.valueOf(map.get("flag")));
//        if(flag < 0){
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
//        }
//        //更新用户信息
//        user.setStage(2);
//        user.setStatus(true);
//        user.setStaffId(String.valueOf(map.get("staffId")));
//        flag = userService.updateUser(user);
//        if (flag < 0){
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
//        }
//        return RespBean.ok("用户审核通过");
//    }

    @ApiOperation(value = "获取用户", notes = "根据角色获取用户", tags = "用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/users/{roleId}", method = RequestMethod.GET)
    public List<User> userAudit(@PathVariable("roleId") Long roleId){
        return userService.getUsersByRole(roleId);
    }

    @ApiOperation(value = "注册用户激活", notes = "（已完成）sceneId场景ID;userId用户ID;组织管理员审核时sceneId置null或\"\"",tags = "用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "sceneId", required = true, name = "场景id", dataTypeClass = String.class),
            @ApiImplicitParam(value = "userId", required = true, name = "用户id", dataTypeClass = String.class),
            @ApiImplicitParam(value = "auditType", required = true, name = "审核类型:1-组织审核；2-平台审核", dataTypeClass = Integer.class)
    })
    @PostMapping(value = "user/active")
    @Transactional
    public RespBean userActivate(@RequestParam String sceneId, @RequestParam String userId, @RequestParam Integer auditType) {
        if (userService.getById(userId).getAdmin() == 1){
            //TODO:
            return RespBean.error("注册为平台管理员的接口还没做");
        }
        if (auditType == 2){
            //场景的默认角色有效化
            sceneRoleService.roleAvailable(sceneId);
        }
        userService.enableUser(userId);
        //设置平台级权限
        UserRole userRole = new UserRole();
        userRole.setCreatedTime(new Date());
        userRole.setUid(userId);
        userRole.setStatus(1);
        userRole.setRoleId(SystemConstant.SYSTEM_DEFAULT_USER);
        userRoleService.save(userRole);
        sceneService.enableScene(userId, sceneId);
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

    @ApiOperation(value = "获取不在某一场景下的用户", notes = "已完成", tags = "用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("users/noScene")
    public RespBean getUsersWithoutScene(@RequestParam String accountName, @RequestParam String realName
            , @RequestParam String email, @RequestParam String sceneId){
        if (Func.hasEmpty(sceneId)){
            return RespBean.paramError();
        }
        accountName = accountName == null ? "" : accountName;
        realName = realName == null ? "" : realName;
        email = email == null ? "" : email;
        return RespBean.data(userService.getUsersWithoutScene(accountName, realName, email, sceneId));
    }

    @ApiOperation(value = "获取所有用户（兼模糊搜索）", notes = "已完成", tags = "用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页数据量", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "userPageParam", value = "UserPageParam", required = true, dataTypeClass = UserPageParam.class)
    })
    @GetMapping("user/list")
    public RespBean userList(@RequestParam Integer page, @RequestParam Integer size, @RequestBody UserPageParam userPageParam){
        PageHelper.startPage(page, size);
        return RespBean.data(new PageInfo<>(userService.allUsers(userPageParam)));
    }

    @ApiOperation(value = "管理控制台，添加用户"
            , notes = "（已完成）必填项：accountName用户名;realName真实姓名;pwd密码;phoneNumber手机号;userEmail用户邮箱"
            , tags = "用户", httpMethod = "POST")
    @PostMapping("user/add")
    public RespBean addUser(@RequestBody User user){
        if (Func.hasEmpty(user.getAccountName(), user.getRealName(), user.getPwd(), user.getPhoneNumber()
                , user.getUserEmail(), user.getStatus(), user.getAdmin())){
            return RespBean.paramError();
        }
        return RespBean.status(userService.saveUser(user));
    }

    @ApiOperation(value = "控制台中删除用户", notes = "（已完成，不对外开放）", tags = "用户", httpMethod = "DELETE")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @DeleteMapping("user/delete")
    public RespBean removeUser(@RequestParam String userId){
        if (Func.hasEmpty(userId)){
            return RespBean.paramError();
        }
        return RespBean.status(userService.removeUser(userId));
    }

    @ApiOperation(value = "控制台中，获取单个用户信息", notes = "（已完成，不对外开放）", tags = "用户", httpMethod = "GET")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @GetMapping("user/detail")
    public RespBean getUser(@RequestParam String userId){
        return RespBean.data(userService.getById(userId));
    }

    @ApiOperation(value = "控制台编辑用户信息", notes = "（已完成，不对外开放）accountName", tags = "用户", httpMethod = "PUT")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PutMapping("user/edit")
    public RespBean editUser(@RequestBody User user){
        if (Func.hasEmpty(user.getId())){
            return RespBean.paramError();
        }
        return RespBean.status(userService.updateById(user));
    }
}
