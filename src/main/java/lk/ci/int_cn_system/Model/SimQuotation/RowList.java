package lk.ci.int_cn_system.Model.SimQuotation;

import java.util.List;

public class RowList {
	
	String vehicleNo;
	
	Double sumInsured;
	
	List<ColumnList> perils;

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public List<ColumnList> getPerils() {
		return perils;
	}

	public void setPerils(List<ColumnList> perils) {
		this.perils = perils;
	}

}
