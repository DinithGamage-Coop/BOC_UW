package lk.ci.int_cn_system.Model.SimQuotation;

 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 
public class MotorQuotationPrintModel {

    public MotorQuotationPrintModel(){
        detailFlow=new ArrayList<>();
    }

    private String quotationNo;

    private String insuredName;

    private String typeOdVehicle;

    private String vehicleNo;

    private Double sumInsured;

    private Date date;

    private String chassisNo;

    private Integer validityPeriod;

    private Integer yearOfManufacture;

    private String makeAndModel;

    private Double netPremium;

    private Double totalPremium;

    private String additionalCovers;

    private String user;

    private Date printedDate;

    private String branch;

    private String salesStaff;
    
    private String usage;

    private List<QuotationDetailFlow> detailFlow;

    
    
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getTypeOdVehicle() {
		return typeOdVehicle;
	}

	public void setTypeOdVehicle(String typeOdVehicle) {
		this.typeOdVehicle = typeOdVehicle;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public Integer getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(Integer validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public Integer getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(Integer yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public String getMakeAndModel() {
		return makeAndModel;
	}

	public void setMakeAndModel(String makeAndModel) {
		this.makeAndModel = makeAndModel;
	}

	public Double getNetPremium() {
		return netPremium;
	}

	public void setNetPremium(Double netPremium) {
		this.netPremium = netPremium;
	}

	public Double getTotalPremium() {
		return totalPremium;
	}

	public void setTotalPremium(Double totalPremium) {
		this.totalPremium = totalPremium;
	}

	public String getAdditionalCovers() {
		return additionalCovers;
	}

	public void setAdditionalCovers(String additionalCovers) {
		this.additionalCovers = additionalCovers;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getPrintedDate() {
		return printedDate;
	}

	public void setPrintedDate(Date printedDate) {
		this.printedDate = printedDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSalesStaff() {
		return salesStaff;
	}

	public void setSalesStaff(String salesStaff) {
		this.salesStaff = salesStaff;
	}

	public List<QuotationDetailFlow> getDetailFlow() {
		return detailFlow;
	}

	public void setDetailFlow(List<QuotationDetailFlow> detailFlow) {
		this.detailFlow = detailFlow;
	}

    //private List<NetRateCoverItem> rateCoverItems;


}
