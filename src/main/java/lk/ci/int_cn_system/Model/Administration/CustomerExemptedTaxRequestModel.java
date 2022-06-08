package lk.ci.int_cn_system.Model.Administration;

public class CustomerExemptedTaxRequestModel {
	
	private String customerExemptedId;
	
	private String customerId;
	
	private String filePath;
	
	private String inactiveReason;
	
	private String isDeleted;
	
	private String status;
	
	private String taxTypeId;

	
	
	public String getCustomerExemptedId() {
		return customerExemptedId;
	}

	public void setCustomerExemptedId(String customerExemptedId) {
		this.customerExemptedId = customerExemptedId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(String taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

}
