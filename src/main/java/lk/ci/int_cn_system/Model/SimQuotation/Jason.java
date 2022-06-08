package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.Date;
import java.util.List;

public class Jason {
	
	public Jason() {
		
	}	
	
	private Double netPremium;	
	
	private List<DetailFlow1> detailFlow;
	
	private String date;
	
	 

//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String branch;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}


	public List<DetailFlow1> getDetailFlow() {
		return detailFlow;
	}

	public void setDetailFlow(List<DetailFlow1> detailFlow) {
		this.detailFlow = detailFlow;
	}

	public Double getNetPremium() {
		return netPremium;
	}

	public void setNetPremium(Double netPremium) {
		this.netPremium = netPremium;
	}

	
}
