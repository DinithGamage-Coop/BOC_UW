package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class SimQuotationSaveData1 {
	
	String policyType; // fleet or non-fleet

//	SimQuotationPolicyData1 policyData;
	
	// non fleet
	
	SimQuotationNonRiskData1 nonRiskdata;
	
//	List<SimQuotationCoverData1> calculatedList;
//	
//	List<SimQuotationSaveExcessData1> excessData;
//	
//	// if fleet		
// 
//	List<FleetCoverData> fleetCoverDatas; 
	
	
	
	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	

	public SimQuotationNonRiskData1 getNonRiskdata() {
		return nonRiskdata;
	}

	public void setNonRiskdata(SimQuotationNonRiskData1 nonRiskdata) {
		this.nonRiskdata = nonRiskdata;
	}



	
}
