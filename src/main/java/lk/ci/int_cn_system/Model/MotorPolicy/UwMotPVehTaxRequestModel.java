package lk.ci.int_cn_system.Model.MotorPolicy;

public class UwMotPVehTaxRequestModel {
	
	private Double amount;
	
	private String inactiveReason;
	
	private String isDeleted;
	
	private Double rate;
	
	private String status;
	
	private String taxName;
	
	private String taxRateId;
	
	private String taxTypeId;
	
	private String uwMotPVehicleId;

	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(String taxRateId) {
		this.taxRateId = taxRateId;
	}

	public String getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(String taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getUwMotPVehicleId() {
		return uwMotPVehicleId;
	}

	public void setUwMotPVehicleId(String uwMotPVehicleId) {
		this.uwMotPVehicleId = uwMotPVehicleId;
	}

}
