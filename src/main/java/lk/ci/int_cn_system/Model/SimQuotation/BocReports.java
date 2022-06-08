package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.Date;

import lk.ci.int_cn_system.Model.Administration.CustomerModel;
import lk.ci.int_cn_system.Model.Administration.CustomerModel1;

public class BocReports {

	public BocReports() {
		// TODO Auto-generated constructor stub
	}
	
	private String id;
	private String customerInitialName;
	private String customerLastName;	
	private String quotationNo;
	private Double sumInsured;
	private Date issuedDateTime;	
	private String vehicleNo;	
	private Double totalPremium;	
	private String locationId;	
	private Boolean intmdCnGenerated;	
	private Boolean uwApproval;
	//private SimMTCustomerResponse customer;	
	private CustomerModel1 customer;
	
	private String quotationData;
	
	
	public String getQuotationData() {
		return quotationData;
	}

	public void setQuotationData(String quotationData) {
		this.quotationData = quotationData;
	}

	public void setCustomer(CustomerModel1 customer) {
		this.customer = customer;
	}

	private ReportResponse reportResponse;
		
	
	public ReportResponse getReportResponse() {
		return reportResponse;
	}
	public void setReportResponse(ReportResponse reportResponse) {
		this.reportResponse = reportResponse;
	}

	public CustomerModel1 getCustomer() {
		return customer;
	}
//	public void setReportResponse(ReportResponse reportResponse) {
//		this.reportResponse = reportResponse;
//	}
	
	public Boolean getUwApproval() {
		return uwApproval;
	}
	public void setUwApproval(Boolean uwApproval) {
		this.uwApproval = uwApproval;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public Double getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(Double totalPremium) {
		this.totalPremium = totalPremium;
	}	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerInitialName() {
		return customerInitialName;
	}
	public void setCustomerInitialName(String customerInitialName) {
		this.customerInitialName = customerInitialName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public String getQuotationNo() {
		return quotationNo;
	}
	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public Date getIssuedDateTime() {
		return issuedDateTime;
	}
	public void setIssuedDateTime(Date issuedDateTime) {
		this.issuedDateTime = issuedDateTime;
	}
	public Boolean getIntmdCnGenerated() {
		return intmdCnGenerated;
	}
	public void setIntmdCnGenerated(Boolean intmdCnGenerated) {
		this.intmdCnGenerated = intmdCnGenerated;
	}
	
	
	
}
