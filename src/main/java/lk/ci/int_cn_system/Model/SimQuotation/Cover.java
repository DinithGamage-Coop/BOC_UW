package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class Cover {

	private String id;
	
	private String alias;
	
	private String peCoverName;
	
	private String peCoverCalBasisAlias;
	
	private String peCoverValueBasisAlias;
	
	private Integer calculationOrder;
	
	
	private List<CoverRange> peCoverRangeResponses;
	
	

	public List<CoverRange> getPeCoverRangeResponses() {
		return peCoverRangeResponses;
	}

	public void setPeCoverRangeResponses(List<CoverRange> peCoverRangeResponses) {
		this.peCoverRangeResponses = peCoverRangeResponses;
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

	public String getPeCoverName() {
		return peCoverName;
	}

	public void setPeCoverName(String peCoverName) {
		this.peCoverName = peCoverName;
	}

	public String getPeCoverCalBasisAlias() {
		return peCoverCalBasisAlias;
	}

	public void setPeCoverCalBasisAlias(String peCoverCalBasisAlias) {
		this.peCoverCalBasisAlias = peCoverCalBasisAlias;
	}

	public String getPeCoverValueBasisAlias() {
		return peCoverValueBasisAlias;
	}

	public void setPeCoverValueBasisAlias(String peCoverValueBasisAlias) {
		this.peCoverValueBasisAlias = peCoverValueBasisAlias;
	}

	public Integer getCalculationOrder() {
		return calculationOrder;
	}

	public void setCalculationOrder(Integer calculationOrder) {
		this.calculationOrder = calculationOrder;
	}
	
	
	
}
