package lk.ci.int_cn_system.UI.SimQuotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;

import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.Model.SimQuotation.BocQuotationDetails;
import lk.ci.int_cn_system.Model.SimQuotation.BocReports;
import lk.ci.int_cn_system.Model.SimQuotation.DetailFlow1;
import lk.ci.int_cn_system.Model.SimQuotation.Jason;
import lk.ci.int_cn_system.Model.SimQuotation.ReportResponse;
import lk.ci.int_cn_system.Model.SimQuotation.SimMTCustomerResponse;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationListGet;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationSaveData;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationSaveData1;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;

public class BusinessReportSub extends BusinessReport {

	DashBoardSub dashBsub;
	UserResponseModel loggedInUser;
	UserResponseModel user;
	private String token;
	List<BocReports> bocReports;
	List<Jason> jasons;

	public BusinessReportSub() {

		excel_btn.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				
				if ((reportitem_combobox.getValue()!= null||startdate.getValue()!= null||enddate.getValue()!=null)) {
					//String des=null;
					SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
					String startdate1 = dt.format(startdate.getValue());
					String enddate1 = dt.format(enddate.getValue());
//					System.out.println(startdate1);
//					System.out.println(enddate1);
					LoadQuotations(startdate1,enddate1);
					setexcell();
					
						}
				
				else {
					Notification.show("Fill All Fields",Notification.TYPE_ERROR_MESSAGE);
					
				}
				

		

			}
		});

	}

	public void sendDashBoard(DashBoardSub boardSub, UserResponseModel loggedInUser) {
		dashBsub = boardSub;
		this.loggedInUser = loggedInUser;
		this.token = loggedInUser.getToken();
		 

	}

	public void setUser(UserResponseModel user) {
		this.user = user;
		this.token = user.getToken();
		 
	}

	void LoadQuotations(String startdate1,String enddate1 ) {
		
//		System.out.println(startdate1);
//		System.out.println(enddate1);

		int i = -1;
		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation/GetReportDetails/From/"+startdate1+"/To/"+enddate1);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			HttpResponse response = client.execute(request);
			InputStream inputStream = response.getEntity().getContent();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
			String getRes = "";
			while ((inputLine = in.readLine()) != null) {
				getRes = getRes + inputLine + "\n";
			}
			in.close();
			i = response.getStatusLine().getStatusCode();
			System.out.println(i);
			if (i == 200) {
				bocReports = gson.fromJson(getRes, new TypeToken<List<BocReports>>() {
				}.getType());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		} finally {

			if (i == 500) {
				Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 400) {
				Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 404) {
				Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 401) {
				Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 200) {

			} else {
				Notification.show("Error", Integer.toString(i), Notification.TYPE_ERROR_MESSAGE);
			}

		}
	}

	public void setexcell() {
		try {

			if (reportitem_combobox.getValue().equals("Quotation Business Report")) {

				String filename = SysPath.GetSysPath() + "/loadq/" + "BOC_Report_" + System.currentTimeMillis() + ".xlsx";
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Business Report");

				XSSFRow rowhead = sheet.createRow(0);
				rowhead.createCell(0).setCellValue("Date");
				rowhead.createCell(1).setCellValue("Boc Branch");
				rowhead.createCell(2).setCellValue("Quotation Number");
				rowhead.createCell(3).setCellValue("Customer Name");
				rowhead.createCell(4).setCellValue("NIC");
				rowhead.createCell(5).setCellValue("Contact No");
				rowhead.createCell(6).setCellValue("Vehicle No");
				rowhead.createCell(7).setCellValue("Chassis No");
				rowhead.createCell(8).setCellValue("Net Premium");
				rowhead.createCell(9).setCellValue("SRCC Premium");
				rowhead.createCell(10).setCellValue("Terrorism Premium");
				rowhead.createCell(11).setCellValue("Total Annual Premium");
				
				

				Integer i = 0;
				Double total = 0.0;
				Integer lastrow = 0;
				Double fnltot = 0.0;
				for (BocReports item : bocReports) {
					
					
					Gson gson = new Gson();
					SimQuotationSaveData1 saveData = gson.fromJson(item.getQuotationData(), SimQuotationSaveData1.class);
					
					Jason jasn = gson.fromJson(item.getReportResponse().getJason(), Jason.class);
					
					

					i = i + 1;
					lastrow = i + 1;
					XSSFRow row = sheet.createRow(i + 1);

					String bb = jasn.getDate();  
				
					
					bb=bb.substring(0,12);
					String[] month= {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
					
					String mon=bb.substring(0,3);
					
					String day=bb.substring(4,6);
					
					String year=bb.substring(8,12);
					
					//String temp="";
					for(int s=0 ; s<12 ;s++) 
					{
						
						if(month[s].equals(mon)) 
						{
							bb=year+"/"+(s+1)+"/"+day;
							break;
						}
						
					}
					
					String bocbr=jasn.getBranch();
					String quono = item.getQuotationNo();
					
					String name1 = item.getCustomer().getCustomerInitialName();
					String name2 = item.getCustomer().getCustomerLastName();
					
					String name = name1+" "+name2;
					
					String cusnam = name;
					String nic = item.getCustomer().getCustomerNic();
					String contnm = item.getCustomer().getCustomerPhone();
					String vehino = item.getVehicleNo();
					String chassno = saveData.getNonRiskdata().getChassisIdCtrl();
					Double netpr = jasn.getNetPremium();
					
					Double  srcc = jasn.getDetailFlow().get(0).getAmount();
					Double  tc = jasn.getDetailFlow().get(1).getAmount();				
					  
					Double totanupre = item.getTotalPremium();
					
					

					row.createCell(0).setCellValue(bb);
					row.createCell(1).setCellValue(bocbr);
					row.createCell(2).setCellValue(quono);
					row.createCell(3).setCellValue(cusnam);
					row.createCell(4).setCellValue(nic);
					row.createCell(5).setCellValue(contnm);
					row.createCell(6).setCellValue(vehino);						
					row.createCell(7).setCellValue(chassno);
					row.createCell(8).setCellValue(netpr);				
					row.createCell(9).setCellValue(srcc);
					row.createCell(10).setCellValue(tc);
					row.createCell(11).setCellValue(totanupre);
					

					total = total + totanupre;
					fnltot = total;
				}
				XSSFRow row1 = sheet.createRow(lastrow + 1);
				row1.createCell(10).setCellValue("TOTAL");
				row1.createCell(11).setCellValue(fnltot);

				FileOutputStream fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				fileOut.close();
				// loadTable();

				FileResource frr = new FileResource(new File(filename));
				getUI().getPage().open(frr, "_blank", true);
			} else if (reportitem_combobox.getValue().equals("Quotation Business Report")) {

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
