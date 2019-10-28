package com.asset.bean;

import com.asset.vo.RoleVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@TableName("as_user")
@ApiModel(value = "用户实体类")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String staffId;

    @TableField("role_id")
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

    public User (String accountName, String userEmail){
        this.accountName = accountName;
        this.userEmail = userEmail;
    }

    public User() {
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return status == 1;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status == 1;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status == 1;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}
