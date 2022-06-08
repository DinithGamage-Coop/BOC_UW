package lk.ci.int_cn_system.Model.SimQuotation;

public class PaymentLines implements  Comparable<PaymentLines>{

//	private String id;
//	
	private String alias;
//	
//	private String name;
	
	private String taxTypeId;	
	
	private String pePaymentLineId;
	
	private Integer orderNo;
	
	private Integer isDisplayed;
	
	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(String taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getPePaymentLineId() {
		return pePaymentLineId;
	}

	public void setPePaymentLineId(String pePaymentLineId) {
		this.pePaymentLineId = pePaymentLineId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIsDisplayed() {
		return isDisplayed;
	}

	public void setIsDisplayed(Integer isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	@Override
	public int compareTo(PaymentLines arg0) {
		// TODO Auto-generated method stub
		return Integer.compare(orderNo, arg0.orderNo);
	}
	

 
	
	
}
