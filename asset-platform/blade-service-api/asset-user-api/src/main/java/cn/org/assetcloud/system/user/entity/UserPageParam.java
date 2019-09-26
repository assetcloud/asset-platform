package cn.org.assetcloud.system.user.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户表分页实体类")
public class UserPageParam {

    private String accountName = "";

    private String realName = "";

    private String userEmail = "";

    private String phoneNumber = "";

    private Integer status = 1;
}
