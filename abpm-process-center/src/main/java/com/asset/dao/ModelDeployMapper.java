package com.asset.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专门用于表单以及流程模型与部署之间的数据绑定
 * @author YBY
 */
@Repository
public interface ModelDeployMapper {


    /**
     * 在去act_re_deployment表中查找KEY值为``app_def_key``内容的那一行，获取那一行的ID值，
     * 就是找到对应的``proc_deploy_id``（这个名自己取的，表示流程部署的ID）
     * @param appDefKey
     * @return
     */
    List<String> getDeployID(String appDefKey);

}
