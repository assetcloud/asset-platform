package com.asset.dao;

import com.asset.entity.GroupCreateInfo;
import com.asset.entity.GroupDeleteInfo;
import com.asset.entity.GroupRenameInfo;
import org.springframework.stereotype.Repository;

/**
 * 用于OApp中分组的创建、修改、删除
 */
@Repository
public interface GroupMapper {

    /**
     * 创建应用中的分组
     * @param createInfo
     */
    void createGroup(GroupCreateInfo createInfo);

    /**
     * 重命名分组
     * @param info
     */
    void renameGroup(GroupRenameInfo info);

    /**
     * 删除分组
     * @param info
     */
    void deleteGroup(GroupDeleteInfo info);
}
