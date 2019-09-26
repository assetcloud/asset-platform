package cn.org.assetcloud.system.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("as_user")
@ApiModel(value = "用户实体类")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String staffId;

    private Integer roleId;
    @ApiModelProperty(value = "真实姓名")
    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    private String realName;

    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "用户账号")
    private String accountName;

    private String nickname;
    private Integer gender;
    private Date userBirthday;

    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;

    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "用户手机号码")
    private String phoneNumber;

    private Integer certificateType;
    private String certificateNumber;
    private String pwd;
    private Integer authenticationMethod;
    private String accountPicture;

    @NotNull(message = "不能为空")
    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "是否是平台管理员")
    private Integer admin;
    private Integer stage;
    private String userAddress;
    private String userPhoto;

    @TableLogic(value = "1", delval = "0")
    private Integer status;
    private Integer userType;
    private Date disableTime;
    private Date enableTime;
    private Date updatedTime;
    private Date removeTime;
    private Date createdTime;
    private String userRemark;

    @TableField(exist = false)
    private List<Role> roles;

	@TableField(exist = false)
	private List<SceneRole> sceneRoles;

    public User(String accountName, String userEmail){
        this.accountName = accountName;
        this.userEmail = userEmail;
    }

    public User() {
    }
}
