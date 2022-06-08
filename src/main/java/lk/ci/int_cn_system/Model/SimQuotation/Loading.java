package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class Loading {

	private String id;
	
	private String peLoadingName;
	
	private String alias;
	
	private Integer isRange;
	
	private Integer isFormula;
	
	private Integer calculationOrder;
	
	private List<LoadingRange> peLoadingRangeResponses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeLoadingName() {
		return peLoadingName;
	}

	public void setPeLoadingName(String peLoadingName) {
		this.peLoadingName = peLoadingName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getIsRange() {
		return isRange;
	}

	public void setIsRange(Integer isRange) {
		this.isRange = isRange;
	}

	public Integer getIsFormula() {
		return isFormula;
	}

	public void setIsFormula(Integer isFormula) {
		this.isFormula = isFormula;
	}

	public Integer getCalculationOrder() {
		return calculationOrder;
	}

	public void setCalculationOrder(Integer calculationOrder) {
		this.calculationOrder = calculationOrder;
	}

	public List<LoadingRange> getPeLoadingRangeResponses() {
		return peLoadingRangeResponses;
	}

	public void setPeLoadingRangeResponses(List<LoadingRange> peLoadingRangeResponses) {
		this.peLoadingRangeResponses = peLoadingRangeResponses;
	}
	
	
	
}
