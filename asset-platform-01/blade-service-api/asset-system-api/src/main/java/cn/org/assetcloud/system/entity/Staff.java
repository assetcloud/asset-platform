package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("as_staff")
@ApiModel(value = "Staff对象", description = "Staff对象类")
public class Staff implements Serializable {

    private String id;

    private String userId;

    private Date graduateTime;

    private String graduateSchool;

    private String authorizedStrength;

    private Date staffBirthday;

    private String partyGroup;

    private String fixedTelephone;

    private String overseasRelation;

    private String nativePlace;

    private String emergencyContact;

    private String contactPhoneNumber;

    private String staffNation;

    private Date staffHiredate;

    private Integer removeTag;

    private String phoneNumber;

    private String staffMajor;

    private Integer gender;

    private String staffName;

    private String userCreatedTag;

    private String postalCode;

    private String staffPhoto;

    private String certificateNumber;

    private String certificateType;

    private String staffTitle;

    private String staffDuty;

    private String status;

    private String assetStatus;

    private String staffReligion;

    private String academicCredential;

    private String familyAddress;
}
