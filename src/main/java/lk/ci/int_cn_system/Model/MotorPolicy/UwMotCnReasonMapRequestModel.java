package lk.ci.int_cn_system.Model.MotorPolicy;

public class UwMotCnReasonMapRequestModel {
	
	private String uwCovernoteReasonId;
	
	private String uwMotCovernoteId;
	
	private String inactiveReason;
	
	private String status;
	
	private String isDeleted;

	
	
	public String getUwCovernoteReasonId() {
		return uwCovernoteReasonId;
	}

	public void setUwCovernoteReasonId(String uwCovernoteReasonId) {
		this.uwCovernoteReasonId = uwCovernoteReasonId;
	}

	public String getUwMotCovernoteId() {
		return uwMotCovernoteId;
	}

	public void setUwMotCovernoteId(String uwMotCovernoteId) {
		this.uwMotCovernoteId = uwMotCovernoteId;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
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
