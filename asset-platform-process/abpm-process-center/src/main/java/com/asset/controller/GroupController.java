package com.asset.controller;

import com.asset.dao.AsGroupMapper;
import com.asset.entity.AsGroup;
import com.asset.entity.AsGroupCreate;
import com.asset.entity.AsGroupDelete;
import com.asset.entity.AsGroupEdit;
import com.asset.javabean.RespBean;
import com.asset.rec.GroupRec;
import com.asset.service.AppGroupService;
import com.asset.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Api(value = "App分组管理接口")
public class GroupController {

    @Autowired
    AppGroupService groupService;
    @Autowired
    AsGroupMapper groupMapper;

    /**
     * 创建分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/create" ,method = RequestMethod.POST)
    public RespBean createOAppGroup(@RequestBody GroupRec rec)
    {
        AsGroup asGroup = new AsGroupCreate(
                rec.getApp_id(),
                rec.getGroup_name(),
                Constants.GROUP_ENABLED);
        int i =groupMapper.insertSelective(asGroup);
        if(i==Constants.DATABASE_FAILED)
            return RespBean.error("创建失败！");

        return RespBean.ok("");
    }

    /**
     * 修改分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/edit" ,method = RequestMethod.PUT)
    public RespBean updateGroup(@RequestBody GroupRec rec)
    {
        AsGroup info = new AsGroupEdit(rec.getGroup_id(),
                rec.getApp_id(),
                rec.getGroup_name());
        int i = groupMapper.updateGroup(info);
        if(i==Constants.DATABASE_FAILED)
            return RespBean.error("修改失败！");
        return RespBean.ok("");
    }

    /**
     * 删除分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/delete" ,method = RequestMethod.DELETE)
    public RespBean deleteGroup(@RequestBody GroupRec rec)
    {
        AsGroup info = new AsGroupDelete(rec.getGroup_id(),
                Constants.GROUP_DISABLED);
        int  i = groupMapper.deleteGroup(info);
        if(i==Constants.DATABASE_FAILED)
            return RespBean.error("删除失败！");
        return RespBean.ok("");
    }

    /**
     * 根据应用ID返回所有分组信息
     * @param appID 应用ID
     * @return
     */
    @RequestMapping(value = "/group/all" ,method = RequestMethod.GET)
    public RespBean showGroups(@RequestParam(value = "app_id") String appID)
    {
        ArrayList<AsGroup> asGroups = (ArrayList<AsGroup>) groupMapper.selectAll(appID);
        for(int i = 0;i<asGroups.size();i++)
        {
            if(asGroups.get(i).getStatus()== Constants.GROUP_DISABLED)
            {
                asGroups.remove(i);
                i--;
            }
        }
        if (asGroups.size()==0)
            return RespBean.ok("分组为空！");
        return RespBean.ok("", asGroups);
    }


}
