/*
 * Copyright (c) 2019.
 * *
 *  * All rights Reserved, Designed By hjhu
 *  * @Title:  ${file_name}
 *  * @Package ${package_name}
 *  * @Description:    ${todo}(用一句话描述该文件做什么)
 *  * @author: HDU
 *  * @date:   ${date} ${time}
 *  * @version V1.0
 *  * @Copyright: ${year} www.assetcloud.org.cn Inc. All rights reserved.
 *
 */

package com.asset.vo;

import com.asset.utils.MyNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "OrganTreeVO对象", description = "OrganTreeVO对象")
public class OrganTreeVO implements MyNode {

    @ApiModelProperty(value = "部门id", name = "id")
    private String id;

    @ApiModelProperty(value = "组织名称", name = "unitName")
    private String unitName;

    @ApiModelProperty(value = "父节点id", name = "parentId")
    private String parentId;

//    @ApiModelProperty(value = "是否被删除", name = "isDeleted")
//    private Integer isDeleted;
//
//    @ApiModelProperty(value = "部门状态", name = "status")
//    private Integer status;

    private List<MyNode> children = new ArrayList<>();

//    public List<MyNode> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<MyNode> children) {
//        this.children = children;
//    }
}
