package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class QtCalRequest {

	private String id;
	
	private String alias;
	
	private Double amount;
	
	private String calBasisAlias;
	
	private String endDate;
	
	private String periodOfCoverAlias;
	
	private String productId;
	
	private Double range;
	
	private Integer seatCount;
	
	private String shortPeriodName;
	
	private String startDate;
	
	private String type;
	
	private Double uev;
	
	private String valueBasisAlias;
	
	private List<CalculatedAlias> calculatedAlias;
	
	


	public List<CalculatedAlias> getCalculatedAlias() {
		return calculatedAlias;
	}

	public void setCalculatedAlias(List<CalculatedAlias> calculatedAlias) {
		this.calculatedAlias = calculatedAlias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCalBasisAlias() {
		return calBasisAlias;
	}

	public void setCalBasisAlias(String calBasisAlias) {
		this.calBasisAlias = calBasisAlias;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPeriodOfCoverAlias() {
		return periodOfCoverAlias;
	}

	public void setPeriodOfCoverAlias(String periodOfCoverAlias) {
		this.periodOfCoverAlias = periodOfCoverAlias;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public String getShortPeriodName() {
		return shortPeriodName;
	}

	public void setShortPeriodName(String shortPeriodName) {
		this.shortPeriodName = shortPeriodName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getUev() {
		return uev;
	}

	public void setUev(Double uev) {
		this.uev = uev;
	}

	public String getValueBasisAlias() {
		return valueBasisAlias;
	}

	public void setValueBasisAlias(String valueBasisAlias) {
		this.valueBasisAlias = valueBasisAlias;
	}
	
	
	
}
