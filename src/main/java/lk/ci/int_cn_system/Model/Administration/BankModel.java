package lk.ci.int_cn_system.Model.Administration;

//import java.util.List;

//import com.cicl.system.Underwriting.Model.Setting.AddressTypeModel;
//import com.cicl.system.Underwriting.Model.Setting.ContactTypeModel;

public class BankModel {

	private Integer code;

	private String email;

	private String initialName;

	private Integer isLocalEntity;

	private String id;

	private String status;

	private String inactiveReason;

//	private AddressTypeModel bankAddress;

//	private List<ContactTypeModel> bankContact;

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public Integer getIsLocalEntity() {
		return isLocalEntity;
	}

	public void setIsLocalEntity(Integer isLocalEntity) {
		this.isLocalEntity = isLocalEntity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInitialName() {
		return initialName;
	}

	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public AddressTypeModel getBankAddress() {
//		return bankAddress;
//	}

//	public void setBankAddress(AddressTypeModel bankAddress) {
//		this.bankAddress = bankAddress;
//	}

//	public List<ContactTypeModel> getBankContact() {
//		return bankContact;
//	}

//	public void setBankContact(List<ContactTypeModel> bankContact) {
//		this.bankContact = bankContact;
//	}

}
