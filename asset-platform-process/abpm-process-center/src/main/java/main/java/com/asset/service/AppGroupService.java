package com.asset.service;

import com.asset.dao.AsGroupMapper;
import com.asset.dao.OAppGroupBindMapper;
import com.asset.entity.*;
import com.asset.rec.GroupRec;
import com.asset.rec.OAppIdRec;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppGroupService {

    @Autowired
    AsGroupMapper groupMapper;

    @Autowired
    OAppGroupBindMapper oAppGroupBindMapper;

    public int createAppGroup(GroupRec rec) {
        AsGroup asGroup = new AsGroupCreate(rec.getApp_id(),
                rec.getGroup_name(),
                Constants.GROUP_ENABLED);
        groupMapper.insertSelective(asGroup);
        return 0;
    }

    /**
     * 重命名分组
     * @param rec
     * @return
     */
    public int renameAppGroup(GroupRec rec) {
        AsGroup info = new AsGroupRename(rec.getGroup_id(),
                rec.getApp_id(),
                rec.getGroup_name());
        groupMapper.insertSelective(info);
        return 0;
    }

    /**
     * 删除分组
     * @param rec
     * @return
     */
    public int deleteAppGroup(GroupRec rec) {
        AsGroup info = new AsGroupDelete(rec.getGroup_id(),
                Constants.GROUP_DISABLED);
        groupMapper.insertSelective(info);
        return 0;
    }

    /**
     * 展示一个应用下所有分组
     * @param rec
     * @return
     */
    public List<OAppGroupBind> showGroups(OAppIdRec rec) {
        List<OAppGroupBind> groups = oAppGroupBindMapper.selectAll(rec.getOAppID());
        return groups;
    }
}
