package com.asset.bean;

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
    private Boolean status;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Integer getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(Integer authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getAccountPicture() {
        return accountPicture;
    }

    public void setAccountPicture(String accountPicture) {
        this.accountPicture = accountPicture == null ? null : accountPicture.trim();
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
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
        return status;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", staffId='" + staffId + '\'' +
                ", realName='" + realName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", userBirthday=" + userBirthday +
                ", userEmail='" + userEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", certificateType=" + certificateType +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", pwd='" + pwd + '\'' +
                ", authenticationMethod=" + authenticationMethod +
                ", accountPicture='" + accountPicture + '\'' +
                ", admin=" + admin +
                ", stage=" + stage +
                ", userAddress='" + userAddress + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", status=" + status +
                ", userType=" + userType +
                ", disableTime=" + disableTime +
                ", enableTime=" + enableTime +
                ", updatedTime=" + updatedTime +
                ", removeTime=" + removeTime +
                ", createdTime=" + createdTime +
                ", userRemark='" + userRemark + '\'' +
                ", roles=" + roles.toString() +
                '}';
    }
}
