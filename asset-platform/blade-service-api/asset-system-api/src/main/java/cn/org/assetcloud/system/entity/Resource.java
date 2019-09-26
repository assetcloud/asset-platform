package cn.org.assetcloud.system.entity;

import cn.org.assetcloud.system.common.SystemConstant;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("as_resource")
@ApiModel(value = "Resource对象", description = "Resource对象")
public class Resource extends Model<Resource> implements Cloneable{

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @NotNull
    @NotBlank
    private String code;

    @NotNull
    @NotBlank
    private String name;

    @TableField("icon_cls")
    private String iconCls;

    @NotNull
    @NotBlank
    private String path;

    private Integer level;

    private Integer sort;

    @NotNull
    @NotBlank
    private Integer category;

    private String remark;
    /**
     * 表单分组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 场景id
     */
    @TableField("scene_id")
    private String sceneId;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField("add_time")
    private Date addTime;

    @TableField("remove_time")
    private Date removeTime;

    @TableField(exist = false)
    private List<FormGroup> group;

    @TableField(exist = false)
    private List<Resource> children = new ArrayList<>();

    public Resource(String code, String name, String iconCls, String path, Integer level, Integer sort, Integer category) {
        this.code = code;
        this.name = name;
        this.iconCls = iconCls;
        this.path = path;
        this.level = level;
        this.sort = sort;
        this.category = category;
    }

    public Resource() {
    }

	public Resource(Application application, String sceneId) {
    	this.code = SystemConstant.CODE_APP;
    	this.name = application.getApplicationName();
    	this.path = application.getId();
    	this.iconCls = application.getIconCls();
    	this.level = 0;
    	this.parentId = 0L;
		this.category = 1;
		this.sceneId = sceneId;
	}

	public Resource(FormModelInfo formModelInfo, Long groupId, Resource parentResource, String sceneId) {
		this.id = formModelInfo.getId();
		this.category = 2;
		this.parentId = parentResource.getParentId();
		this.level = 0;
		this.path = formModelInfo.getFormModelId();
		this.name = formModelInfo.getFormName();
		this.iconCls = formModelInfo.getIconCls();
		this.sceneId = sceneId;
		this.code = SystemConstant.CODE_FORM;
		this.groupId = groupId;
	}

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
