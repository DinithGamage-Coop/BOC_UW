package lk.ci.int_cn_system.Model.Administration;

public class ContactModel1 {
	
	private String contactNo;
	
	private String countryCode;
	
	private String isOnce;
	
	private ContactTypeModel1 contactType;

	
	
	
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getIsOnce() {
		return isOnce;
	}

	public void setIsOnce(String isOnce) {
		this.isOnce = isOnce;
	}

	public ContactTypeModel1 getContactType() {
		return contactType;
	}

	public void setContactType(ContactTypeModel1 contactType) {
		this.contactType = contactType;
	}

}
