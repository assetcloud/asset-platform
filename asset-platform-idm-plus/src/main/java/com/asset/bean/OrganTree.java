package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "组织结构表")
@TableName("as_organ_tree")
public class OrganTree implements Serializable {

    @TableField("id")
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "部门id", name = "id", required = true)
    private String id;

//    @NotBlank(message = "组织名称不能为空")
//    @NotNull(message = "组织名称不能为空")
    @ApiModelProperty(value = "组织名称", name = "unitName", required = true)
    private String unitName;
    private String unitNameEn;
//    @NotNull(message = "父节点不能为空")
//    @NotBlank(message = "父节点不能为空")
    @ApiModelProperty(value = "父节点id", name = "parentId", required = true)
    private String parentId;
    private Integer organizationForm;
    private Integer unitType;
    private String parentNodeName;
    private Integer chargeSectionId;
    private String chargeSectionName;
    private String localFinancialCode;
    private String localFinancialName;
    private String officeAdministrationCode;
    private String officeAdministrationName;
    private String administrationDivisionCode;
    private String administrationDivisionName;
    private Integer budgetCode;
    private String collegeCode;
    private String organizationCode;
    private Integer unitTypeCode;
    private Integer unitTypeName;
    private Integer unitBasicProperty;
    private String socialCreditCode;
    private String budgetUnitName;
    private String budgetUnitCode;
    private Integer budgetManagementLevel;
    private String firmName;
    private String firmCode;
    private String telephoneNumber;
    private String phoneNumber;
    private String faxNumber;
    private String emailAddress;
    private String province;
    private String city;
    private String county;
    private String streetAddress;
    private String postalCode;
    private Integer fundSupplyModeName;
    private Integer fundSupplyModeCode;
    private Integer administrationLevelName;
    private Integer administrationLevelType;
    private Integer departmentCategoryName;
    private Integer businessDepartmentClassificationCode;
    private String principal;
    private String contact;
    private String corporateRepresentative;
    private Integer economicType;
    private String financialAffiliation;
    @TableField("financial_unit")
    private Boolean financialUnit;
    @TableField("vertical_unit")
    private Boolean verticalUnit;
    @TableField("virtual_unit")
    private Boolean virtualUnit;
    private Integer accountingSystem;
    private String corporateTag;
    private String maintainerMark;
    private String supplierMark;
    private String manufactureMark;
    private String assetDisposalAgencyTag;
    private String stateAssetManagementCompany;
    private Integer authorizedNumber;
    private String administrationAuthority;
    private Date enableTime;
    private Date disableTime;
    private Date sealTime;
    private String unitRemark;
    private String assetDownloadStatus;
    private String latitudeAndLongitude;
    private Date createdTime;
    @TableLogic
    private Integer isDeleted;
    private Integer status;
    private Integer sort;
    private String tenantCode;
    //表示该属性不为数据库表字段，但又是必须使用的
    @TableField(exist = false)
    private List<OrganTree> children = new ArrayList<>();

    //编辑场景中组织时，用于判断该节点是否在场景中
    @TableField(exist = false)
    private Integer checked = 0;
}
