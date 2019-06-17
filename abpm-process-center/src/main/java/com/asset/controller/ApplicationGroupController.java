package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.OAppGroupBind;
import com.asset.rec.GroupCreateRec;
import com.asset.rec.GroupIDRec;
import com.asset.rec.GroupRenameRec;
import com.asset.rec.OAppIdRec;
import com.asset.service.impl.OAppGroupServiceImpl;
import com.asset.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * OApp即仿照氚云中的应用 结构，与flowable中的APP作区分
 * 这边只管理OApp中的分组内容（这里只会得到一个OApp的ID），OApp的创建、修改有haijie那里完成
 * 这里完成分组的创建、重命名、删除、根据应用返回所有分组信息
 */
@Controller
@Api(value = "OApp分组管理接口")
public class ApplicationGroupController {

    @Autowired
    OAppGroupServiceImpl oAppGroupService;

    /**
     * 创建分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/create" ,method = RequestMethod.POST)
    public int createOAppGroup(@RequestBody GroupCreateRec rec)
    {
        oAppGroupService.createOAppGroup(rec);
        return 1;
    }

    /**
     * 重命名分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/rename" ,method = RequestMethod.PUT)
    public int renameGroup(@RequestBody GroupRenameRec rec)
    {
        oAppGroupService.renameOAppGroup(rec);
        return 1;
    }

    /**
     * 删除分组
     * @param rec
     * @return
     */
    @RequestMapping(value = "/group/delete" ,method = RequestMethod.PUT)
    public int deleteGroup(@RequestBody GroupIDRec rec)
    {
        oAppGroupService.deleteOAppGroup(rec);
        return 1;
    }

    /**
     * 根据应用ID返回所有分组信息
     * @param rec 包含应用ID的实体类
     * @return
     */
    @RequestMapping(value = "/group/selectall" ,method = RequestMethod.PUT)
    public String showGroups(@RequestBody OAppIdRec rec)
    {
        ArrayList<OAppGroupBind> groups = (ArrayList<OAppGroupBind>) oAppGroupService.showGroups(rec);

        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", Constants.CODE_SUCCESS);
        map.put("list", groups);

        Object json = JSONObject.toJSON(map);
        return json.toString();
    }


}
