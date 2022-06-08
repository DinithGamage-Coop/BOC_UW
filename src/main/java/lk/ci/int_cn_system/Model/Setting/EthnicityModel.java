package lk.ci.int_cn_system.Model.Setting;

public class EthnicityModel {
	
	
	private String id;	
	
	private String name;
	
	private String status;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String inactiveReason;
	
	private String isDeleted;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
