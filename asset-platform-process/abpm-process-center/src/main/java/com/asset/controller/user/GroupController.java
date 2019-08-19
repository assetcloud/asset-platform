package com.asset.controller.user;

import com.asset.dao.AsGroupMapper;
import com.asset.entity.*;
import com.asset.entity.GroupDelete;
import com.asset.entity.Group;
import com.asset.javabean.RespBean;
import com.asset.dto.GroupRec;
import com.asset.service.AppGroupService;
import com.asset.utils.Constants;
import com.asset.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 这边只管理App中的分组内容（这里只会得到一个App的ID），App的创建、修改有haijie那里完成
 * 这里完成分组的创建、重命名、删除,返回应用下所有分组
 * @author YBY
 * @time 190619 1049
 * @version 1.1_190619 1049
 */
@RestController
@Api(tags = "终端：分组管理")
public class GroupController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);


    @Autowired
    AppGroupService groupService;
    @Autowired
    AsGroupMapper groupMapper;

    /**
     * 创建分组
     * @param rec
     * @return
     */
    @ApiOperation(value = "新建分组")
    @RequestMapping(value = "/group/save" ,method = RequestMethod.POST)
    public R createOAppGroup(@RequestBody GroupRec rec)
    {
        Group group = new GroupCreate(
                rec.getApp_id(),
                rec.getGroup_name(),
                Constants.GROUP_ENABLED);
        int i =groupMapper.insertSelective(group);
        if(i==Constants.DATABASE_FAILED)
            return R.fail("创建失败！");

        return R.success("创建成功");
    }

    /**
     * 修改分组信息
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/save" ,method = RequestMethod.PATCH)
    public R updateGroup(@RequestBody GroupRec rec)
    {
        Group info = new GroupEdit(rec.getGroup_id(),
                rec.getApp_id(),
                rec.getGroup_name());
        int i = groupMapper.updateGroup(info);
        if(i==Constants.DATABASE_FAILED)
            return R.fail("修改失败！");
        return R.success("修改成功");
    }

    /**
     * 删除分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/save" ,method = RequestMethod.DELETE)
    public R deleteGroup(@RequestBody GroupRec rec)
    {
        Group info = new GroupDelete(rec.getGroup_id(),
                Constants.GROUP_DISABLED);
        int  i = groupMapper.deleteGroup(info);
        if(i==Constants.DATABASE_FAILED)
            return R.fail("删除失败！");
        return R.success("删除成功");
    }

    /**
     * 根据应用ID返回所有分组信息
     * @param appID 应用ID
     * @return
     */
    @RequestMapping(value = "/group/all" ,method = RequestMethod.GET)
    public R<ArrayList<Group>> showGroups(@RequestParam(value = "app_id") String appID)
    {
        ArrayList<Group> groups = (ArrayList<Group>) groupMapper.selectAll(appID);
        for(int i = 0; i< groups.size(); i++)
        {
            if(groups.get(i).getStatus()== Constants.GROUP_DISABLED)
            {
                groups.remove(i);
                i--;
            }
        }
        if (groups.size()==0)
            return R.fail("分组为空！");
        return R.data(groups);
    }

}
