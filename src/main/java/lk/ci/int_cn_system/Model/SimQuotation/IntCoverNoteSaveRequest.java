package lk.ci.int_cn_system.Model.SimQuotation;

public class IntCoverNoteSaveRequest {
	
	private CoverNote coverNoteModel;
	
	private String quotationId;
	
	private SimMTCustomerRequest simMTCustomerRequest;

	public CoverNote getCoverNoteModel() {
		return coverNoteModel;
	}

	public void setCoverNoteModel(CoverNote coverNoteModel) {
		this.coverNoteModel = coverNoteModel;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public SimMTCustomerRequest getSimMTCustomerRequest() {
		return simMTCustomerRequest;
	}

	public void setSimMTCustomerRequest(SimMTCustomerRequest simMTCustomerRequest) {
		this.simMTCustomerRequest = simMTCustomerRequest;
	}
	
	

}
