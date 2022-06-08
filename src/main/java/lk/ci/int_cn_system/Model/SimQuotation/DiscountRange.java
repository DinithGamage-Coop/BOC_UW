package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.Date;

public class DiscountRange {

	private String rangeName;
	
	private Date effectiveFrom;
	
	private Date effectiveTo;
	
	private Integer isDisplayed;
	
	private Double rangeRate;
	
	private Double minimumAmount;
	
	private Double maximumAmount;
	
	

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

	public String getRangeName() {
		return rangeName;
	}

	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public Integer getIsDisplayed() {
		return isDisplayed;
	}

	public void setIsDisplayed(Integer isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public Double getRangeRate() {
		return rangeRate;
	}

	public void setRangeRate(Double rangeRate) {
		this.rangeRate = rangeRate;
	}
	
	
	
	
}
