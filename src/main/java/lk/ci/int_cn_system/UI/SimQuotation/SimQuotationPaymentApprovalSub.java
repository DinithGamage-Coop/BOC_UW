package lk.ci.int_cn_system.UI.SimQuotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.Model.SimQuotation.BocQuotationDetails;
import lk.ci.int_cn_system.Model.SimQuotation.CoverNote;
import lk.ci.int_cn_system.Model.SimQuotation.IntCoverNoteSaveRequest;
import lk.ci.int_cn_system.Model.SimQuotation.Location;
import lk.ci.int_cn_system.Model.SimQuotation.SimMTCustomerRequest;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationListGet;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;

public class SimQuotationPaymentApprovalSub extends SimQuotationPaymentApproval {

	DashBoardSub dashBsub;

	UserResponseModel loggedInUser;

	UserResponseModel user;

	List<SimQuotationListGet> quotationList;
	
	List<BocQuotationDetails> bocquotationList;

	List<Location> locations;
	HashMap<String, Location> mapLocations = new HashMap<>();

	private String token;

	public SimQuotationPaymentApprovalSub() {
		// TODO Auto-generated constructor stub
		table1.addContainerProperty("#", Integer.class, null);
		table1.addContainerProperty("Quotation No", String.class, null);
		table1.addContainerProperty("Vehicle No.", String.class, null);
		table1.addContainerProperty("Sum Insured", String.class, null);
		table1.addContainerProperty("Date Issued", String.class, null);
//		table1.addContainerProperty("Convert to Cover Note", Label.class, null);
		table1.addContainerProperty("Is Approved", Label.class, null);
//		table1.addContainerProperty("Payment Status", Label.class, null);
		table1.addContainerProperty("Action", HorizontalLayout.class, null);

		textFieldSearch.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				// loadTable(textFieldSearch.getValue().trim());
			}
		});
		
//		btnview.addClickListener(new Button.ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				LoadQuotationDetailsForReport();
//				setexcell();
//				 
//			}
//		});
	}

	public void sendDashBoard(DashBoardSub boardSub, UserResponseModel loggedInUser) {
		dashBsub = boardSub;
		this.loggedInUser = loggedInUser;
		this.token = loggedInUser.getToken();

	}

	void loadTable() {
		try {

			table1.removeAllItems();
			for (SimQuotationListGet item : quotationList) {
				addToTable(item);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}
	}

	void addToTable(SimQuotationListGet listGet) {
		HorizontalLayout layout = new HorizontalLayout();
		Button paid_btn = new Button();
		paid_btn.setStyleName("danger");
		paid_btn.setCaption("Pending");
		

		if (listGet.getUwApproval() == true) {
			paid_btn.setEnabled(false);
			paid_btn.setStyleName("friendly");
			paid_btn.setCaption("Approved");
		} else {
			paid_btn.setEnabled(true);
			
		}

		paid_btn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				updatePaidStatus(listGet.getId());
				

				paid_btn.setEnabled(false);
				LoadQuotations();
				loadTable();
			}
		});
		
		

		Button cnview_btn = new Button();
		cnview_btn.setStyleName("primary");
		cnview_btn.setCaption("View");
		
		if (listGet.getUwApproval() == true) {
			paid_btn.setStyleName("friendly");
			paid_btn.setCaption("Approved");
		} else {
			cnview_btn.setEnabled(true);
			
		}
		
		
		
		

		cnview_btn.addClickListener(new Button.ClickListener() {
			
			

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				printCoverNote(listGet.getId());	
				
				
			}
		});
		

		layout.addComponent(paid_btn, 0);
		layout.addComponent(cnview_btn, 1);

//		Label cnapproval = new Label();
//		
//
//
//		if (listGet.getUwApproval() == false) {
//			cnapproval.setValue("Not Approved");
//		} else {
//			cnapproval.setValue("Approved");
//		}

		Label cnapproval = new Label();
		boolean st = listGet.getUwApproval();

		String stname = "";
		if (st == (false)) {
			stname = "Not Approved";
			cnapproval = new Label(
					"<div style='background: Orange';align='center';font-weight: 900;>" + stname + "</div>",
					ContentMode.HTML);
		} else {
			stname = "Approved";
			cnapproval = new Label(
					"<div style='background: lightgreen';text-align='center';font-weight: '900';>" + stname + "</div>",
					//"<h1 style='background: Orange';text-align: center';font-weight: 900;>" + stname + "</h1>",
					ContentMode.HTML);
		}

		table1.addItem(new Object[] { table1.size() + 1, listGet.getQuotationNo(), listGet.getVehicleNo(),
				new DecimalFormat("0.00").format(listGet.getSumInsured()),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(listGet.getIssuedDateTime()), cnapproval, layout },
				table1.size());

	}

	void LoadQuotations() {

		table1.removeAllItems();
		int i = -1;
		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation/getAllForUw");			
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
				quotationList = gson.fromJson(getRes, new TypeToken<List<SimQuotationListGet>>() {

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

	void updatePaidStatus(String quoteId) {

		int responseCode = 0;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation/UwApprove/" + quoteId);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			// only for post & Put
//			StringEntity body = new StringEntity(gson.toJson(m));
//			request.setEntity(body);

			HttpResponse response = client.execute(request);
			InputStream inputStream = (InputStream) response.getEntity().getContent();
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
			String getRes = "";
			while ((inputLine = in.readLine()) != null) {
				getRes = getRes + inputLine + "\n";
			}
			in.close();
			responseCode = response.getStatusLine().getStatusCode();

			if (responseCode == 200) {
				Notification.show("Approved Successfully");

				loadTable();
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	public void setUser(UserResponseModel user) {
		this.user = user;

		this.token = user.getToken();
		LoadQuotations();
		loadTable();
		
		// setexcell();

	}

	void LoadQuotationDetailsForReport() {

		System.out.println();

		table1.removeAllItems();
		int i = -1;
		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation");			
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
				bocquotationList = gson.fromJson(getRes, new TypeToken<List<BocQuotationDetails>>() {

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

			String filename = SysPath.GetSysPath() + "/loadq/" + "ot1" + System.currentTimeMillis() + ".xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Business Report");

			XSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("vehicle No");
			rowhead.createCell(1).setCellValue("name");
			rowhead.createCell(2).setCellValue("qt no");
			rowhead.createCell(3).setCellValue("premium");
			rowhead.createCell(4).setCellValue("amount");
			rowhead.createCell(5).setCellValue("amount");
			rowhead.createCell(6).setCellValue("amount");
			rowhead.createCell(7).setCellValue("amount");

			Integer i = 0;
			Double total = 0.0;
			Integer lastrow = 0;
			Double fnltot = 0.0;
			for (BocQuotationDetails item : bocquotationList) {
						
				i = i + 1;
				lastrow = i + 1;
				XSSFRow row = sheet.createRow(i + 1);

				String epff = item.getVehicleNo();

				Double namee = item.getSumInsured();
				String branchh = item.getLocationId();
				String dpttt = item.getQuotationNo();
				Double accnoo = item.getTotalPremium();
				String customer = item.getCustomer().getCustomerPhone();
				String customer1 = item.getCustomer().getCustomerNic();
				String customer2 = item.getCustomer().getLastName();
				//String nic = item.getCustomerModel().getCustomerNic();
				
				
 
				row.createCell(0).setCellValue(epff);
				row.createCell(1).setCellValue(namee);
				row.createCell(2).setCellValue(branchh);
				row.createCell(3).setCellValue(dpttt);
				row.createCell(4).setCellValue(accnoo);
				row.createCell(5).setCellValue(customer);
				row.createCell(6).setCellValue(customer1);
				row.createCell(7).setCellValue(customer2 );

				total = total + accnoo;
				fnltot = total; 
			}
			XSSFRow row1 = sheet.createRow(lastrow + 1);
			row1.createCell(3).setCellValue("total");
			row1.createCell(4).setCellValue(fnltot);

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			loadTable();

			FileResource frr = new FileResource(new File(filename));
			getUI().getPage().open(frr, "_blank", true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	void printCoverNote(String quoteId) {
		
		
		//////////////////////////////////////********
			int i = 0;

			try {
				
				String cvrReason = "";
				
				
				cvrReason=cvrReason.trim();
				
				IntCoverNoteSaveRequest intCoverNoteSave = new IntCoverNoteSaveRequest();
				
				CoverNote coverNote = new CoverNote();
				
				coverNote.setAddress("fff");
				coverNote.setChassisNo("fff");
				coverNote.setCovernoteDate("ff");
				coverNote.setCovernoteReson("ff");
			    coverNote.setDate("2022-01-10");
				coverNote.setEngineNo("aaa");
				coverNote.setInsuranceClass("aaa");
				coverNote.setInsuredName("Motor");
				coverNote.setNoOfDays(30);
				coverNote.setPolicyNo("sss");
				coverNote.setProductOfInsurance("sss");
				coverNote.setSumInsured("ss");
				coverNote.setTotalPremium("sss");
				coverNote.setVehiMake("ss");
				coverNote.setVehicleNo("sss");
				coverNote.setYom("sss");
				
				intCoverNoteSave.setCoverNoteModel(coverNote);
				
				intCoverNoteSave.setQuotationId(quoteId);
			
				
				SimMTCustomerRequest customer = new SimMTCustomerRequest();
				
				customer.setCustomerAddressArea("aa");
				customer.setCustomerAddressCity("aaa");
				customer.setCustomerAddressNo("qqq");
				customer.setCustomerAddressStreet("qqq");
				customer.setCustomerInitialName("qqq");
				customer.setCustomerLastName("11");
				customer.setCustomerNic("aa");
				customer.setCustomerPhone("aaaa");
				
				intCoverNoteSave.setSimMTCustomerRequest(customer);
				
				System.out.println("bbbbbb"+intCoverNoteSave);
				
				Gson gson = new Gson();
				HttpClient client = HttpClientBuilder.create().build();
			////	HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation/PrintIntmdCoverNote");
				HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation/PrintCoverNote");

				request.setHeader("Accept", "application/json");
				request.setHeader("Content-Type", "application/json");
				request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
				// only for post & Put
				StringEntity body = new StringEntity(gson.toJson(intCoverNoteSave));
				request.setEntity(body);
				
				System.out.println("bbbbbb " + gson.toJson(intCoverNoteSave));

				HttpResponse response = client.execute(request);
				InputStream inputStream = (InputStream) response.getEntity().getContent();
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
				i = response.getStatusLine().getStatusCode();

				if (i == 200) {
					
					InputStream is = response.getEntity().getContent();
					File pdf=new File(System.getProperty("user.home") + "/UW/temp/"+ System.currentTimeMillis() + ".pdf");
					FileOutputStream fos = new FileOutputStream(pdf);
						
					int read = 0;
					byte[] buffer = new byte[32768];
					while( (read = is.read(buffer)) > 0) {
					  fos.write(buffer, 0, read);
					}

					fos.close();
					is.close();
					
					FileResource fr2 = new FileResource(pdf);

					getUI().getPage().open(fr2, "_blank", true);
					
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
			} finally {
				if (i == 400) {
					Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
				} else if (i == 404) {
					Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
				}
			}

		//////////////////////////////////////*****
		}

}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        