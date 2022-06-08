package lk.ci.int_cn_system.Model.SimQuotation;
 
import java.util.Date;

 
public class SimMotorQuotationSaveRequest {

    private String quotationNo;
    private String agentCode;
    private String debtorCode;
    private SimMTCustomerRequest customer;
    private String locationId;
    private Date effectiveFrom;
    private Date effectiveTo;
    private Double sumInsured;
    private Double totalPremium;
    private String vehicleNo;
    private String policyTypeId;
    private String peProductId;
    private String peProductCatId;
    private String peCoverTypeId;
    private Approved isApproved;
    private Issued isIssued;
    private Integer printCount;
    private String mktInsClassId;
    private String revisionNo;
    private SimMTQuotationReportRequest simMTQuotationReport;
    private MotorQuotationPrintModel motorQuotationPrintModel;
    private Status status;
    private Boolean intmdCnGenerated;
    private String intmdCovernoteReasons;

    private String createdBy;
    private String modifiedBy;
    private String id;
    private String quotationData;
    
    

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getQuotationData() {
		return quotationData;
	}

	public void setQuotationData(String quotationData) {
		this.quotationData = quotationData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getDebtorCode() {
		return debtorCode;
	}

	public void setDebtorCode(String debtorCode) {
		this.debtorCode = debtorCode;
	}

	public SimMTCustomerRequest getCustomer() {
		return customer;
	}

	public void setCustomer(SimMTCustomerRequest customer) {
		this.customer = customer;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public Double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public Double getTotalPremium() {
		return totalPremium;
	}

	public void setTotalPremium(Double totalPremium) {
		this.totalPremium = totalPremium;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPolicyTypeId() {
		return policyTypeId;
	}

	public void setPolicyTypeId(String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}

	public String getPeProductId() {
		return peProductId;
	}

	public void setPeProductId(String peProductId) {
		this.peProductId = peProductId;
	}

	public String getPeProductCatId() {
		return peProductCatId;
	}

	public void setPeProductCatId(String peProductCatId) {
		this.peProductCatId = peProductCatId;
	}

	public String getPeCoverTypeId() {
		return peCoverTypeId;
	}

	public void setPeCoverTypeId(String peCoverTypeId) {
		this.peCoverTypeId = peCoverTypeId;
	}

	public Approved getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Approved isApproved) {
		this.isApproved = isApproved;
	}

	public Issued getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(Issued isIssued) {
		this.isIssued = isIssued;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public String getMktInsClassId() {
		return mktInsClassId;
	}

	public void setMktInsClassId(String mktInsClassId) {
		this.mktInsClassId = mktInsClassId;
	}

 
	public String getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}

	public SimMTQuotationReportRequest getSimMTQuotationReport() {
		return simMTQuotationReport;
	}

	public void setSimMTQuotationReport(SimMTQuotationReportRequest simMTQuotationReport) {
		this.simMTQuotationReport = simMTQuotationReport;
	}

	public MotorQuotationPrintModel getMotorQuotationPrintModel() {
		return motorQuotationPrintModel;
	}

	public void setMotorQuotationPrintModel(MotorQuotationPrintModel motorQuotationPrintModel) {
		this.motorQuotationPrintModel = motorQuotationPrintModel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getIntmdCnGenerated() {
		return intmdCnGenerated;
	}

	public void setIntmdCnGenerated(Boolean intmdCnGenerated) {
		this.intmdCnGenerated = intmdCnGenerated;
	}

	public String getIntmdCovernoteReasons() {
		return intmdCovernoteReasons;
	}

	public void setIntmdCovernoteReasons(String intmdCovernoteReasons) {
		this.intmdCovernoteReasons = intmdCovernoteReasons;
	}

    
}
