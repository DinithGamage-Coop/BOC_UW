package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.Date;

public class SimQuotationListGet {

	public SimQuotationListGet() {
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
	private SimMTCustomerResponse customer;	
		
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
	public SimMTCustomerResponse getCustomer() {
		return customer;
	}
	public void setCustomer(SimMTCustomerResponse customer) {
		this.customer = customer;
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
