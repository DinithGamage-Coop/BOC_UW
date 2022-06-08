package lk.ci.int_cn_system.Model.Setting;

public class ContactTypeModel {
	
	private String id;
	
	private String name;
	
	private String  description;
	
	private Integer isOnce;
	
	private Integer countryCode;	

	private String status;
	
	private String  inactiveReason;
	
	private String contactNo;
	
	private String contactType;
	
	private String createdBy;
	
	private String modifiedBy;	
	
	
	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}



	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public Integer getIsOnce() {
		return isOnce;
	}

	public void setIsOnce(Integer isonece) {
		this.isOnce = isonece;
	}
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public String getInactivereason() {
		return inactiveReason;
	}

	public void setInactivereason(String inactivereason) {
		this.inactiveReason = inactivereason;
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

}
