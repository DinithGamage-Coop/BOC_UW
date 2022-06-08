package lk.ci.int_cn_system.Model.MotorPolicy;

import java.util.Date;

public class PolicyInactiveReasonModel {
	
	private String id;
	
	private String reasonName;
	
	private String validPeriodFrom;
	
	private String validPeriodTo;
	
	private Integer status;
	
	private String inactiveReason;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private String modifiedDate;
	
	private Integer isDeleted;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getValidPeriodFrom() {
		return validPeriodFrom;
	}

	public void setValidPeriodFrom(String validPeriodFrom) {
		this.validPeriodFrom = validPeriodFrom;
	}

	public String getValidPeriodTo() {
		return validPeriodTo;
	}

	public void setValidPeriodTo(String validPeriodTo) {
		this.validPeriodTo = validPeriodTo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
