package lk.ci.int_cn_system.Model.Setting;

public class BranchCategoryModel {

	private String id;

	private String name;

	private String description;

	private String status;

	private String createdBy;

	private String modifiedBy;

	private String inactiveReason;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

}
