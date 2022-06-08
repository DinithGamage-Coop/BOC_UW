package lk.ci.int_cn_system.Model.MotorPolicy;

public class UwMotPVCrdPrModRequestModel {
	
	private String inactiveReason;
	
	private String isDeleted;
	
	private String jsonData;
	
	private String lastPrintedLocation;
	
	private String lastPrintedUser;
	
	private Integer printCount;
	
	private String status;
	
	private String uwMotPVehicleId;
	
	private String uwMotPolicyId;

	
	
	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getLastPrintedLocation() {
		return lastPrintedLocation;
	}

	public void setLastPrintedLocation(String lastPrintedLocation) {
		this.lastPrintedLocation = lastPrintedLocation;
	}

	public String getLastPrintedUser() {
		return lastPrintedUser;
	}

	public void setLastPrintedUser(String lastPrintedUser) {
		this.lastPrintedUser = lastPrintedUser;
	}

	public Integer getPrintCount() {
		return printCount;
	}

	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUwMotPVehicleId() {
		return uwMotPVehicleId;
	}

	public void setUwMotPVehicleId(String uwMotPVehicleId) {
		this.uwMotPVehicleId = uwMotPVehicleId;
	}

	public String getUwMotPolicyId() {
		return uwMotPolicyId;
	}

	public void setUwMotPolicyId(String uwMotPolicyId) {
		this.uwMotPolicyId = uwMotPolicyId;
	}

}
