package cn.org.assetcloud.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 与数据库交互的表单模型entity
 * @author hjhu
 * @version 1.0
 */

@Data
@ApiModel(value = "FormModelInfo对象", description = "FormModelInfo对象类")
public class FormModelInfo implements Serializable {
	/**
	 * id
	 */
    private Long id;
	/**
	 * 表单资源id
	 */
    @ApiModelProperty(value = "表单模型id")
    private String formModelId;  //数据库表中ID是int类型，自增，这里暂时用不到
	/**
	 * 表单名称
	 */
    @ApiModelProperty(value = "表单名称")
    private String formName;
	/**
	 * 创建时间
	 */
    @ApiModelProperty(value = "创建时间")
    private Timestamp createdTime;
	/**
	 * 创建人
	 */
    @ApiModelProperty(value = "创建人")
    private String createdBy;
	/**
	 * 对应父应用
	 */
    @ApiModelProperty(value = "对应父应用")
    private String applicationId;
	/**
	 * 最后更新时间
	 */
    @ApiModelProperty(value = "最后更新时间")
    private Timestamp lastUpdatedTime;
	/**
	 * 最后更新者
	 */
    @ApiModelProperty(value = "最后更新者")
    private String lastUpdatedBy;
	/**
	 * 模型版本
	 */
    @ApiModelProperty(value = "模型版本")
    int version ;
	/**
	 * 表单源数据
	 */
    @ApiModelProperty(value = "表单源数据")
    private String modelJson;
	/**
	 * 表单模型分组id
	 */
    @ApiModelProperty(value = "表单模型分组id")
    private String groupId;
	/**
	 * 表单模型图标
	 */
    @ApiModelProperty(value = "表单模型图标")
    private String iconCls;
	/**
	 * 表单模型状态
	 */
    @ApiModelProperty(value = "表单模型状态")
    private int status;
	/**
	 * 表单流程关联id
	 */
    @ApiModelProperty(value = "表单流程关联id")
    private String procModelId;
	/**
	 * 表单模型分组名称
	 */
    @ApiModelProperty(value = "表单模型分组名称")
    private String groupName;
}
