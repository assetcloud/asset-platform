package com.asset.service;

import com.asset.dao.AsGroupMapper;
import com.asset.entity.AsGroup;
import com.asset.entity.AsGroupCreate;
import com.asset.entity.AsGroupDelete;
import com.asset.entity.AsGroupEdit;
import com.asset.rec.GroupRec;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppGroupService {

    @Autowired
    AsGroupMapper groupMapper;


//     public int createAppGroup(GroupRec rec) {
//        AsGroup asGroup = new AsGroupCreate(
//                rec.getApp_id(),
//                rec.getGroup_name(),
//                Constants.GROUP_ENABLED);
//        groupMapper.insertSelective(asGroup);
//        return 0;
//    }

//    /**
//     * 修改分组
//     * @param rec
//     * @return
//     */
//    public int updateAppGroup(GroupRec rec) {
//        AsGroup info = new AsGroupEdit(rec.getGroup_id(),
//                rec.getApp_id(),
//                rec.getGroup_name());
//        groupMapper.updateByPrimaryKey(info);
//        return 0;
//    }
//
//    /**
//     * 删除分组
//     * @param rec
//     * @return
//     */
//    public int deleteAppGroup(GroupRec rec) {
//        AsGroup info = new AsGroupDelete(rec.getGroup_id(),
//                Constants.GROUP_DISABLED);
//        groupMapper.updateByPrimaryKey(info);
//        return 0;
//    }


}
