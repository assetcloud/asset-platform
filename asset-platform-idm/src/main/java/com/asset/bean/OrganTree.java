package com.asset.bean;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

@ApiModel(value = "组织结构表")
public class OrganTree {

    private String id;
    private String unitName;
    private String unitNameEn;
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
    private Boolean isFinancialUnit;
    private Boolean isVerticalUnit;
    private Boolean isVirtualUnit;
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
    private Integer isDeleted;
    private Integer status;
    private Integer sort;
    private String tenantCode;
    private List<OrganTree> children;

    public List<OrganTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrganTree> children) {
        this.children = children;
    }

    public Boolean getFinancialUnit() {
        return isFinancialUnit;
    }

    public void setFinancialUnit(Boolean financialUnit) {
        isFinancialUnit = financialUnit;
    }

    public Boolean getVerticalUnit() {
        return isVerticalUnit;
    }

    public void setVerticalUnit(Boolean verticalUnit) {
        isVerticalUnit = verticalUnit;
    }

    public Boolean getVirtualUnit() {
        return isVirtualUnit;
    }

    public void setVirtualUnit(Boolean virtualUnit) {
        isVirtualUnit = virtualUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    public String getUnitNameEn() {
        return unitNameEn;
    }

    public void setUnitNameEn(String unitNameEn) {
        this.unitNameEn = unitNameEn == null ? null : unitNameEn.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getOrganizationForm() {
        return organizationForm;
    }

    public void setOrganizationForm(Integer organizationForm) {
        this.organizationForm = organizationForm;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getParentNodeName() {
        return parentNodeName;
    }

    public void setParentNodeName(String parentNodeName) {
        this.parentNodeName = parentNodeName == null ? null : parentNodeName.trim();
    }

    public Integer getChargeSectionId() {
        return chargeSectionId;
    }

    public void setChargeSectionId(Integer chargeSectionId) {
        this.chargeSectionId = chargeSectionId;
    }

    public String getChargeSectionName() {
        return chargeSectionName;
    }

    public void setChargeSectionName(String chargeSectionName) {
        this.chargeSectionName = chargeSectionName == null ? null : chargeSectionName.trim();
    }

    public String getLocalFinancialCode() {
        return localFinancialCode;
    }

    public void setLocalFinancialCode(String localFinancialCode) {
        this.localFinancialCode = localFinancialCode == null ? null : localFinancialCode.trim();
    }

    public String getLocalFinancialName() {
        return localFinancialName;
    }

    public void setLocalFinancialName(String localFinancialName) {
        this.localFinancialName = localFinancialName == null ? null : localFinancialName.trim();
    }

    public String getOfficeAdministrationCode() {
        return officeAdministrationCode;
    }

    public void setOfficeAdministrationCode(String officeAdministrationCode) {
        this.officeAdministrationCode = officeAdministrationCode == null ? null : officeAdministrationCode.trim();
    }

    public String getOfficeAdministrationName() {
        return officeAdministrationName;
    }

    public void setOfficeAdministrationName(String officeAdministrationName) {
        this.officeAdministrationName = officeAdministrationName == null ? null : officeAdministrationName.trim();
    }

    public String getAdministrationDivisionCode() {
        return administrationDivisionCode;
    }

    public void setAdministrationDivisionCode(String administrationDivisionCode) {
        this.administrationDivisionCode = administrationDivisionCode == null ? null : administrationDivisionCode.trim();
    }

    public String getAdministrationDivisionName() {
        return administrationDivisionName;
    }

    public void setAdministrationDivisionName(String administrationDivisionName) {
        this.administrationDivisionName = administrationDivisionName == null ? null : administrationDivisionName.trim();
    }

    public Integer getBudgetCode() {
        return budgetCode;
    }

    public void setBudgetCode(Integer budgetCode) {
        this.budgetCode = budgetCode;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode == null ? null : collegeCode.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    public Integer getUnitTypeCode() {
        return unitTypeCode;
    }

    public void setUnitTypeCode(Integer unitTypeCode) {
        this.unitTypeCode = unitTypeCode;
    }

    public Integer getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(Integer unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public Integer getUnitBasicProperty() {
        return unitBasicProperty;
    }

    public void setUnitBasicProperty(Integer unitBasicProperty) {
        this.unitBasicProperty = unitBasicProperty;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode == null ? null : socialCreditCode.trim();
    }

    public String getBudgetUnitName() {
        return budgetUnitName;
    }

    public void setBudgetUnitName(String budgetUnitName) {
        this.budgetUnitName = budgetUnitName == null ? null : budgetUnitName.trim();
    }

    public String getBudgetUnitCode() {
        return budgetUnitCode;
    }

    public void setBudgetUnitCode(String budgetUnitCode) {
        this.budgetUnitCode = budgetUnitCode == null ? null : budgetUnitCode.trim();
    }

    public Integer getBudgetManagementLevel() {
        return budgetManagementLevel;
    }

    public void setBudgetManagementLevel(Integer budgetManagementLevel) {
        this.budgetManagementLevel = budgetManagementLevel;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName == null ? null : firmName.trim();
    }

    public String getFirmCode() {
        return firmCode;
    }

    public void setFirmCode(String firmCode) {
        this.firmCode = firmCode == null ? null : firmCode.trim();
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber == null ? null : telephoneNumber.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber == null ? null : faxNumber.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress == null ? null : streetAddress.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public Integer getFundSupplyModeName() {
        return fundSupplyModeName;
    }

    public void setFundSupplyModeName(Integer fundSupplyModeName) {
        this.fundSupplyModeName = fundSupplyModeName;
    }

    public Integer getFundSupplyModeCode() {
        return fundSupplyModeCode;
    }

    public void setFundSupplyModeCode(Integer fundSupplyModeCode) {
        this.fundSupplyModeCode = fundSupplyModeCode;
    }

    public Integer getAdministrationLevelName() {
        return administrationLevelName;
    }

    public void setAdministrationLevelName(Integer administrationLevelName) {
        this.administrationLevelName = administrationLevelName;
    }

    public Integer getAdministrationLevelType() {
        return administrationLevelType;
    }

    public void setAdministrationLevelType(Integer administrationLevelType) {
        this.administrationLevelType = administrationLevelType;
    }

    public Integer getDepartmentCategoryName() {
        return departmentCategoryName;
    }

    public void setDepartmentCategoryName(Integer departmentCategoryName) {
        this.departmentCategoryName = departmentCategoryName;
    }

    public Integer getBusinessDepartmentClassificationCode() {
        return businessDepartmentClassificationCode;
    }

    public void setBusinessDepartmentClassificationCode(Integer businessDepartmentClassificationCode) {
        this.businessDepartmentClassificationCode = businessDepartmentClassificationCode;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getCorporateRepresentative() {
        return corporateRepresentative;
    }

    public void setCorporateRepresentative(String corporateRepresentative) {
        this.corporateRepresentative = corporateRepresentative == null ? null : corporateRepresentative.trim();
    }

    public Integer getEconomicType() {
        return economicType;
    }

    public void setEconomicType(Integer economicType) {
        this.economicType = economicType;
    }

    public String getFinancialAffiliation() {
        return financialAffiliation;
    }

    public void setFinancialAffiliation(String financialAffiliation) {
        this.financialAffiliation = financialAffiliation == null ? null : financialAffiliation.trim();
    }

    public Boolean getIsFinancialUnit() {
        return isFinancialUnit;
    }

    public void setIsFinancialUnit(Boolean isFinancialUnit) {
        this.isFinancialUnit = isFinancialUnit;
    }

    public Boolean getIsVerticalUnit() {
        return isVerticalUnit;
    }

    public void setIsVerticalUnit(Boolean isVerticalUnit) {
        this.isVerticalUnit = isVerticalUnit;
    }

    public Boolean getIsVirtualUnit() {
        return isVirtualUnit;
    }

    public void setIsVirtualUnit(Boolean isVirtualUnit) {
        this.isVirtualUnit = isVirtualUnit;
    }

    public Integer getAccountingSystem() {
        return accountingSystem;
    }

    public void setAccountingSystem(Integer accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public String getCorporateTag() {
        return corporateTag;
    }

    public void setCorporateTag(String corporateTag) {
        this.corporateTag = corporateTag == null ? null : corporateTag.trim();
    }

    public String getMaintainerMark() {
        return maintainerMark;
    }

    public void setMaintainerMark(String maintainerMark) {
        this.maintainerMark = maintainerMark == null ? null : maintainerMark.trim();
    }

    public String getSupplierMark() {
        return supplierMark;
    }

    public void setSupplierMark(String supplierMark) {
        this.supplierMark = supplierMark == null ? null : supplierMark.trim();
    }

    public String getManufactureMark() {
        return manufactureMark;
    }

    public void setManufactureMark(String manufactureMark) {
        this.manufactureMark = manufactureMark == null ? null : manufactureMark.trim();
    }

    public String getAssetDisposalAgencyTag() {
        return assetDisposalAgencyTag;
    }

    public void setAssetDisposalAgencyTag(String assetDisposalAgencyTag) {
        this.assetDisposalAgencyTag = assetDisposalAgencyTag == null ? null : assetDisposalAgencyTag.trim();
    }

    public String getStateAssetManagementCompany() {
        return stateAssetManagementCompany;
    }

    public void setStateAssetManagementCompany(String stateAssetManagementCompany) {
        this.stateAssetManagementCompany = stateAssetManagementCompany == null ? null : stateAssetManagementCompany.trim();
    }

    public Integer getAuthorizedNumber() {
        return authorizedNumber;
    }

    public void setAuthorizedNumber(Integer authorizedNumber) {
        this.authorizedNumber = authorizedNumber;
    }

    public String getAdministrationAuthority() {
        return administrationAuthority;
    }

    public void setAdministrationAuthority(String administrationAuthority) {
        this.administrationAuthority = administrationAuthority == null ? null : administrationAuthority.trim();
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getSealTime() {
        return sealTime;
    }

    public void setSealTime(Date sealTime) {
        this.sealTime = sealTime;
    }

    public String getUnitRemark() {
        return unitRemark;
    }

    public void setUnitRemark(String unitRemark) {
        this.unitRemark = unitRemark == null ? null : unitRemark.trim();
    }

    public String getAssetDownloadStatus() {
        return assetDownloadStatus;
    }

    public void setAssetDownloadStatus(String assetDownloadStatus) {
        this.assetDownloadStatus = assetDownloadStatus == null ? null : assetDownloadStatus.trim();
    }

    public String getLatitudeAndLongitude() {
        return latitudeAndLongitude;
    }

    public void setLatitudeAndLongitude(String latitudeAndLongitude) {
        this.latitudeAndLongitude = latitudeAndLongitude == null ? null : latitudeAndLongitude.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    @Override
    public String toString() {
        return "OrganTree{" +
                "id='" + id + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitNameEn='" + unitNameEn + '\'' +
                ", parentId='" + parentId + '\'' +
                ", organizationForm=" + organizationForm +
                ", unitType=" + unitType +
                ", parentNodeName='" + parentNodeName + '\'' +
                ", chargeSectionId=" + chargeSectionId +
                ", chargeSectionName='" + chargeSectionName + '\'' +
                ", localFinancialCode='" + localFinancialCode + '\'' +
                ", localFinancialName='" + localFinancialName + '\'' +
                ", officeAdministrationCode='" + officeAdministrationCode + '\'' +
                ", officeAdministrationName='" + officeAdministrationName + '\'' +
                ", administrationDivisionCode='" + administrationDivisionCode + '\'' +
                ", administrationDivisionName='" + administrationDivisionName + '\'' +
                ", budgetCode=" + budgetCode +
                ", collegeCode='" + collegeCode + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", unitTypeCode=" + unitTypeCode +
                ", unitTypeName=" + unitTypeName +
                ", unitBasicProperty=" + unitBasicProperty +
                ", socialCreditCode='" + socialCreditCode + '\'' +
                ", budgetUnitName='" + budgetUnitName + '\'' +
                ", budgetUnitCode='" + budgetUnitCode + '\'' +
                ", budgetManagementLevel=" + budgetManagementLevel +
                ", firmName='" + firmName + '\'' +
                ", firmCode='" + firmCode + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", faxNumber='" + faxNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", fundSupplyModeName=" + fundSupplyModeName +
                ", fundSupplyModeCode=" + fundSupplyModeCode +
                ", administrationLevelName=" + administrationLevelName +
                ", administrationLevelType=" + administrationLevelType +
                ", departmentCategoryName=" + departmentCategoryName +
                ", businessDepartmentClassificationCode=" + businessDepartmentClassificationCode +
                ", principal='" + principal + '\'' +
                ", contact='" + contact + '\'' +
                ", corporateRepresentative='" + corporateRepresentative + '\'' +
                ", economicType=" + economicType +
                ", financialAffiliation='" + financialAffiliation + '\'' +
                ", isFinancialUnit=" + isFinancialUnit +
                ", isVerticalUnit=" + isVerticalUnit +
                ", isVirtualUnit=" + isVirtualUnit +
                ", accountingSystem=" + accountingSystem +
                ", corporateTag='" + corporateTag + '\'' +
                ", maintainerMark='" + maintainerMark + '\'' +
                ", supplierMark='" + supplierMark + '\'' +
                ", manufactureMark='" + manufactureMark + '\'' +
                ", assetDisposalAgencyTag='" + assetDisposalAgencyTag + '\'' +
                ", stateAssetManagementCompany='" + stateAssetManagementCompany + '\'' +
                ", authorizedNumber=" + authorizedNumber +
                ", administrationAuthority='" + administrationAuthority + '\'' +
                ", enableTime=" + enableTime +
                ", disableTime=" + disableTime +
                ", sealTime=" + sealTime +
                ", unitRemark='" + unitRemark + '\'' +
                ", assetDownloadStatus='" + assetDownloadStatus + '\'' +
                ", latitudeAndLongitude='" + latitudeAndLongitude + '\'' +
                ", createdTime=" + createdTime +
                ", isDeleted=" + isDeleted +
                ", status=" + status +
                ", sort=" + sort +
                ", tenantCode='" + tenantCode + '\'' +
                '}';
    }
}
