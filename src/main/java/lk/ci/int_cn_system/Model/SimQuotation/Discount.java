package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class Discount {

	private String id;
	
	private String peDiscountName;
	
	private String alias;
	
	private Integer isRange;
	
	private Integer isFormula;
	
	private Integer calculationOrder;
	
	private List<DiscountRange> peDiscountRangeResponses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeDiscountName() {
		return peDiscountName;
	}

	public void setPeDiscountName(String peDiscountName) {
		this.peDiscountName = peDiscountName;
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

	public List<DiscountRange> getPeDiscountRangeResponses() {
		return peDiscountRangeResponses;
	}

	public void setPeDiscountRangeResponses(List<DiscountRange> peDiscountRangeResponses) {
		this.peDiscountRangeResponses = peDiscountRangeResponses;
	}
	
	
	
}
