package lk.ci.int_cn_system.Model.Administration;

import java.util.List;

public class BankBranchModel {
	
	private String id;
	
	private String bankId;
	
	private Integer code;
	
	private String email;
	
	private String initialName;

	private Integer isLocalEntity;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String status;
	
	private List<BankBranchContactModel> bankBranchContact;
	
	private BankBranchAddressModel bankBranchAddress;

	
	// Get & Set Methods
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
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

	public Integer getIsLocalEntity() {
		return isLocalEntity;
	}

	public void setIsLocalEntity(Integer isLocalEntity) {
		this.isLocalEntity = isLocalEntity;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BankBranchContactModel> getBankBranchContact() {
		return bankBranchContact;
	}

	public void setBankBranchContact(List<BankBranchContactModel> bankBranchContact) {
		this.bankBranchContact = bankBranchContact;
	}

	public BankBranchAddressModel getBankBranchAddress() {
		return bankBranchAddress;
	}

	public void setBankBranchAddress(BankBranchAddressModel bankBranchAddress) {
		this.bankBranchAddress = bankBranchAddress;
	}
}
