package com.asset.service.impl;

import com.asset.dao.GroupMapper;
import com.asset.dao.OAppGroupBindMapper;
import com.asset.entity.GroupCreateInfo;
import com.asset.entity.GroupDeleteInfo;
import com.asset.entity.GroupRenameInfo;
import com.asset.entity.OAppGroupBind;
import com.asset.rec.GroupCreateRec;
import com.asset.rec.GroupIDRec;
import com.asset.rec.GroupRenameRec;
import com.asset.rec.OAppIdRec;
import com.asset.service.OAppGroupService;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAppGroupServiceImpl implements OAppGroupService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    OAppGroupBindMapper oAppGroupBindMapper;

    @Override
    public int createOAppGroup(GroupCreateRec rec) {
        GroupCreateInfo createInfo = new GroupCreateInfo(rec.getOapp_id(),rec.getName(), Constants.GROUP_ENABLED);
        groupMapper.createGroup(createInfo);
        return 0;
    }

    /**
     * 重命名分组
     * @param rec
     * @return
     */
    @Override
    public int renameOAppGroup(GroupRenameRec rec) {
        GroupRenameInfo info = new GroupRenameInfo(rec.getNew_name(),rec.getGroup_id());
        groupMapper.renameGroup(info);
        return 0;
    }

    /**
     * 删除分组
     * @param rec
     * @return
     */
    @Override
    public int deleteOAppGroup(GroupIDRec rec) {
        GroupDeleteInfo info = new GroupDeleteInfo(rec.getGroup_id(),Constants.GROUP_DISABLED);
        groupMapper.deleteGroup(info);
        return 0;
    }

    @Override
    public List<OAppGroupBind> showGroups(OAppIdRec rec) {
        List<OAppGroupBind> groups = oAppGroupBindMapper.selectAll(rec.getOAppID());




        return groups;
    }
}
