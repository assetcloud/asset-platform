package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "ApiDataSource对象", description = "ApiDataSource对象类")
@TableName("as_api_data_source")
public class ApiDataSource implements Serializable {

	private Integer id;

	private String urlName;

	private String sceneId;

	private String apiUrl;

	private String param;

	private String methodType;

	private String httpHeader;

	private String httpBody;

	private Date addTime;

	@TableLogic(delval = "1", value = "0")
	private Integer isDeleted;

	private Integer contentType;
}
