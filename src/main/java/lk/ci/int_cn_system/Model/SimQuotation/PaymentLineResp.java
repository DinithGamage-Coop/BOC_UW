package lk.ci.int_cn_system.Model.SimQuotation;

public class PaymentLineResp {

	private String id;
	
	private String name;
	
	private String alias;
	
	private Double value;
	
	private Integer isDisplayed;
	
	

 

	public Integer getIsDisplayed() {
		return isDisplayed;
	}

	public void setIsDisplayed(Integer isDisplayed) {
		this.isDisplayed = isDisplayed;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
	
}