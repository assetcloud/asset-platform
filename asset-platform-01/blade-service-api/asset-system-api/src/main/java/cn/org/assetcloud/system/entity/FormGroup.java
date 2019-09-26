package cn.org.assetcloud.system.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "FormGroup对象", description = "FormGroup对象类")
public class FormGroup {
	/**
	 * 表单分组id
	 */
    private String groupId;
	/**
	 * 分组名称
	 */
    private String groupName;
	/**
	 * 资源数据
	 */
    private List<Resource> children;
}
