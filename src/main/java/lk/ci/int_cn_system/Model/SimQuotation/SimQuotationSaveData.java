package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class SimQuotationSaveData {
	
	String policyType; // fleet or non-fleet

	SimQuotationPolicyData policyData;
	
	// non fleet
	
	SimQuotationNonRiskData nonRiskdata;
	
	List<SimQuotationCoverData> calculatedList;
	
	List<SimQuotationSaveExcessData> excessData;
	
	// if fleet		
 
	List<FleetCoverData> fleetCoverDatas; 
	
	
	
	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public List<FleetCoverData> getFleetCoverDatas() {
		return fleetCoverDatas;
	}

	public void setFleetCoverDatas(List<FleetCoverData> fleetCoverDatas) {
		this.fleetCoverDatas = fleetCoverDatas;
	}

	public List<SimQuotationCoverData> getCalculatedList() {
		return calculatedList;
	}

	public void setCalculatedList(List<SimQuotationCoverData> calculatedList) {
		this.calculatedList = calculatedList;
	}

	public SimQuotationPolicyData getPolicyData() {
		return policyData;
	}

	public void setPolicyData(SimQuotationPolicyData policyData) {
		this.policyData = policyData;
	}

	public SimQuotationNonRiskData getNonRiskdata() {
		return nonRiskdata;
	}

	public void setNonRiskdata(SimQuotationNonRiskData nonRiskdata) {
		this.nonRiskdata = nonRiskdata;
	}

	public List<SimQuotationSaveExcessData> getExcessData() {
		return excessData;
	}

	public void setExcessData(List<SimQuotationSaveExcessData> excessData) {
		this.excessData = excessData;
	}

	 
 
	
	
}
