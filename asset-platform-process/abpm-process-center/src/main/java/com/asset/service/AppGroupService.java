package com.asset.service;

import com.asset.dao.AsGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppGroupService {

    @Autowired
    AsGroupMapper groupMapper;


//     public int createAppGroup(GroupRec dto) {
//        Group asGroup = new GroupCreate(
//                dto.getApp_id(),
//                dto.getGroup_name(),
//                Constants.GROUP_ENABLED);
//        groupMapper.insertSelective(asGroup);
//        return 0;
//    }

//    /**
//     * 修改分组
//     * @param dto
//     * @return
//     */
//    public int updateAppGroup(GroupRec dto) {
//        Group info = new GroupEdit(dto.getGroup_id(),
//                dto.getApp_id(),
//                dto.getGroup_name());
//        groupMapper.updateByPrimaryKey(info);
//        return 0;
//    }
//
//    /**
//     * 删除分组
//     * @param dto
//     * @return
//     */
//    public int deleteAppGroup(GroupRec dto) {
//        Group info = new GroupDelete(dto.getGroup_id(),
//                Constants.GROUP_DISABLED);
//        groupMapper.updateByPrimaryKey(info);
//        return 0;
//    }


}