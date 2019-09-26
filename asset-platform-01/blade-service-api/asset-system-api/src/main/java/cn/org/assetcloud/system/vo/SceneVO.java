package cn.org.assetcloud.system.vo;

import cn.org.assetcloud.system.entity.Scene;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视图实体类
 *
 * @author hjhu
 */
@Data
@ApiModel(value = "SceneVO对象", description = "SceneVO对象")
public class SceneVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String id;

	/**
	 * 角色名
	 */
	private String sceneName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 场景图片
	 */
	private String img;
}
