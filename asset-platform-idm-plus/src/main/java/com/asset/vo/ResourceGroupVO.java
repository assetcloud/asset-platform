package com.asset.vo;

import com.asset.bean.Resource;
import com.asset.bean.ResourceGroup;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "资源分组VO对象", description = "资源分组VO对象")
public class ResourceGroupVO extends Model<ResourceGroup> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 所属工作空间名称
     */
    private String sceneName;

    /**
     * 分组下资源
     */
    private List<ResourceVO> children = new LinkedList<>();
}
