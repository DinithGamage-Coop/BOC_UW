package lk.ci.int_cn_system.Model.MotorPolicy;

import java.util.List;

public class UwMotPVehicleRequestModel {
	
	private String customerRemark;
	
	private String isDeleted;
	
	private String motMVehicleId;
	
	private String status;
	
	private Integer sumInsured;
	
	private String systemRemark;
	
	private UwMotCovernoteRequestModel uwMotCovernoteRequest;
	
	private UwMotPVCrdPrModRequestModel uwMotPVCrdPrModRequest;
	
	private List<UwMotPVehCoversRequestListModel> uwMotPVehCoversRequestList;
	
	private List<UwMotPVehDiscountsRequestListModel> uwMotPVehDiscountsRequestList;
	
	private List<UwMotPVehLoadingRequestListModel> uwMotPVehLoadingRequestList;
	
	private UwMotPVehTaxRequestModel uwMotPVehTaxRequest;
	
	private String uwMotPolicyId;
	
	private String vehicleNo;

	
	
	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getMotMVehicleId() {
		return motMVehicleId;
	}

	public void setMotMVehicleId(String motMVehicleId) {
		this.motMVehicleId = motMVehicleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Integer sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getSystemRemark() {
		return systemRemark;
	}

	public void setSystemRemark(String systemRemark) {
		this.systemRemark = systemRemark;
	}

	public UwMotCovernoteRequestModel getUwMotCovernoteRequest() {
		return uwMotCovernoteRequest;
	}

	public void setUwMotCovernoteRequest(UwMotCovernoteRequestModel uwMotCovernoteRequest) {
		this.uwMotCovernoteRequest = uwMotCovernoteRequest;
	}

	public UwMotPVCrdPrModRequestModel getUwMotPVCrdPrModRequest() {
		return uwMotPVCrdPrModRequest;
	}

	public void setUwMotPVCrdPrModRequest(UwMotPVCrdPrModRequestModel uwMotPVCrdPrModRequest) {
		this.uwMotPVCrdPrModRequest = uwMotPVCrdPrModRequest;
	}

	public List<UwMotPVehCoversRequestListModel> getUwMotPVehCoversRequestList() {
		return uwMotPVehCoversRequestList;
	}

	public void setUwMotPVehCoversRequestList(List<UwMotPVehCoversRequestListModel> uwMotPVehCoversRequestList) {
		this.uwMotPVehCoversRequestList = uwMotPVehCoversRequestList;
	}

	public List<UwMotPVehDiscountsRequestListModel> getUwMotPVehDiscountsRequestList() {
		return uwMotPVehDiscountsRequestList;
	}

	public void setUwMotPVehDiscountsRequestList(List<UwMotPVehDiscountsRequestListModel> uwMotPVehDiscountsRequestList) {
		this.uwMotPVehDiscountsRequestList = uwMotPVehDiscountsRequestList;
	}

	public List<UwMotPVehLoadingRequestListModel> getUwMotPVehLoadingRequestList() {
		return uwMotPVehLoadingRequestList;
	}

	public void setUwMotPVehLoadingRequestList(List<UwMotPVehLoadingRequestListModel> uwMotPVehLoadingRequestList) {
		this.uwMotPVehLoadingRequestList = uwMotPVehLoadingRequestList;
	}

	public UwMotPVehTaxRequestModel getUwMotPVehTaxRequest() {
		return uwMotPVehTaxRequest;
	}

	public void setUwMotPVehTaxRequest(UwMotPVehTaxRequestModel uwMotPVehTaxRequest) {
		this.uwMotPVehTaxRequest = uwMotPVehTaxRequest;
	}

	public String getUwMotPolicyId() {
		return uwMotPolicyId;
	}

	public void setUwMotPolicyId(String uwMotPolicyId) {
		this.uwMotPolicyId = uwMotPolicyId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

}
