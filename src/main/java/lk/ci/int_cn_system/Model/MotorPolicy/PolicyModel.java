package lk.ci.int_cn_system.Model.MotorPolicy;

import java.util.List;

import lk.ci.int_cn_system.Model.Administration.CustomerModel;

public class PolicyModel {
	
	private String additionalInfo;
	
	private String agentCode;
	
	private String authorizedBy;
	
	private String authorizedDate;
	
	private Integer claimCount;
	
	private CustomerModel customerSaveRequest;
	
	private String debtorCode;
	
	private String drivingSchoolDetails;
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private String endorsementNo;
	
	private String examinedBy;
	
	private String examinedDate;
	
	private Boolean hasClaims;
	
	private String inactiveReasonId;
	
	private Boolean isApproved;
	
	private String isDeleted;
	
	private Boolean isExamined;
	
	private Boolean isIssued;
	
	private String mktInsuranceClassId;
	
	private List<MotMVehicleRequestsModel> motMVehicleRequests;
	
	private String pabInsuredDetails;
	
	private String peCoverTypeId;
	
	private String pePolicyTypeId;
	
	private String peProductCatId;
	
	private String peProductId;
	
	private Double policyBasicPremium;
	
	private String policyExcessNarratives;
	
	private String policyLimitNarratives;
	
	private String policyNo;
	
	private Double policySumInsured;
	
	private Double policyTotalPremium;
	
	private String quotationLocation;
	
	private String quotationNo;
	
	private String revisionNo;
	
	private String specialRemarks;
	
	private String status;
	
	private List<UwMotDocsUploadMappingRequestListModel> uwMotDocsUploadMappingRequestList;
	
	private List<UwMotPPaymentLineRequestListModel> uwMotPPaymentLineRequestList;

	
	
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public Integer getClaimCount() {
		return claimCount;
	}

	public void setClaimCount(Integer claimCount) {
		this.claimCount = claimCount;
	}

	public CustomerModel getCustomerSaveRequest() {
		return customerSaveRequest;
	}

	public void setCustomerSaveRequest(CustomerModel customerSaveRequest) {
		this.customerSaveRequest = customerSaveRequest;
	}

	public String getDebtorCode() {
		return debtorCode;
	}

	public void setDebtorCode(String debtorCode) {
		this.debtorCode = debtorCode;
	}

	public String getDrivingSchoolDetails() {
		return drivingSchoolDetails;
	}

	public void setDrivingSchoolDetails(String drivingSchoolDetails) {
		this.drivingSchoolDetails = drivingSchoolDetails;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getEndorsementNo() {
		return endorsementNo;
	}

	public void setEndorsementNo(String endorsementNo) {
		this.endorsementNo = endorsementNo;
	}

	public String getExaminedBy() {
		return examinedBy;
	}

	public void setExaminedBy(String examinedBy) {
		this.examinedBy = examinedBy;
	}

	public String getExaminedDate() {
		return examinedDate;
	}

	public void setExaminedDate(String examinedDate) {
		this.examinedDate = examinedDate;
	}

	public Boolean getHasClaims() {
		return hasClaims;
	}

	public void setHasClaims(Boolean hasClaims) {
		this.hasClaims = hasClaims;
	}

	public String getInactiveReasonId() {
		return inactiveReasonId;
	}

	public void setInactiveReasonId(String inactiveReasonId) {
		this.inactiveReasonId = inactiveReasonId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsExamined() {
		return isExamined;
	}

	public void setIsExamined(Boolean isExamined) {
		this.isExamined = isExamined;
	}

	public Boolean getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(Boolean isIssued) {
		this.isIssued = isIssued;
	}

	public String getMktInsuranceClassId() {
		return mktInsuranceClassId;
	}

	public void setMktInsuranceClassId(String mktInsuranceClassId) {
		this.mktInsuranceClassId = mktInsuranceClassId;
	}

	public List<MotMVehicleRequestsModel> getMotMVehicleRequests() {
		return motMVehicleRequests;
	}

	public void setMotMVehicleRequests(List<MotMVehicleRequestsModel> motMVehicleRequests) {
		this.motMVehicleRequests = motMVehicleRequests;
	}

	public String getPabInsuredDetails() {
		return pabInsuredDetails;
	}

	public void setPabInsuredDetails(String pabInsuredDetails) {
		this.pabInsuredDetails = pabInsuredDetails;
	}

	public String getPeCoverTypeId() {
		return peCoverTypeId;
	}

	public void setPeCoverTypeId(String peCoverTypeId) {
		this.peCoverTypeId = peCoverTypeId;
	}

	public String getPePolicyTypeId() {
		return pePolicyTypeId;
	}

	public void setPePolicyTypeId(String pePolicyTypeId) {
		this.pePolicyTypeId = pePolicyTypeId;
	}

	public String getPeProductCatId() {
		return peProductCatId;
	}

	public void setPeProductCatId(String peProductCatId) {
		this.peProductCatId = peProductCatId;
	}

	public String getPeProductId() {
		return peProductId;
	}

	public void setPeProductId(String peProductId) {
		this.peProductId = peProductId;
	}

	public Double getPolicyBasicPremium() {
		return policyBasicPremium;
	}

	public void setPolicyBasicPremium(Double policyBasicPremium) {
		this.policyBasicPremium = policyBasicPremium;
	}

	public String getPolicyExcessNarratives() {
		return policyExcessNarratives;
	}

	public void setPolicyExcessNarratives(String policyExcessNarratives) {
		this.policyExcessNarratives = policyExcessNarratives;
	}

	public String getPolicyLimitNarratives() {
		return policyLimitNarratives;
	}

	public void setPolicyLimitNarratives(String policyLimitNarratives) {
		this.policyLimitNarratives = policyLimitNarratives;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Double getPolicySumInsured() {
		return policySumInsured;
	}

	public void setPolicySumInsured(Double policySumInsured) {
		this.policySumInsured = policySumInsured;
	}

	public Double getPolicyTotalPremium() {
		return policyTotalPremium;
	}

	public void setPolicyTotalPremium(Double policyTotalPremium) {
		this.policyTotalPremium = policyTotalPremium;
	}

	public String getQuotationLocation() {
		return quotationLocation;
	}

	public void setQuotationLocation(String quotationLocation) {
		this.quotationLocation = quotationLocation;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public String getSpecialRemarks() {
		return specialRemarks;
	}

	public void setSpecialRemarks(String specialRemarks) {
		this.specialRemarks = specialRemarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UwMotDocsUploadMappingRequestListModel> getUwMotDocsUploadMappingRequestList() {
		return uwMotDocsUploadMappingRequestList;
	}

	public void setUwMotDocsUploadMappingRequestList(
			List<UwMotDocsUploadMappingRequestListModel> uwMotDocsUploadMappingRequestList) {
		this.uwMotDocsUploadMappingRequestList = uwMotDocsUploadMappingRequestList;
	}

	public List<UwMotPPaymentLineRequestListModel> getUwMotPPaymentLineRequestList() {
		return uwMotPPaymentLineRequestList;
	}

	public void setUwMotPPaymentLineRequestList(List<UwMotPPaymentLineRequestListModel> uwMotPPaymentLineRequestList) {
		this.uwMotPPaymentLineRequestList = uwMotPPaymentLineRequestList;
	}

}
