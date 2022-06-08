package lk.ci.int_cn_system.Model.Administration;

public class BankBranchContactModel {
	
	private String id;
	
	private String contactType;
	
	private Integer countryCode;
	
	private String contactNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
}
