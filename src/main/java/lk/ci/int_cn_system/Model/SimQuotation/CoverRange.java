package lk.ci.int_cn_system.Model.SimQuotation;

public class CoverRange {

	
	private String id;
	
	private String rangeName;
	
	private Double rate;
	
	private Double rangeRate;
	
	private Double minimumAmount;
	
	private Double maximumAmount;
	
	private String effectiveFrom;
	
	private String effectiveTo;
	
	private String status;
	
	private String isDeleted;
	

	
	


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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getRangeRate() {
		return rangeRate;
	}

	public void setRangeRate(Double rangeRate) {
		this.rangeRate = rangeRate;
	}

	public Double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(Double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}

	public Double getMaximumAmount() {
		return maximumAmount;
	}

	public void setMaximumAmount(Double maximumAmount) {
		this.maximumAmount = maximumAmount;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	
	
	
}
