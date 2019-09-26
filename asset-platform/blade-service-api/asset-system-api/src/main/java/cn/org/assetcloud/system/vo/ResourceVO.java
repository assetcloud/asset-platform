package cn.org.assetcloud.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "ResourceVO对象", description = "ResourceVO对象")
public class ResourceVO implements RNode {

    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 父资源名称
     */
    private String parentName;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源图标
     */
    private String iconCls;

    /**
     * 场景id
     */
    private String sceneId;

    /**
     * 所属工作空间
     */
    private String sceneName;

    /**
     * 分类
     */
    private String categoryName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 资源分组名称
     */
    private String groupName;

    /**
     * 子资源
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RNode> children;

    public List<RNode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }
}
