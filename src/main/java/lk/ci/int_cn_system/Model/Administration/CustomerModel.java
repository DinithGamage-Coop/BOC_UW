package lk.ci.int_cn_system.Model.Administration;

import java.util.Date;
import java.util.List;

public class CustomerModel {
	
	private String id;
	
	private String isLocalEntity;
	
	private String titleId;
	
	private String initials;
	
	private String lastName;
	
	private String fullName;
	
	private String otherName;
	
	private String gender;
	
	private String designationId;
	
	private String company;
	
	private String email;
	
	private String nic;
	
	private String customerNic;
	
	private String customerPhone;
	
//	private String nicFile;
	
	public String getCustomerNic() {
		return customerNic;
	}

	public void setCustomerNic(String customerNic) {
		this.customerNic = customerNic;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	private String drivingLicenseNo;
	
//	private String dlFile;
	
	private String passportNo;
	
	private Date passportIssuedDate;
	
//	private String passportFile;
	
	private Date dateOfBirth;

	private String ethnicityId;
	
	private String religionId;
	
	private String maritalStatusId;
	
	private String preferredLanguageId;
	
	private Integer isCreditAllowed;
	
	private Integer isPolticlyExposed;
	
	private String isDeleted;
	
	private String status;
	
	private String customerCode;
	
	private String createdBy;
	
	private Boolean smsNotification;
	
	private Boolean emailNotification;
	
	private String inactiveReason;
	
	private CustomerExemptedTaxRequestModel customerExemptedTaxRequest;
	
	private CustomerOtherDetailsSaveRequestModel customerOtherDetailsSaveRequest;
	
	private List<ContactModel1> contact;
	
	private List<AddressModel1> address;
	
	private List<SpecModel> specs;
	
	private List<CusTradeTypeModel> tradeType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsLocalEntity() {
		return isLocalEntity;
	}

	public void setIsLocalEntity(String isLocalEntity) {
		this.isLocalEntity = isLocalEntity;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPassportIssuedDate() {
		return passportIssuedDate;
	}

	public void setPassportIssuedDate(Date passportIssuedDate) {
		this.passportIssuedDate = passportIssuedDate;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public String getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(String maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public String getPreferredLanguageId() {
		return preferredLanguageId;
	}

	public void setPreferredLanguageId(String preferredLanguageId) {
		this.preferredLanguageId = preferredLanguageId;
	}

	public Integer getIsCreditAllowed() {
		return isCreditAllowed;
	}

	public void setIsCreditAllowed(Integer isCreditAllowed) {
		this.isCreditAllowed = isCreditAllowed;
	}

	public Integer getIsPolticlyExposed() {
		return isPolticlyExposed;
	}

	public void setIsPolticlyExposed(Integer isPolticlyExposed) {
		this.isPolticlyExposed = isPolticlyExposed;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getSmsNotification() {
		return smsNotification;
	}

	public void setSmsNotification(Boolean smsNotification) {
		this.smsNotification = smsNotification;
	}

	public Boolean getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(Boolean emailNotification) {
		this.emailNotification = emailNotification;
	}

	public List<ContactModel1> getContact() {
		return contact;
	}

	public void setContact(List<ContactModel1> contact) {
		this.contact = contact;
	}

	public List<AddressModel1> getAddress() {
		return address;
	}

	public void setAddress(List<AddressModel1> address) {
		this.address = address;
	}

	public List<SpecModel> getSpecs() {
		return specs;
	}

	public void setSpecs(List<SpecModel> specs) {
		this.specs = specs;
	}

	public List<CusTradeTypeModel> getTradeType() {
		return tradeType;
	}

	public void setCustomerTradeType(List<CusTradeTypeModel> tradeType) {
		this.tradeType = tradeType;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public CustomerExemptedTaxRequestModel getCustomerExemptedTaxRequest() {
		return customerExemptedTaxRequest;
	}

	public void setCustomerExemptedTaxRequest(CustomerExemptedTaxRequestModel customerExemptedTaxRequest) {
		this.customerExemptedTaxRequest = customerExemptedTaxRequest;
	}

	public CustomerOtherDetailsSaveRequestModel getCustomerOtherDetailsSaveRequest() {
		return customerOtherDetailsSaveRequest;
	}

	public void setCustomerOtherDetailsSaveRequest(CustomerOtherDetailsSaveRequestModel customerOtherDetailsSaveRequest) {
		this.customerOtherDetailsSaveRequest = customerOtherDetailsSaveRequest;
	}

	public void setTradeType(List<CusTradeTypeModel> tradeType) {
		this.tradeType = tradeType;
	}

}