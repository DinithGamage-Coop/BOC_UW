package lk.ci.int_cn_system.Model.Administration;

public class AddressModel1 {

	public String id;
	
	public String addressLine;
	
	public String inactiveReason;
	
	public Boolean isDefault;
	
	private AddressTypeModel1 addressType;
	
	private CityModel1 city;
	
	private CountryModel1 country;
	
	private ProvinceModel1 province;

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public AddressTypeModel1 getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressTypeModel1 addressType) {
		this.addressType = addressType;
	}

	public CityModel1 getCity() {
		return city;
	}

	public void setCity(CityModel1 city) {
		this.city = city;
	}

	public CountryModel1 getCountry() {
		return country;
	}

	public void setCountry(CountryModel1 country) {
		this.country = country;
	}

	public ProvinceModel1 getProvince() {
		return province;
	}

	public void setProvince(ProvinceModel1 province) {
		this.province = province;
	}

}
