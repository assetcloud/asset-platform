package com.asset.service;

import com.asset.entity.OAppGroupBind;
import com.asset.rec.GroupCreateRec;
import com.asset.rec.GroupIDRec;
import com.asset.rec.GroupRenameRec;
import com.asset.rec.OAppIdRec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OAppGroupService {
    int createOAppGroup(GroupCreateRec rec);

    int renameOAppGroup(GroupRenameRec rec);

    int deleteOAppGroup(GroupIDRec rec);

    List<OAppGroupBind> showGroups(OAppIdRec rec);
}
