package lk.ci.int_cn_system.UI.SimQuotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.vaadin.dialogs.ConfirmDialog;

import lk.ci.int_cn_system.Model.Auth.RoleCheck;
import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.Model.SimQuotation.Approved;
import lk.ci.int_cn_system.Model.SimQuotation.CalculatedAlias;
import lk.ci.int_cn_system.Model.SimQuotation.City;
import lk.ci.int_cn_system.Model.SimQuotation.ColumnList;
import lk.ci.int_cn_system.Model.SimQuotation.Cover;
import lk.ci.int_cn_system.Model.SimQuotation.CoverNote;
import lk.ci.int_cn_system.Model.SimQuotation.CoverNoteReason;
import lk.ci.int_cn_system.Model.SimQuotation.CoverRange;
import lk.ci.int_cn_system.Model.SimQuotation.Discount;
import lk.ci.int_cn_system.Model.SimQuotation.DiscountRange;
import lk.ci.int_cn_system.Model.SimQuotation.Excess;
import lk.ci.int_cn_system.Model.SimQuotation.IntCoverNoteSaveRequest;
import lk.ci.int_cn_system.Model.SimQuotation.Issued;
import lk.ci.int_cn_system.Model.SimQuotation.Loading;
import lk.ci.int_cn_system.Model.SimQuotation.LoadingRange;
import lk.ci.int_cn_system.Model.SimQuotation.Location;
import lk.ci.int_cn_system.Model.SimQuotation.MakeModel;
import lk.ci.int_cn_system.Model.SimQuotation.MotorQuotationPrintModel;
import lk.ci.int_cn_system.Model.SimQuotation.NetRate;
import lk.ci.int_cn_system.Model.SimQuotation.PaymentLineResp;
import lk.ci.int_cn_system.Model.SimQuotation.PaymentLines;
import lk.ci.int_cn_system.Model.SimQuotation.PeCoverType;
import lk.ci.int_cn_system.Model.SimQuotation.PePackage;
import lk.ci.int_cn_system.Model.SimQuotation.PePolicyCategory;
import lk.ci.int_cn_system.Model.SimQuotation.PePolicyUsage;
import lk.ci.int_cn_system.Model.SimQuotation.PeProductListItem;
import lk.ci.int_cn_system.Model.SimQuotation.PeriodOfCover;
import lk.ci.int_cn_system.Model.SimQuotation.QtCalRequest;
import lk.ci.int_cn_system.Model.SimQuotation.QuotationDetailFlow;
import lk.ci.int_cn_system.Model.SimQuotation.RowList;
import lk.ci.int_cn_system.Model.SimQuotation.SalesStaff;
import lk.ci.int_cn_system.Model.SimQuotation.SimMTCustomerRequest;
import lk.ci.int_cn_system.Model.SimQuotation.SimMTCustomerResponse;
import lk.ci.int_cn_system.Model.SimQuotation.SimMTQuotationReportRequest;
import lk.ci.int_cn_system.Model.SimQuotation.SimMotorQuotationSaveRequest;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationCoverData;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationListGet;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationNonRiskData;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationPolicyData;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationResp;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationSaveData;
import lk.ci.int_cn_system.Model.SimQuotation.Status;
import lk.ci.int_cn_system.Model.SimQuotation.VehicleType;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;
import lk.ci.int_cn_system.Utils.VehiNoPatternValidator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SimQuotationSub extends SimQuotation {

	DashBoardSub dashBsub;

	UserResponseModel user;

	private String token;

	private Boolean isUpdate = false;
	private Boolean isRevised = false;

//	public UserResponseModel getUser() {
//		return user;
//	}
	
	List<SimQuotationListGet> quotationList;

	public void setUser(UserResponseModel user) {
		this.user = user;

		this.token = user.getToken();

		loadLocation();

		loadCities();

		loadCoveTypes();

		loadPolCats();

		loadPolUs();

		loadPeriodOfCover();
		
//		loadVehiTypes();
		
		loadCNReasons();
		
		fileUpload();
		
		loadMakeModels();

		txtVehNos.addBlurListener(new FieldEvents.BlurListener() {

			@Override
			public void blur(BlurEvent event) {
				// TODO Auto-generated method stub

				String prov = comboVehProv.getValue() == null ? "" : comboVehProv.getValue().toString();

				getByVehicleNo(VehiNoPatternValidator.generate(prov, txtVehLett.getValue().trim(), txtVehNos.getValue().trim()).replaceAll(" ", "%20"));

			}
		});

		buttonSearchByVehicle.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				String vehi = "";

				String prov = comboVehProv.getValue() == null ? "" : comboVehProv.getValue().toString();

				if (!txtVehLett.getValue().trim().isEmpty() && !txtVehNos.getValue().trim().isEmpty()) {

					VehicleNoSearchWindowSub vehicleNoSearchSub = new VehicleNoSearchWindowSub();
					Window vehicleNoSearchWindow = new Window();

					vehicleNoSearchWindow.center();
					vehicleNoSearchWindow.setClosable(true);
					vehicleNoSearchWindow.setModal(true);
					vehicleNoSearchWindow.setResponsive(true);
					vehicleNoSearchWindow.setResizable(false);
					vehicleNoSearchWindow.setWidth("800px");
					vehicleNoSearchWindow.setHeight("450px");

					vehicleNoSearchSub.getWindow(vehicleNoSearchWindow, SimQuotationSub.this);
					vehicleNoSearchWindow.setContent(vehicleNoSearchSub);

					vehicleNoSearchSub.getByVehicleNo(VehiNoPatternValidator
							.generate(prov, txtVehLett.getValue().trim(), txtVehNos.getValue().trim())
							.replaceAll(" ", "%20"));
					vehicleNoSearchSub.loadToTable();
					UI.getCurrent().addWindow(vehicleNoSearchWindow);
				}

			}
		});
		
		txtSumInsured.addBlurListener(new FieldEvents.BlurListener() {
			
			@Override
			public void blur(BlurEvent event) {
				// TODO Auto-generated method stub
				if(!txtSumInsured.getValue().trim().isEmpty()) {
					if (Integer.parseInt(txtSumInsured.getValue().toString()) >= 20000000) {
						
						Notification.show("Sum Inured Limit Exceeded..!", Notification.TYPE_WARNING_MESSAGE);
						
						txtSumInsured.setValue("");
						
					}
				}
				
					
			}
		});

		optionGroupRegNonReg.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				if (optionGroupRegNonReg.getValue().toString().equals("Non-Registered")) {

					txtChassis.setRequired(true);
					txtChassis.setRequiredError("Required !");
				} else {
					txtChassis.setRequired(false);
				}
			}
		});
		
		buttonSaveCvrNote.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				saveIntCvrNote();
				
			}
		});

//		comboPeriodOfCover.addValueChangeListener(new ValueChangeListener() {
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				// TODO Auto-generated method stub
//				if (comboPeriodOfCover.getValue() != null) {
//					if (comboPeriodOfCover.getValue().toString().equalsIgnoreCase("Annual")) {
//						Calendar cfrom = Calendar.getInstance();
//						cfrom.setTime(dateStartDate.getValue());
//						cfrom.add(Calendar.YEAR, 1);
//
//						dateEndDate.setValue(cfrom.getTime());
//					}
//				}
//
//			}
//		});

		btnCalculatePerils.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				if (!validated()) {
					return;
				}
				
				selectDefaultCovers();
				
				
			}
		});
		
//		comboVehType.addValueChangeListener(new ValueChangeListener() {
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				// TODO Auto-generated method stub
//				if (comboVehType.getValue() != null) {
//					loadMakeModels(ConstantData.makeAndModelId,
//							mapVehiType.get(comboVehType.getValue().toString()).getId());
//				}
//			}
//		});

		txtSumInsured.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				// calculate();
			}
		});

//		comboPePackageName.addValueChangeListener(new ValueChangeListener() {
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				// TODO Auto-generated method stub
//				if (comboPePackageName.getValue() != null) {
//					String productId = mapPePackage.get(comboPePackageName.getValue().toString()).getPackageId();
//
//					System.out.println("Product id " + productId);
//
//					calAliasList.clear();
//
//					loadNetRate(productId);
//					loadCovers(productId);
//					loadDsounts(productId);
//					loadLoading(productId);
//
//					tablePerils.setSortEnabled(true);
//					loadTable();
//
//					tablePerils.setSortEnabled(false);
//				}
//
//			}
//		});

		buttonSaveQuotation.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				calculate();
				removePaymentLinesAlias();
				tablePmtLine.removeAllItems();
				loadPmtLines();
				calPaymntLine();
				genCalBreakDown();

				if (loadedQuoId == null) {

					save();

				} else {
					update();
				}

			}
		});

		buttonClear.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ConfirmDialog.show(getUI(), "Confirmation", "Sure to clear ?", "Yes", "No",
						new ConfirmDialog.Listener() {
							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									// Confirmed to continue
									clearForm();
								} else {
									// User did not confirm
								}
							}
						});
			}
		});

		buttonCalTotalPre.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				int i = 0;

				for (Object x : tablePerils.getItemIds()) {
					CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(x, "+").getValue();
					if (chbSel.getValue()) {
						i++;
					}
				}

				if (i == 0) {
					Notification.show("No items selected !", Notification.TYPE_ERROR_MESSAGE);
					return;
				}

				calculate();
				removePaymentLinesAlias();
				tablePmtLine.removeAllItems();
				loadPmtLines();
				calPaymntLine();
				genCalBreakDown();

			}
		});
		
		btnQuotationSearch.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				
				String quoteNo = txtQuotationNo.getValue().toString().trim();
				
				getByQuotationNo(quoteNo);
				
			}
		});

		comboPeriodOfCover.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {

			}
		});

//		txtSeatCount.addValueChangeListener(new Property.ValueChangeListener() {
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				// TODO Auto-generated method stub
//
//				if (!txtSeatCount.getValue().trim().isEmpty()) {
//
//					try {
//						Integer.parseInt(txtSeatCount.getValue().trim());
//					} catch (NumberFormatException e) {
//						Notification.show("Error", "Invalid value for 'Seat Count'", Notification.TYPE_ERROR_MESSAGE);
//						return;
//					}
//
//				}
//
//			}
//		});

		comboProduct.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub

				tablePerils.removeAllItems();
				if (calAliasList != null) {
					calAliasList.clear();
				}

				if (comboProduct.getValue() != null) {

					loadPackages(((PeProductListItem) mapPeProdList.get(comboProduct.getValue().toString()))
							.getPeProductCatId());

					String productId = ((PeProductListItem) mapPeProdList.get(comboProduct.getValue().toString()))
							.getPeProductCatId();

				}
				
				String productId = mapPePackage.get(comboPePackageName.getValue().toString()).getPackageId();

				System.out.println("Product id " + productId);

				calAliasList.clear();

				loadNetRate(productId);
				loadCovers(productId);
				loadDsounts(productId);
				loadLoading(productId);

				tablePerils.setSortEnabled(true);
				loadTable();
				//tablePerils.sort();
				//tablePerils.setSortEnabled(false);

			}
		});

		comboPolUsage.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub

				if (comboPolUsage.getValue() != null) {

					loadProducts(((PePolicyUsage) mapPePolUs.get(comboPolUsage.getValue().toString())).getUsageId());
					
					if(comboPolUsage.getValue().toString().equals("Private")) {
						
						comboPolCategory.select("Private Vehicle");
						
					} else if(comboPolUsage.getValue().toString().equals("Hiring")) {
						
						comboPolCategory.select("Commercial");
						
					}

				}

			}
		});

		txtNIC.addBlurListener(new FieldEvents.BlurListener() {

			@Override
			public void blur(BlurEvent arg0) {
				// TODO Auto-generated method stub
				searchCustomer(txtNIC.getValue().trim(), "NIC");
			}
		});

		txtMobile.addBlurListener(new FieldEvents.BlurListener() {

			@Override
			public void blur(BlurEvent arg0) {
				// TODO Auto-generated method stub
				searchCustomer(txtMobile.getValue().trim(), "PH");
			}
		});
		
		
		comboCoverType.select("Comprehensive");
//		comboPolCategory.select("Private Vehicle");
		comboPeriodOfCover.select("Annual");
		
		comboCoverType.setEnabled(false);
		comboPolCategory.setEnabled(false);
		comboPePackageName.setEnabled(false);
		comboPeriodOfCover.setEnabled(false);
		dateExpireDate.setEnabled(false);

	}

	List<City> cities;
	HashMap<String, City> mapCities = new HashMap<>();

	List<PeCoverType> peCoverTypes;
	HashMap<String, PeCoverType> mapCoverTypes = new HashMap<>();

	List<PePolicyCategory> pePolcats;
	HashMap<String, PePolicyCategory> mapPePolcats = new HashMap<>();

	List<PePolicyUsage> pePolUs;
	HashMap<String, PePolicyUsage> mapPePolUs = new HashMap<>();

	List<PeProductListItem> peProdList;
	HashMap<String, PeProductListItem> mapPeProdList = new HashMap<>();

	List<PePackage> pePackage;
	HashMap<String, PePackage> mapPePackage = new HashMap<>();

	List<Location> locations;
	HashMap<String, Location> mapLocations = new HashMap<>();

	List<SalesStaff> salesStaff;
	HashMap<String, SalesStaff> mapSalesStaff = new HashMap<>();

	List<Excess> excess;
	HashMap<String, Excess> mapExcess = new HashMap<>();

	List<VehicleType> vehiType;
	HashMap<String, VehicleType> mapVehiType = new HashMap<>();

	List<MakeModel> makeModel;
	HashMap<String, MakeModel> mapMakeModel = new HashMap<>();

	List<PeriodOfCover> periodCover;
	HashMap<String, PeriodOfCover> mapPeriodCover = new HashMap<>();

	List<PaymentLines> paymentLines;

	List<Cover> covers;

	List<Discount> discounts;

	List<Loading> loadings;

	List<RowList> rowList;

	List<ColumnList> columnList;
	
	List<CoverNoteReason> cvrNoteReasons;

	List<CalculatedAlias> calAliasList = new ArrayList<>();
	// List<PaymentLineResp> calPmtList;

	String vehicleNo;

	private String cusId;

	private String savedQuoId;
	private String savedQuoNo;
	private Integer loadedPrintCount;

	private String loadedQuoId;
	private String loadedQuoNo;

	public SimQuotationSub() {
		
		loadYOM();
		
		buttonSaveCvrNote.setEnabled(false);
		
		loadedPrintCount = 0;

		tablePerils.addContainerProperty("#", Integer.class, null);
		tablePerils.addContainerProperty("+", CheckBox.class, null);
		tablePerils.addContainerProperty("Type", String.class, null);
		tablePerils.addContainerProperty("Name", Label.class, null);
		tablePerils.addContainerProperty("Value", VerticalLayout.class, null);
		tablePerils.addContainerProperty("Total", String.class, null);
		tablePerils.addContainerProperty(".", Integer.class, null);
		tablePerils.addContainerProperty("..", Object.class, null);
		tablePerils.addContainerProperty("rv", HashMap.class, null);

		tablePerils.setColumnWidth("#", 40);
		tablePerils.setColumnWidth("+", 40);
		tablePerils.setColumnWidth("Type", 90);
		tablePerils.setColumnWidth("Name", 290);
		tablePerils.setColumnWidth("Value", 110);
		tablePerils.setColumnWidth(".", 0);
		tablePerils.setColumnWidth("..", 0);
		tablePerils.setColumnWidth("rv", 0);

		tablePerils.setSortContainerPropertyId(".");

		tablePmtLine.addContainerProperty("Name", String.class, null);
		tablePmtLine.addContainerProperty("Amount", String.class, null);
		tablePmtLine.addContainerProperty(".", Integer.class, null);
		tablePmtLine.addContainerProperty("..", Object.class, null);

		tablePmtLine.setColumnWidth("Amount", 110);
		tablePmtLine.setColumnWidth(".", 0);
		tablePmtLine.setColumnWidth("..", 0);

		tablePmtLine.setSortContainerPropertyId(".");
		
		tableCNReason.addContainerProperty("+", CheckBox.class, null);
		tableCNReason.addContainerProperty("Name", String.class, null);
		tableCNReason.addContainerProperty("id", String.class, null);
		tableCNReason.setColumnWidth("+", 40);
		tableCNReason.setColumnWidth("id", 0);
		
		tableDocumentUpload.addContainerProperty("#", Integer.class, null);
		tableDocumentUpload.addContainerProperty("Document Name", String.class, null);

		optionGroupRegNonReg.setValue("Registered");
		dateStartDate.setValue(new Date());
		dateStartDate.setEnabled(false);

//		dateEndDate.setValue(new Date());

		txtVehLett.setMaxLength(3);
		txtVehNos.setMaxLength(4);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 45);
		Date after45 = c.getTime();
		
		dateExpireDate.setValue(after45);
		
		Calendar cnEndDate = Calendar.getInstance();
		cnEndDate.add(Calendar.DATE, 30);
		Date endDate = cnEndDate.getTime();

		dateEndDate.setValue(endDate);
		
		txtSeatCount.setValue("4");

	}

	void removePaymentLinesAlias() {

		for (Object i : tablePmtLine.getItemIds()) {

			PaymentLineResp pline = (PaymentLineResp) (tablePmtLine.getContainerProperty(i, "..").getValue());

			int idx = 0;
			boolean found = false;
			for (CalculatedAlias c : calAliasList) {
				if (c.getAlias().equals(pline.getAlias())) {
					found = true;
					break;
				}
				idx++;
			}

			if (found) {
				calAliasList.remove(idx);
			}

		}

	}

	void searchCustomer(String txt, String type) {

		int i = -1;

		SimMTCustomerResponse customer;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation/"
					+ (type.equals("NIC") ? ("CustomerByNic/") : ("CustomerByPhone/")) + txt);
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

			if (i == 200) {

				customer = gson.fromJson(getRes, SimMTCustomerResponse.class);

				txtCusInitName
						.setValue(customer.getCustomerInitialName() == null ? "" : customer.getCustomerInitialName());
				txtCusLastName.setValue(customer.getCustomerLastName() == null ? "" : customer.getCustomerLastName());
				txtCusAddressNo
						.setValue(customer.getCustomerAddressNo() == null ? "" : customer.getCustomerAddressNo());
				txtCusArea.setValue(customer.getCustomerAddressArea() == null ? "" : customer.getCustomerAddressArea());
				txtCusStreet.setValue(
						customer.getCustomerAddressStreet() == null ? "" : customer.getCustomerAddressStreet());
				comboCusCity.setValue(customer.getCustomerAddressCity());

				txtNIC.setValue(customer.getCustomerNic() == null ? "" : customer.getCustomerNic());
				txtMobile.setValue(customer.getCustomerPhone() == null ? "" : customer.getCustomerPhone());

				cusId = customer.getId();
			}

		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
//			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		} finally {
//			if (i == 500) {
//				Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
//			} else if (i == 400) {
//				Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
//			} else if (i == 404) {
//				Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
//			}
		}
	}
	
	void loadYOM() {

		int thisYear = Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(0, 4));

		for (int i = 1951; i <= thisYear; i++) {

			comboYOM.addItem(i + "");

		}

	}

	void loadCities() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "City/Active");
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

			if (i == 200) {

				cities = gson.fromJson(getRes, new TypeToken<List<City>>() {
				}.getType());

				if (cities != null) {
					if (cities.size() != 0) {

						comboCusCity.removeAllItems();
						for (City city : cities) {
							comboCusCity.addItem(city.getName());
							mapCities.put(city.getName(), city);
						}

					}
				}
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
			}
		}

	}

	void loadCoveTypes() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "PeCoverType/Active");
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

			if (i == 200) {

				peCoverTypes = gson.fromJson(getRes, new TypeToken<List<PeCoverType>>() {
				}.getType());

				if (peCoverTypes != null) {
					if (peCoverTypes.size() != 0) {

						comboCoverType.removeAllItems();
						for (PeCoverType covertype : peCoverTypes) {
							comboCoverType.addItem(covertype.getName());
							mapCoverTypes.put(covertype.getName(), covertype);
						}

					}
				}
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
			}
		}

	}

	void loadPolCats() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "MotorPolicyQuotations/PolicyCategory");
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

			if (i == 200) {

				pePolcats = gson.fromJson(getRes, new TypeToken<List<PePolicyCategory>>() {
				}.getType());

				if (pePolcats != null) {
					if (pePolcats.size() != 0) {

						comboPolCategory.removeAllItems();
						for (PePolicyCategory polCat : pePolcats) {
							comboPolCategory.addItem(polCat.getPeObjectName());
							mapPePolcats.put(polCat.getPeObjectName(), polCat);
						}

					}
				}
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
			}
		}

	}

	void loadPolUs() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "MotorPolicyQuotations/Usage");
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

			if (i == 200) {

				pePolUs = gson.fromJson(getRes, new TypeToken<List<PePolicyUsage>>() {
				}.getType());

				if (pePolUs != null) {
					if (pePolUs.size() != 0) {

						comboPolUsage.removeAllItems();
						for (PePolicyUsage polUs : pePolUs) {
							comboPolUsage.addItem(polUs.getUsageName());
							mapPePolUs.put(polUs.getUsageName(), polUs);
						}

					}
				}
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
			}
		}

	}

	void loadProducts(String usageId) {

		int i = -1;
		System.out.println("usage " + usageId);
		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "PeProducts/ProductName?MktInsuranceClassId=161&PeObjectId=" + usageId);
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

			if (i == 200) {

				peProdList = gson.fromJson(getRes, new TypeToken<List<PeProductListItem>>() {
				}.getType());

				if (peProdList != null) {
					if (peProdList.size() != 0) {

						comboProduct.removeAllItems();
						for (PeProductListItem proDruct : peProdList) {
							comboProduct.addItem(proDruct.getPeCategoryName());
							mapPeProdList.put(proDruct.getPeCategoryName(), proDruct);
						}

					}
				}
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
				Notification.show("Error", "No Records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void loadPackages(String productCatId) {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "PeProducts/PackageName?peProductCatId=" + productCatId);
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

			if (i == 200) {

				pePackage = gson.fromJson(getRes, new TypeToken<List<PePackage>>() {
				}.getType());

				if (pePackage != null) {
					if (pePackage.size() != 0) {

						comboPePackageName.removeAllItems();
						for (PePackage pePkg : pePackage) {
							comboPePackageName.addItem(pePkg.getPackageName());
							mapPePackage.put(pePkg.getPackageName(), pePkg);
						}
						
						comboPePackageName.select("BOC");

					}
				}
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
				Notification.show("Error", "No Records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void loadLocation() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "location/active");
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

			if (i == 200) {

				locations = gson.fromJson(getRes, new TypeToken<List<Location>>() {
				}.getType());

				if (locations != null) {
					if (locations.size() != 0) {

						comboLocation.removeAllItems();
						for (Location loc : locations) {
							comboLocation.addItem(loc.getInitialName().toUpperCase());
							mapLocations.put(loc.getInitialName().toUpperCase(), loc);
						}

					}
				}

				comboLocation.select(user.getUser().getLocation().getInitialName());

				if (!new RoleCheck().isAdmin(user)) {
					comboLocation.setEnabled(false);
				}

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
			}
		}

	}
	
//	void loadVehiTypes() {
//
//		int i = -1;
//
//		try {
//
//			Gson gson = new Gson();
//			HttpClient client = HttpClientBuilder.create().build();
//			HttpGet request = new HttpGet(ConstantData.baseUrl + "PeObject/PeObjectCategoryId?PeObjectCategoryId="
//					+ ConstantData.vehicleTypeId);
//			request.setHeader("Accept", "application/json");
//			request.setHeader("Content-Type", "application/json");
//			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//			HttpResponse response = client.execute(request);
//			InputStream inputStream = response.getEntity().getContent();
//			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//			String inputLine;
//			String getRes = "";
//			while ((inputLine = in.readLine()) != null) {
//				getRes = getRes + inputLine + "\n";
//			}
//			in.close();
//			i = response.getStatusLine().getStatusCode();
//
//			if (i == 200) {
//
//				vehiType = gson.fromJson(getRes, new TypeToken<List<VehicleType>>() {
//				}.getType());
//
//				if (vehiType != null) {
//					if (vehiType.size() != 0) {
//
//						comboVehType.removeAllItems();
//						for (VehicleType vhTy : vehiType) {
//							comboVehType.addItem(vhTy.getName());
//							mapVehiType.put(vhTy.getName(), vhTy);
//						}
//
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
//		} finally {
//			if (i == 500) {
//				Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
//			} else if (i == 400) {
//				Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
//			} else if (i == 404) {
//				Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
//			}
//		}
//
//	}

	void loadPeriodOfCover() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "PeObject/PeObjectCategoryId?PeObjectCategoryId="
					+ ConstantData.periodOfCoverId);
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

			if (i == 200) {

				periodCover = gson.fromJson(getRes, new TypeToken<List<PeriodOfCover>>() {
				}.getType());

				if (periodCover != null) {
					if (periodCover.size() != 0) {

						comboPeriodOfCover.removeAllItems();
						for (PeriodOfCover vhTy : periodCover) {
							comboPeriodOfCover.addItem(vhTy.getName());
							mapPeriodCover.put(vhTy.getName(), vhTy);
						}

					}
				}
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
			}
		}

	}

	void loadMakeModels() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "PeObject/PeObjectCategoryId?PeObjectCategoryId=82");
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

			if (i == 200) {

				makeModel = gson.fromJson(getRes, new TypeToken<List<MakeModel>>() {
				}.getType());

				if (makeModel != null) {
					if (makeModel.size() != 0) {

						comboMakeModel.removeAllItems();
						for (MakeModel vhTy : makeModel) {
							comboMakeModel.addItem(vhTy.getName());
							mapMakeModel.put(vhTy.getName(), vhTy);
						}

					}
				}
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
			}
		}

	}

	void loadCovers(String productId) {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "MotorPolicyQuotations/Simplified/Calc/GetCovers?productId=" + productId);
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

			if (i == 200) {
				System.out.println("SimQuotationSub.loadCovers()");
				covers = gson.fromJson(getRes, new TypeToken<List<Cover>>() {
				}.getType());

				System.out.println(covers);

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
			}
		}

	}

	void loadNetRate(String productId) {

		List<NetRate> rates = null;

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "PePackageRates/Active?PeProductId=" + productId);
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

			if (i == 200) {
				System.out.println("SimQuotationSub.loadNetRate()");
				rates = gson.fromJson(getRes, new TypeToken<List<NetRate>>() {
				}.getType());

				if(rates != null && rates.size() != 0) {
					lblDefNetRate.setValue(new DecimalFormat("0.000").format(rates.get(0).getNetRate()));
				}
				
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
				Notification.show("Error", "No Net Rate records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void loadDsounts(String productId) {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "MotorPolicyQuotations/Simplified/Calc/GetDiscounts?productId=" + productId);
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

			if (i == 200) {
				System.out.println("SimQuotationSub.loadDsounts()");
				discounts = gson.fromJson(getRes, new TypeToken<List<Discount>>() {
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
				//Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void loadLoading(String productId) {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "MotorPolicyQuotations/Simplified/Calc/GetLoadings?productId=" + productId);
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

			if (i == 200) {
				System.out.println("SimQuotationSub.loadLoading()");
				loadings = gson.fromJson(getRes, new TypeToken<List<Loading>>() {
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
				//Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void loadPmtLines() {

		int i = -1;

		try {

			String productId = (mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId();

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "PeProdPmntLine/PeProductId?PeProductId=" + productId);
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

			if (i == 200) {

				paymentLines = gson.fromJson(getRes, new TypeToken<List<PaymentLines>>() {
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
			}
		}

	}

	String getPmtLineAlias(String id) {

		String alias = null;

		int i = -1;

		try {
			PaymentLines lines = null;

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "/PePaymentLine/" + id);
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

			if (i == 200) {

				lines = gson.fromJson(getRes, PaymentLines.class);

				if (lines != null) {
					alias = lines.getAlias();
				}
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
			}
		}

		return alias;
	}

	void calPaymntLine() {

		try {
			tablePmtLine.removeAllItems();
			if (paymentLines != null) {

				Collections.sort(paymentLines);

				for (PaymentLines pmtLn : paymentLines) {

					QtCalRequest calRequest = new QtCalRequest();
					calRequest.setAlias(getPmtLineAlias(pmtLn.getPePaymentLineId()));
					calRequest.setId(pmtLn.getPePaymentLineId());
					calRequest.setCalculatedAlias(calAliasList);

					Gson gson = new Gson();
					System.out.println("Pmt line req : " + gson.toJson(calRequest));
					PaymentLineResp lineResp = getCalculatedPmntLn(gson.toJson(calRequest));
					lineResp.setIsDisplayed(pmtLn.getIsDisplayed());

					if (lineResp != null) {
						if (lineResp.getValue() != null) {
							CalculatedAlias alias = new CalculatedAlias();
							alias.setAlias(lineResp.getAlias());
							alias.setValue(lineResp.getValue());
							addCalAlias(alias);

							if (lineResp.getName().equals("Net Premium")) {
								lblNetPremium.setValue(new DecimalFormat("#,##0.00").format(lineResp.getValue()));
							} else if (lineResp.getName().equals("Total Premium")) {
								lblTotPremium.setValue(new DecimalFormat("#,##0.00").format(lineResp.getValue()));
							} else {
								
								tablePmtLine.addItem(new Object[] { lineResp.getName(),
										new DecimalFormat("0.00").format(lineResp.getValue()), pmtLn.getOrderNo(),
										lineResp }, tablePmtLine.size());
							}

						} else {
							// System.out.println("value null");
						}
					}

				}
				tablePmtLine.sort();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}

	}

	PaymentLineResp getCalculatedPmntLn(String cal) {

		int i = 0;

		PaymentLineResp lineResp = null;

		try {

			String productId = (mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId();

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(ConstantData.baseUrl
					+ "MotorPolicyQuotations/Simplified/Calc/PremiumCalculate?productId=" + productId);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			StringEntity body = new StringEntity(cal);
			request.setEntity(body);

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

			if (i == 200) {
				System.out.println("Pmt Line resp : " + getRes);
				lineResp = gson.fromJson(getRes, PaymentLineResp.class);

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
			}
		}

		return lineResp;
	}

	void loadTable() {

		try {

			for (Cover cvr : covers) {

				int column = tablePerils.size() == 0 ? 0
						: ((int) tablePerils.getItemIds().toArray()[tablePerils.size() - 1] + 1);

				VerticalLayout layout = new VerticalLayout();

				HashMap<String, CoverRange> rangeList = new HashMap<>();

				if (cvr.getPeCoverValueBasisAlias().equals("VSC")) {

					TextField txt = new TextField();
					txt.setCaption("Value");
					txt.setWidth("100px");
					txt.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					TextField txtS = new TextField();
					txtS.setCaption("Seat Count");
					txtS.setWidth("100px");
					txtS.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					layout.addComponent(txt, 0);
					layout.addComponent(txtS, 1);

				} else if (cvr.getPeCoverValueBasisAlias().equals("UEV")) {

					TextField txt = new TextField();
					txt.setWidth("100px");
					txt.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					layout.addComponent(txt, 0);

				} else if (cvr.getPeCoverValueBasisAlias().equals("RNG")) {

					ComboBox combo = new ComboBox();
					combo.setWidth("100px");

					for (CoverRange range : getCoverRanges(cvr.getId())) {

						if (range.getStatus().equals("ACTIVE") && range.getIsDeleted().equals("NO")) {
							combo.addItem(range.getRangeName());
							rangeList.put(range.getRangeName(), range);
						}

					}
					combo.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					layout.addComponent(combo, 0);

				} else if (cvr.getPeCoverValueBasisAlias().equals("RSC")) {

					ComboBox combo = new ComboBox();
					combo.setCaption("Range");
					combo.setWidth("100px");
					for (CoverRange range : getCoverRanges(cvr.getId())) {

						if (range.getStatus().equals("ACTIVE") && range.getIsDeleted().equals("NO")) {
							combo.addItem(range.getRangeName());
							rangeList.put(range.getRangeName(), range);
						}
					}
					combo.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					TextField txtS = new TextField();
					txtS.setCaption("Seat Count");
					txtS.setWidth("100px");
					txtS.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub
							calOne("cover", cvr, null, null, column);
							calculate();
						}
					});

					layout.addComponent(combo, 0);
					layout.addComponent(txtS, 1);

				} else {

					Label lbl = new Label();
					lbl.setWidth("100px");
					layout.addComponent(lbl, 0);

				}

				Label lblName = new Label(cvr.getPeCoverName());

				CheckBox chbSel = new CheckBox();
				chbSel.addValueChangeListener(new ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						// TODO Auto-generated method stub

						Label lbn = (Label) tablePerils.getContainerProperty(column, "Name").getValue();

						if (chbSel.getValue()) {
							Label newLbl = new Label(lbn.getValue());
							newLbl.addStyleName("coverTextGreen");
							tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
						} else {
							Label newLbl = new Label(lbn.getValue());
							tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
						}
						calOne("cover", cvr, null, null, column);
						calculate();
					}
				});

				tablePerils.addItem(new Object[] { tablePerils.size() + 1, chbSel, "Cover", lblName, layout, "0.00",
						cvr.getCalculationOrder(), cvr, rangeList }, column);

			}

			if(discounts!=null) {
				for (Discount dsc : discounts) {

					int column = tablePerils.size() == 0 ? 0
							: ((int) tablePerils.getItemIds().toArray()[tablePerils.size() - 1] + 1);

					VerticalLayout layout = new VerticalLayout();

					HashMap<String, DiscountRange> rangeList = new HashMap<>();

					if (dsc.getIsRange() == 1) {

						ComboBox combo = new ComboBox();
						combo.setWidth("100px");

						for (DiscountRange range : dsc.getPeDiscountRangeResponses()) {
							combo.addItem(range.getRangeName());
							rangeList.put(range.getRangeName(), range);
						}
						combo.addValueChangeListener(new ValueChangeListener() {

							@Override
							public void valueChange(ValueChangeEvent event) {
								// TODO Auto-generated method stub
								calOne("discount", null, dsc, null, column);
								calculate();
							}
						});

						layout.addComponent(combo, 0);

					} else {

						Label lbl = new Label();
						lbl.setWidth("100px");
						layout.addComponent(lbl, 0);

					}

					Label lblName = new Label(dsc.getPeDiscountName());

					CheckBox chbSel = new CheckBox();
					chbSel.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub

							Label lbn = (Label) tablePerils.getContainerProperty(column, "Name").getValue();

							if (chbSel.getValue()) {
								Label newLbl = new Label(lbn.getValue());
								newLbl.addStyleName("discTextOrange");
								tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
							} else {
								Label newLbl = new Label(lbn.getValue());
								tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
							}
							calOne("discount", null, dsc, null, column);
							calculate();
						}
					});

					tablePerils.addItem(new Object[] { tablePerils.size() + 1, chbSel, "Discount", lblName, layout, "0.00",
							dsc.getCalculationOrder(), dsc, rangeList }, column);

				}
			}
			

			if(loadings!=null) {
				for (Loading ldn : loadings) {

					int column = tablePerils.size() == 0 ? 0
							: ((int) tablePerils.getItemIds().toArray()[tablePerils.size() - 1] + 1);

					VerticalLayout layout = new VerticalLayout();

					HashMap<String, LoadingRange> rangeList = new HashMap<>();

					if (ldn.getIsRange() == 1 || ldn.getPeLoadingRangeResponses() != null) {

						ComboBox combo = new ComboBox();
						combo.setWidth("100px");

						for (LoadingRange range : ldn.getPeLoadingRangeResponses()) {
							combo.addItem(range.getRangeName());
							rangeList.put(range.getRangeName(), range);
						}

						combo.addValueChangeListener(new ValueChangeListener() {

							@Override
							public void valueChange(ValueChangeEvent event) {
								// TODO Auto-generated method stub
								calOne("loading", null, null, ldn, column);
								calculate();
							}
						});

						layout.addComponent(combo, 0);

					} else {

						Label lbl = new Label();
						lbl.setWidth("100px");
						layout.addComponent(lbl, 0);

					}

					Label lblName = new Label(ldn.getPeLoadingName());

					CheckBox chbSel = new CheckBox();
					chbSel.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(ValueChangeEvent event) {
							// TODO Auto-generated method stub

							Label lbn = (Label) tablePerils.getContainerProperty(column, "Name").getValue();

							if (chbSel.getValue()) {
								Label newLbl = new Label(lbn.getValue());
								newLbl.addStyleName("loadTextBlue");
								tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
							} else {
								Label newLbl = new Label(lbn.getValue());
								tablePerils.getContainerProperty(column, "Name").setValue(newLbl);
							}
							calOne("loading", null, null, ldn, column);
							calculate();
						}
					});

					tablePerils.addItem(new Object[] { tablePerils.size() + 1, chbSel, "Loading", lblName, layout, "0.00",
							ldn.getCalculationOrder(), ldn, rangeList }, column);

				}
			}
			

			tablePerils.sort();
			reNumberTable();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}

	}

	void reNumberTable() {

		int i = 1;
		for (Object col : tablePerils.getItemIds()) {
			tablePerils.getContainerProperty(col, "#").setValue(i);
			i++;
		}

	}

	boolean validated() {

//		if (txtCusInitName.getValue().trim().isEmpty() || txtCusLastName.getValue().trim().isEmpty()
//				|| comboCusCity.getValue() == null) {
//			Notification.show("Enter/Search the details of Customer", Notification.TYPE_ERROR_MESSAGE);
//			return false;
//		}

		if (comboCoverType.getValue() == null || comboPolCategory.getValue() == null || comboPolUsage.getValue() == null
				|| comboLocation.getValue() == null || comboPeriodOfCover.getValue() == null) {
			Notification.show("Select the mandatory Policy details", Notification.TYPE_ERROR_MESSAGE);
			return false;
		}

//		if (txtVehLett.getValue().trim().isEmpty() || txtVehNos.getValue().trim().isEmpty()) {
//			Notification.show("Enter the name Vehicle Number", Notification.TYPE_ERROR_MESSAGE);
//			return false;
//		}
//		if (optionGroupPolicyType.getValue().toString().equals("Non-Fleet")) {
		if (optionGroupRegNonReg.getValue().toString().equals("Registered")) {
			if (txtVehLett.getValue().trim().isEmpty() || txtVehNos.getValue().trim().isEmpty()) {
				Notification.show("Enter the Vehicle Number", Notification.TYPE_ERROR_MESSAGE);
				return false;
			}
		} else {
			if (txtChassis.getValue().trim().isEmpty()) {
				Notification.show("Enter the Chassis Number", Notification.TYPE_ERROR_MESSAGE);
				return false;
			}
		}

		if (txtSumInsured.getValue().trim().isEmpty() 
				|| txtSeatCount.getValue().trim().isEmpty()
				) {
			Notification.show("Select the mandatory Risk details", Notification.TYPE_ERROR_MESSAGE);
			return false;
		}

//		}

//		if (!txtNIC.getValue().trim().isEmpty()) {
//
//			if (txtNIC.getValue().trim().length() == 10) {
//				if (!(txtNIC.getValue().trim().toLowerCase().endsWith("v")
//						|| txtNIC.getValue().trim().toLowerCase().endsWith("x"))) {
//
//					Notification.show("NIC is invalid", Notification.TYPE_ERROR_MESSAGE);
//					return false;
//
//				}
//
//				int ch = 0;
//				for (char c : txtNIC.getValue().trim().substring(0, 9).toCharArray()) {
//					if (!(c >= '0' && c <= '9')) {
//						ch++;
//					}
//				}
//				if (ch != 0) {
//					Notification.show("NIC is invalid", Notification.TYPE_ERROR_MESSAGE);
//					return false;
//				}
//
//			} else if (txtNIC.getValue().trim().length() == 12) {
//				int ch = 0;
//				for (char c : txtNIC.getValue().trim().substring(0, 9).toCharArray()) {
//					if (!(c >= '0' && c <= '9')) {
//						ch++;
//					}
//				}
//				if (ch != 0) {
//					Notification.show("NIC is invalid", Notification.TYPE_ERROR_MESSAGE);
//					return false;
//				}
//			} else {
//				Notification.show("NIC is invalid", Notification.TYPE_ERROR_MESSAGE);
//				return false;
//			}
//
//		}

//		if (!txtMobile.getValue().trim().isEmpty()) {
//
//			if (txtMobile.getValue().trim().length() == 10) {
//				int ch = 0;
//				for (char c : txtMobile.getValue().trim().toCharArray()) {
//
//					if (!(c >= '0' && c <= '9')) {
//						ch++;
//					}
//				}
//				if (ch != 0) {
//					Notification.show("Mobile number is invalid", Notification.TYPE_ERROR_MESSAGE);
//					return false;
//				}
//			} else {
//				Notification.show("Mobile number is invalid", Notification.TYPE_ERROR_MESSAGE);
//				return false;
//			}
//
//		}

		return true;
	}

	void addCalAlias(CalculatedAlias cal) {

		int ch = 0;
		for (CalculatedAlias c : calAliasList) {

			if (c.getAlias().equals(cal.getAlias())) {
				c.setValue(cal.getValue());
				ch = 1;
			}

		}
		if (ch == 0) {
			calAliasList.add(cal);
		}

	}

	void setAliasZero(String al) {

		for (CalculatedAlias c : calAliasList) {

			if (c.getAlias().equals(al)) {
				c.setValue(0.0);
				break;
			}

		}
	}

	void calOne(String type, Cover cover, Discount disc, Loading ldn, Object col) {

//		if (!validated()) {
//			return;
//		}

		try {

			CheckBox chb = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
			if (!chb.getValue()) {
				if (type.equalsIgnoreCase("cover")) {
					setAliasZero(cover.getAlias());
				} else if (type.equalsIgnoreCase("discount")) {
					setAliasZero(disc.getAlias());
				} else if (type.equalsIgnoreCase("loading")) {
					setAliasZero(ldn.getAlias());
				}
				return;
			}

			CalculatedAlias cal = new CalculatedAlias();
			cal.setAlias("SUMINSU");
			cal.setValue(Double.parseDouble(txtSumInsured.getValue().trim()));
			// cal.setType("peril");
			addCalAlias(cal);

			String pocAlias = mapPeriodCover.get(comboPeriodOfCover.getValue().toString()).getAlias();

			if (type.equalsIgnoreCase("cover")) {

				QtCalRequest calReq = new QtCalRequest();
				calReq.setAlias(cover.getAlias());
				calReq.setType("CVR");
				calReq.setCalBasisAlias(cover.getPeCoverCalBasisAlias());
				calReq.setValueBasisAlias(cover.getPeCoverValueBasisAlias());
				calReq.setId(cover.getId());
				calReq.setPeriodOfCoverAlias(pocAlias);
				calReq.setProductId((mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

				calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
				calReq.setCalculatedAlias(calAliasList);

				if (cover.getPeCoverValueBasisAlias().equals("VSC")
						|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

					TextField txtF = (TextField) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
							.getValue()).getComponent(0);

					if (!txtF.getValue().trim().isEmpty()) {
						calReq.setUev(Double.parseDouble(txtF.getValue().trim()));
					}

					if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

						TextField txtSc = (TextField) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
								.getValue()).getComponent(1);

						if (!txtSc.getValue().trim().isEmpty()) {
							calReq.setSeatCount(Integer.parseInt(txtSc.getValue()));
						}
					}
				} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
						|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

					ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
							.getValue()).getComponent(0);

					// set selected range value
					if (cmb.getValue() != null) {

						HashMap<String, CoverRange> cvrRanges = (HashMap) tablePerils.getContainerProperty(col, "rv")
								.getValue();
						double minAmt = 0;
						if (cvrRanges.get(cmb.getValue()).getMinimumAmount() == 0) {
							minAmt = cvrRanges.get(cmb.getValue()).getMaximumAmount();
						} else {
							minAmt = cvrRanges.get(cmb.getValue()).getMinimumAmount();
						}
						calReq.setAmount(minAmt);

						if (minAmt == 0) {
							calReq.setRange(cvrRanges.get(cmb.getValue()).getRate());
						}

					}

					if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

						TextField txtSc = (TextField) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
								.getValue()).getComponent(1);

						if (!txtSc.getValue().trim().isEmpty()) {
							calReq.setSeatCount(Integer.parseInt(txtSc.getValue()));
						}
					}
				}
				Gson gson = new Gson();
				System.out.println("cal one --> " + gson.toJson(calReq));
				CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

				if (gotCals == null) {
					Notification.show("Error", "Calculation Error, Check cover dependants",
							Notification.TYPE_ERROR_MESSAGE);

				} else {
					if (gotCals.getValue() == null) {

						Notification.show("Couldn't calculate, Check cover dependants");

					} else {

						addCalAlias(gotCals);
						tablePerils.getContainerProperty(col, "Total")
								.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

					}
				}

			} else if (type.equalsIgnoreCase("discount")) {

				QtCalRequest calReq = new QtCalRequest();
				calReq.setAlias(disc.getAlias());
				calReq.setType("DSC");
				// calReq.setCalBasisAlias(disc.getPeCoverCalBasisAlias());
				// calReq.setValueBasisAlias(disc.getPeCoverValueBasisAlias());
				calReq.setId(disc.getId());
				// calReq.setPeriodOfCoverAlias(pocAlias);
				calReq.setProductId((mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

				calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
				// calReq.setShortPeriodName(comboPeriodOfCover.getValue().toString());
				calReq.setCalculatedAlias(calAliasList);

				if (disc.getAlias().equals("DVO") || disc.getAlias().equals("DVM")) {
					// Voluntary excess discount
					ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
							.getValue()).getComponent(0);

					List<Double> rateList = new ArrayList<>();
					List<Double> valueList = new ArrayList<>();

					HashMap<String, DiscountRange> cvrRanges = (HashMap) tablePerils.getContainerProperty(col, "rv")
							.getValue();

					for (Object item : cmb.getItemIds()) {

						if (item != null) {
							if (item.toString().endsWith("%")) {
								rateList.add(cvrRanges.get(item.toString()).getRangeRate());
							} else {
								valueList.add(cvrRanges.get(item.toString()).getMinimumAmount());
							}
						}

					}
					Collections.sort(rateList);
					Collections.sort(valueList);

					if (cmb.getValue() == null) {
						Notification.show("Couldn't calculate, Check discount dependants");
						return;
					}

					String selectedVal = cmb.getValue().toString();

					double amtVal = 0;
					double rateVal = 0;

					CalculatedAlias gotCals = null;

					CalculatedAlias finalCal = new CalculatedAlias();

					if (selectedVal.endsWith("%")) {

						double selVal = cvrRanges.get(selectedVal).getRangeRate();

						calReq.setRange(selVal);

						Gson gson = new Gson();
						System.out.println(gson.toJson(calReq));
						gotCals = getCalValues(gson.toJson(calReq));

						if (gotCals != null && gotCals.getValue() != null) {
							rateVal = gotCals.getValue();

							calReq.setRange(0.0);
							calReq.setAmount(valueList.get(rateList.indexOf(selVal)));
							System.out.println(gson.toJson(calReq));
							amtVal = getCalValues(gson.toJson(calReq)).getValue();

							finalCal.setAlias(gotCals.getAlias());

							if (rateVal < amtVal) {
								finalCal.setValue(rateVal);
							} else {
								finalCal.setValue(amtVal);
							}
						}

					} else {

						double selVal = cvrRanges.get(selectedVal).getMinimumAmount();

						calReq.setRange(rateList.get(valueList.indexOf(selVal)));

						Gson gson = new Gson();
						System.out.println(gson.toJson(calReq));
						gotCals = getCalValues(gson.toJson(calReq));

						if (gotCals != null && gotCals.getValue() != null) {
							rateVal = gotCals.getValue();

							calReq.setRange(0.0);
							calReq.setAmount(selVal);
							System.out.println(gson.toJson(calReq));
							amtVal = getCalValues(gson.toJson(calReq)).getValue();

							finalCal.setAlias(gotCals.getAlias());

							if (rateVal < amtVal) {
								finalCal.setValue(rateVal);
							} else {
								finalCal.setValue(amtVal);
							}
						}
					}

					if (gotCals == null) {
						Notification.show("Error", "Calculation Error, Check discount dependants",
								Notification.TYPE_ERROR_MESSAGE);

					} else {
						if (gotCals.getValue() == null) {
							Notification.show("Couldn't calculate, Check discount dependants");

						} else {

							addCalAlias(finalCal);
							tablePerils.getContainerProperty(col, "Total")
									.setValue(new DecimalFormat("0.00").format(finalCal.getValue()));

						}
					}
				} else {

					if (disc.getIsRange() == 1) {

						ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
								.getValue()).getComponent(0);

						// set selected range value
						if (cmb.getValue() != null) {

							HashMap<String, DiscountRange> cvrRanges = (HashMap) tablePerils
									.getContainerProperty(col, "rv").getValue();

							calReq.setRange(cvrRanges.get(cmb.getValue()).getRangeRate());

						}

					}

					Gson gson = new Gson();
					System.out.println(gson.toJson(calReq));
					CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

					if (gotCals == null) {
						Notification.show("Error", "Calculation Error, Check discount dependants",
								Notification.TYPE_ERROR_MESSAGE);

					} else {
						if (gotCals.getValue() == null) {
							Notification.show("Couldn't calculate, Check discount dependants");

						} else {

							addCalAlias(gotCals);
							tablePerils.getContainerProperty(col, "Total")
									.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

						}
					}
				}
			} else if (type.equalsIgnoreCase("loading")) {

				// Loading calculation

				QtCalRequest calReq = new QtCalRequest();
				calReq.setAlias(ldn.getAlias());
				calReq.setType("LDN");
				// calReq.setCalBasisAlias(disc.getPeCoverCalBasisAlias());
				// calReq.setValueBasisAlias(disc.getPeCoverValueBasisAlias());
				calReq.setId(ldn.getId());
				// calReq.setPeriodOfCoverAlias(pocAlias);
				calReq.setProductId((mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

				calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
				// calReq.setShortPeriodName(comboPeriodOfCover.getValue().toString());
				calReq.setCalculatedAlias(calAliasList);

				if (ldn.getIsRange() == 1 || ldn.getPeLoadingRangeResponses() != null) {

					ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
							.getValue()).getComponent(0);

					// set selected range value
					if (cmb.getValue() != null) {

						HashMap<String, LoadingRange> cvrRanges = (HashMap) tablePerils.getContainerProperty(col, "rv")
								.getValue();

						calReq.setRange(cvrRanges.get(cmb.getValue()).getRangeRate());

					}

				}

				Gson gson = new Gson();
				System.out.println(gson.toJson(calReq));
				CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

				if (gotCals == null) {

					Notification.show("Error", "Calculation Error, Check loading dependants",
							Notification.TYPE_ERROR_MESSAGE);
					tablePerils.getContainerProperty(col, "Total").setValue("");

				} else {
					if (gotCals.getValue() == null) {
						Notification.show("Couldn't calculate, Check loading dependants");
						tablePerils.getContainerProperty(col, "Total").setValue("");

					} else {

						addCalAlias(gotCals);
						tablePerils.getContainerProperty(col, "Total")
								.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	void loadCNReasons() {

		int i = -1;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "UwCoverNoteReason");
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

			if (i == 200) {

				cvrNoteReasons = gson.fromJson(getRes, new TypeToken<List<CoverNoteReason>>() {
				}.getType());

				tableCNReason.removeAllItems();
				if (cvrNoteReasons != null) {
					for (CoverNoteReason cvrNoteReason : cvrNoteReasons) {
						tableCNReason.addItem(new Object[] { new CheckBox(), cvrNoteReason.getReasonName(), cvrNoteReason.getId() }, tableCNReason.size());
					}
				}

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
			}
		}

	}

	void calculate() {

		if (!validated()) {
			return;
		}

		try {

			String pocAlias = mapPeriodCover.get(comboPeriodOfCover.getValue().toString()).getAlias();

			// calAliasList = new ArrayList<>();
			CalculatedAlias cal = new CalculatedAlias();
			cal.setAlias("SUMINSU");

			cal.setValue(Double.parseDouble(txtSumInsured.getValue().trim()));

			addCalAlias(cal);

			String prov = "";
			if (comboVehProv.getValue() != null) {
				prov = comboVehProv.getValue().toString();
			}

			if (optionGroupRegNonReg.getValue().toString().equals("Registered")) {
				vehicleNo = VehiNoPatternValidator.generate(prov, txtVehLett.getValue(), txtVehNos.getValue());

			} else {
				vehicleNo = "U/R " + txtChassis.getValue();
			}

			for (Object col : tablePerils.getItemIds()) {

				CheckBox chb = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
				if (chb.getValue()) {

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					// Cover calculation

					if (type.equals("Cover")) {

						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();

						QtCalRequest calReq = new QtCalRequest();
						calReq.setAlias(cover.getAlias());
						calReq.setType("CVR");
						calReq.setCalBasisAlias(cover.getPeCoverCalBasisAlias());
						calReq.setValueBasisAlias(cover.getPeCoverValueBasisAlias());
						calReq.setId(cover.getId());
						calReq.setPeriodOfCoverAlias(pocAlias);
						calReq.setProductId(
								(mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

						calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
						// calReq.setShortPeriodName(comboPeriodOfCover.getValue().toString());
						calReq.setCalculatedAlias(calAliasList);

						if (cover.getPeCoverValueBasisAlias().equals("VSC")
								|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

							TextField txtF = (TextField) ((VerticalLayout) tablePerils
									.getContainerProperty(col, "Value").getValue()).getComponent(0);

							if (!txtF.getValue().trim().isEmpty()) {
								calReq.setUev(Double.parseDouble(txtF.getValue().trim()));
							}

							if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);

								if (!txtSc.getValue().trim().isEmpty()) {
									calReq.setSeatCount(Integer.parseInt(txtSc.getValue()));
								}
							}
						} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
								|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							// set selected range value
							if (cmb.getValue() != null) {

								HashMap<String, CoverRange> cvrRanges = (HashMap) tablePerils
										.getContainerProperty(col, "rv").getValue();
								double minAmt = 0;
								if (cvrRanges.get(cmb.getValue()).getMinimumAmount() == 0) {
									minAmt = cvrRanges.get(cmb.getValue()).getMaximumAmount();
								} else {
									minAmt = cvrRanges.get(cmb.getValue()).getMinimumAmount();
								}
								calReq.setAmount(minAmt);

								if (minAmt == 0) {
									calReq.setRange(cvrRanges.get(cmb.getValue()).getRate());
								}

							}

							if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);

								if (!txtSc.getValue().trim().isEmpty()) {
									calReq.setSeatCount(Integer.parseInt(txtSc.getValue()));
								}
							}
						}

						Gson gson = new Gson();
						System.out.println(gson.toJson(calReq));
						CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

						if (gotCals == null) {
							Notification.show("Error", "Calculation Error, Check cover dependants",
									Notification.TYPE_ERROR_MESSAGE);
							break;
						} else {
							if (gotCals.getValue() == null) {

								Notification.show("Couldn't calculate, Check cover dependants");

								break;
							} else {

								addCalAlias(gotCals);
								tablePerils.getContainerProperty(col, "Total")
										.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

							}
						}

					} else if (type.equals("Discount")) {

						// Discount calculation
						Discount disc = (Discount) tablePerils.getContainerProperty(col, "..").getValue();

						QtCalRequest calReq = new QtCalRequest();
						calReq.setAlias(disc.getAlias());
						calReq.setType("DSC");
						// calReq.setCalBasisAlias(disc.getPeCoverCalBasisAlias());
						// calReq.setValueBasisAlias(disc.getPeCoverValueBasisAlias());
						calReq.setId(disc.getId());
						// calReq.setPeriodOfCoverAlias(pocAlias);
						calReq.setProductId(
								(mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

						calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
						// calReq.setShortPeriodName(comboPeriodOfCover.getValue().toString());
						calReq.setCalculatedAlias(calAliasList);

						if (disc.getAlias().equals("DVO") || disc.getAlias().equals("DVM")) {
							// Voluntary excess discount
							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							List<Double> rateList = new ArrayList<>();
							List<Double> valueList = new ArrayList<>();

							HashMap<String, DiscountRange> cvrRanges = (HashMap) tablePerils
									.getContainerProperty(col, "rv").getValue();

							for (Object item : cmb.getItemIds()) {

								if (item != null) {
									if (item.toString().endsWith("%")) {
										rateList.add(cvrRanges.get(item.toString()).getRangeRate());
									} else {
										valueList.add(cvrRanges.get(item.toString()).getMinimumAmount());
									}
								}

							}
							Collections.sort(rateList);
							Collections.sort(valueList);

							if (cmb.getValue() == null) {
								Notification.show("Couldn't calculate, Check discount dependants");
								return;
							}

							String selectedVal = cmb.getValue().toString();

							double amtVal = 0;
							double rateVal = 0;

							CalculatedAlias gotCals = null;

							CalculatedAlias finalCal = new CalculatedAlias();

							if (selectedVal.endsWith("%")) {

								double selVal = cvrRanges.get(selectedVal).getRangeRate();

								calReq.setRange(selVal);

								Gson gson = new Gson();
								System.out.println(gson.toJson(calReq));
								gotCals = getCalValues(gson.toJson(calReq));

								if (gotCals != null && gotCals.getValue() != null) {
									rateVal = gotCals.getValue();

									calReq.setRange(0.0);
									calReq.setAmount(valueList.get(rateList.indexOf(selVal)));
									System.out.println(gson.toJson(calReq));
									amtVal = getCalValues(gson.toJson(calReq)).getValue();

									finalCal.setAlias(gotCals.getAlias());

									if (rateVal < amtVal) {
										finalCal.setValue(rateVal);
									} else {
										finalCal.setValue(amtVal);
									}
								}

							} else {

								double selVal = cvrRanges.get(selectedVal).getMinimumAmount();

								calReq.setRange(rateList.get(valueList.indexOf(selVal)));

								Gson gson = new Gson();
								System.out.println(gson.toJson(calReq));
								gotCals = getCalValues(gson.toJson(calReq));

								if (gotCals != null && gotCals.getValue() != null) {
									rateVal = gotCals.getValue();

									calReq.setRange(0.0);
									calReq.setAmount(selVal);
									System.out.println(gson.toJson(calReq));
									amtVal = getCalValues(gson.toJson(calReq)).getValue();

									finalCal.setAlias(gotCals.getAlias());

									if (rateVal < amtVal) {
										finalCal.setValue(rateVal);
									} else {
										finalCal.setValue(amtVal);
									}
								}
							}

							if (gotCals == null) {
								Notification.show("Error", "Calculation Error, Check discount dependants",
										Notification.TYPE_ERROR_MESSAGE);
								break;
							} else {
								if (gotCals.getValue() == null) {
									Notification.show("Couldn't calculate, Check discount dependants");
									break;
								} else {

									addCalAlias(finalCal);
									tablePerils.getContainerProperty(col, "Total")
											.setValue(new DecimalFormat("0.00").format(finalCal.getValue()));

								}
							}
						} else {

							if (disc.getIsRange() == 1) {

								ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(0);

								// set selected range value
								if (cmb.getValue() != null) {

									HashMap<String, DiscountRange> cvrRanges = (HashMap) tablePerils
											.getContainerProperty(col, "rv").getValue();

									calReq.setRange(cvrRanges.get(cmb.getValue()).getRangeRate());

								}

							}

							Gson gson = new Gson();
							System.out.println(gson.toJson(calReq));
							CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

							if (gotCals == null) {
								Notification.show("Error", "Calculation Error, Check discount dependants",
										Notification.TYPE_ERROR_MESSAGE);
								break;
							} else {
								if (gotCals.getValue() == null) {
									Notification.show("Couldn't calculate, Check discount dependants");
									break;
								} else {

									addCalAlias(gotCals);
									tablePerils.getContainerProperty(col, "Total")
											.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

								}
							}
						}

					} else if (type.equals("Loading")) {

						// Loading calculation

						Loading disc = (Loading) tablePerils.getContainerProperty(col, "..").getValue();

						QtCalRequest calReq = new QtCalRequest();
						calReq.setAlias(disc.getAlias());
						calReq.setType("LDN");
						// calReq.setCalBasisAlias(disc.getPeCoverCalBasisAlias());
						// calReq.setValueBasisAlias(disc.getPeCoverValueBasisAlias());
						calReq.setId(disc.getId());
						// calReq.setPeriodOfCoverAlias(pocAlias);
						calReq.setProductId(
								(mapPePackage.get(comboPePackageName.getValue().toString())).getPackageId());

						calReq.setSeatCount(Integer.parseInt(txtSeatCount.getValue()));
						// calReq.setShortPeriodName(comboPeriodOfCover.getValue().toString());
						calReq.setCalculatedAlias(calAliasList);

						if (disc.getIsRange() == 1 || disc.getPeLoadingRangeResponses() != null) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							// set selected range value
							if (cmb.getValue() != null) {

								HashMap<String, LoadingRange> cvrRanges = (HashMap) tablePerils
										.getContainerProperty(col, "rv").getValue();

								calReq.setRange(cvrRanges.get(cmb.getValue()).getRangeRate());

							}

						}

						Gson gson = new Gson();
						System.out.println(gson.toJson(calReq));
						CalculatedAlias gotCals = getCalValues(gson.toJson(calReq));

						if (gotCals == null) {

							Notification.show("Error", "Calculation Error, Check loading dependants",
									Notification.TYPE_ERROR_MESSAGE);
							tablePerils.getContainerProperty(col, "Total").setValue("");
							break;

						} else {
							if (gotCals.getValue() == null) {
								Notification.show("Couldn't calculate, Check loading dependants");
								tablePerils.getContainerProperty(col, "Total").setValue("");
								break;
							} else {

								addCalAlias(gotCals);
								tablePerils.getContainerProperty(col, "Total")
										.setValue(new DecimalFormat("0.00").format(gotCals.getValue()));

							}
						}

					}

				} else {

					tablePerils.getContainerProperty(col, "Total").setValue("");

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					if (type.equals("Cover")) {

						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();
						CalculatedAlias zeroCal = new CalculatedAlias();
						zeroCal.setAlias(cover.getAlias());
						zeroCal.setValue(0.0);

						addCalAlias(zeroCal);

					} else if (type.equals("Discount")) {

						Discount disc = (Discount) tablePerils.getContainerProperty(col, "..").getValue();
						CalculatedAlias zeroCal = new CalculatedAlias();
						zeroCal.setAlias(disc.getAlias());
						zeroCal.setValue(0.0);

						addCalAlias(zeroCal);

					} else if (type.equals("Loading")) {

						Loading laod = (Loading) tablePerils.getContainerProperty(col, "..").getValue();
						CalculatedAlias zeroCal = new CalculatedAlias();
						zeroCal.setAlias(laod.getAlias());
						zeroCal.setValue(0.0);

						addCalAlias(zeroCal);

					}

				}

			}

		} catch (NullPointerException nex) {
			nex.printStackTrace();

			Notification.show("Couldn't calculate, Check peril dependants");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		} finally {
			genCalBreakDown();
		}

	}

	CalculatedAlias getCalValues(String cal) {

		int i = 0;

		CalculatedAlias calValues = null;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(ConstantData.baseUrl + "MotorPolicyQuotations/Simplified/Calc/Calculate");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			StringEntity body = new StringEntity(cal);
			request.setEntity(body);
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

			if (i == 200) {

				calValues = gson.fromJson(getRes, CalculatedAlias.class);

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
			}
		}

		return calValues;
	}

	List<CoverRange> getCoverRanges(String coverId) {
		int i = 0;

		List<CoverRange> coverRanges = null;

		Cover cover = null;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet((ConstantData.baseUrl + "PeCover/%7Bid%7D?id=" + coverId));
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

			if (i == 200) {

				cover = gson.fromJson(getRes, Cover.class);
				if (cover != null) {
					coverRanges = cover.getPeCoverRangeResponses();
				}

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
			}
		}

		return coverRanges;
	}

	void genCalBreakDown() {

		double netPre = 0;
		double srccTc = 0;
		double other_chrgs = 0;
		double life_protection = 0;

		for (Object i : tablePerils.getItemIds()) {

			CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(i, "+").getValue();

			if (chbSel.getValue()) {

				String name = ((Label) tablePerils.getContainerProperty(i, "Name").getValue()).getValue();

				String type = tablePerils.getContainerProperty(i, "Type").getValue().toString();

				String totalAmt = tablePerils.getContainerProperty(i, "Total").getValue().toString();

				if (type.equals("Cover")) {

					Cover cover = (Cover) tablePerils.getContainerProperty(i, "..").getValue();
					if (cover.getAlias().equals("OTHLPMT") || cover.getAlias().equals("OTHLPOV")) {
						life_protection = Double.parseDouble(totalAmt);
					}
				}

				if (!totalAmt.isEmpty()) {
					double amt = Double.parseDouble(totalAmt);

					if (type.equals("Cover") || type.equals("Loading")) {
						netPre = netPre + amt;
					} else if (type.equals("Discount")) {
						netPre = netPre - amt;
					}

					if (type.equals("Cover")) {
						if (name.contains("SRCC") || name.contains("TC")) {
							srccTc = srccTc + amt;
						}
					}

					if (name.toLowerCase().startsWith("other charg")) {
						other_chrgs = Double.parseDouble(totalAmt);
					}

				}

			}
		}

		netPre = netPre - life_protection;

		lblNetPremium.setValue(new DecimalFormat("#,##0.00").format(netPre));

		for (Object i : tablePmtLine.getItemIds()) {

			String name = (tablePmtLine.getContainerProperty(i, "Name").getValue()).toString();

			String totalAmt = tablePmtLine.getContainerProperty(i, "Amount").getValue().toString();

		}

		// net rate

		double sum = 0;

		sum = Double.parseDouble(txtSumInsured.getValue());

		double calNetRate = ((netPre - srccTc - other_chrgs) / sum) * 100;

		lblCalNetRate.setValue(new DecimalFormat("0.000").format(calNetRate));

	}

	void clearForm() {
		
		tablePmtLine.removeAllItems();
		tablePerils.removeAllItems();		

		txtChassis.setValue("");
		txtCusAddressNo.setValue("");
		txtCusArea.setValue("");
		txtCusInitName.setValue("");
		txtCusLastName.setValue("");
		txtCusStreet.setValue("");
		txtEngineNo.setValue("");
		txtMobile.setValue("");
		txtNIC.setValue("");
		txtSumInsured.setValue("");
		txtVehLett.setValue("");
		txtVehNos.setValue("");

		comboCusCity.setValue(null);
		comboMakeModel.setValue(null);
		comboPolUsage.setValue(null);
		comboProduct.setValue(null);
		comboPePackageName.setValue(null);
		comboVehProv.setValue(null);

		lblCalNetRate.setValue("0.00");
		lblDefNetRate.setValue("0.00");
		lblNetPremium.setValue("0.00");
		lblTotPremium.setValue("0.00");
		
		for(Object o:tableCNReason.getItemIds()) {
			((CheckBox) tableCNReason.getContainerProperty(o, "+").getValue()).setValue(false);
		}

		cusId = null;
		savedQuoId = null;
		savedQuoNo = null;
		loadedPrintCount = 0;

		loadedQuoId = null;
		loadedQuoNo = null;
		labelLoadedQuoNo.setValue("");

		comboVehProv.setEnabled(true);
		txtVehLett.setEnabled(true);
		txtVehNos.setEnabled(true);

		buttonSaveQuotation.setEnabled(true);

	}

	// ---------------------------------------------------UPDATE-------------------------------------//

	void update() {

		if (!validated()) {
			return;
		}

		if (tablePmtLine.size() == 0) {

			Notification.show("Generate Payment Lines", Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		// calPaymntLine();
		// genCalBreakDown();

		int i = 0;

		try {

			SimMTCustomerRequest customer = new SimMTCustomerRequest();
			customer.setCustomerAddressArea(txtCusArea.getValue().trim());
			customer.setCustomerAddressCity("");
			customer.setCustomerAddressNo(txtCusAddressNo.getValue().trim());
			customer.setCustomerAddressStreet(txtCusStreet.getValue().trim());
			customer.setCustomerInitialName(txtCusInitName.getValue().trim());
			customer.setCustomerLastName(txtCusLastName.getValue().trim());
			customer.setCustomerNic(txtNIC.getValue().trim());
//			customer.setCustomerPassPort(txtPassportNo.getValue().trim());
			customer.setCustomerPhone(txtMobile.getValue().trim());
//			if (cusId != null) {
//				customer.setId(cusId);
//			}

			MotorQuotationPrintModel printModel = new MotorQuotationPrintModel();

			List<QuotationDetailFlow> dfList = new ArrayList<>();

			String addCovers = "";

			double srcc_total = 0;
			double tc_total = 0;
			double life_protection = 0;

			for (Object col : tablePerils.getItemIds()) {
				CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();

				if (chbSel.getValue()) {

					String name = ((Label) tablePerils.getContainerProperty(col, "Name").getValue()).getValue();

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					String totalAmt = tablePerils.getContainerProperty(col, "Total").getValue().toString();

					if (type.equals("Cover")) {
						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();

						if (name.contains("SRCC")) {

							srcc_total = srcc_total + (Double.parseDouble(totalAmt));

						} else if (name.contains("TC")) {

							tc_total = tc_total + (Double.parseDouble(totalAmt));

						}

						if (!cover.getAlias().equals("PBASCCC")) {

							String comp_value = "";
							String seat_count = "";

							if (cover.getAlias().equals("OTHLPMT") || cover.getAlias().equals("OTHLPOV")) {
								life_protection = Double.parseDouble(totalAmt);
							}

							if (cover.getPeCoverValueBasisAlias().equals("VSC")
									|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

								TextField txtF = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(0);
								comp_value = (txtF.getValue());

								if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

									TextField txtSc = (TextField) ((VerticalLayout) tablePerils
											.getContainerProperty(col, "Value").getValue()).getComponent(1);

									seat_count = (txtSc.getValue());
								}
							} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
									|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

								ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(0);

								comp_value = (cmb.getValue().toString());

								if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

									TextField txtSc = (TextField) ((VerticalLayout) tablePerils
											.getContainerProperty(col, "Value").getValue()).getComponent(1);
									seat_count = (txtSc.getValue());

								}
							}

							if (!comp_value.isEmpty() && seat_count.isEmpty()) {

								name = name + "(" + comp_value + ")";

							} else if (!seat_count.isEmpty() && !comp_value.isEmpty()) {
								name = name + "(" + comp_value + " x " + seat_count + ")";
							}

							addCovers = addCovers + name + ",";
						}

					}

				}
			}

			addCovers = addCovers.substring(0, addCovers.length() - 1);

			if (life_protection != 0) {
				QuotationDetailFlow lfpt = new QuotationDetailFlow();
				lfpt.setDescription("Life Protection Cover");
				lfpt.setAmount((life_protection));
				dfList.add(lfpt);
			}

			QuotationDetailFlow srcc = new QuotationDetailFlow();
			srcc.setDescription("SRCC");
			srcc.setAmount((srcc_total));

			QuotationDetailFlow tc = new QuotationDetailFlow();
			tc.setDescription("TC");
			tc.setAmount((tc_total));

			dfList.add(srcc);
			dfList.add(tc);

			for (Object c : tablePmtLine.getItemIds()) {

				String name = (tablePmtLine.getContainerProperty(c, "Name").getValue()).toString();

				String totalAmt = tablePmtLine.getContainerProperty(c, "Amount").getValue().toString();

				PaymentLineResp lineResp = (PaymentLineResp) tablePmtLine.getContainerProperty(c, "..").getValue();

				if (lineResp.getIsDisplayed() == 1) {
					QuotationDetailFlow flow = new QuotationDetailFlow();
					flow.setDescription(name);
					flow.setAmount(Double.parseDouble(totalAmt));
					dfList.add(flow);
				}
			}

			double net_pre = Double.parseDouble(lblNetPremium.getValue().replaceAll(",", "")) - srcc_total - tc_total;

			printModel.setAdditionalCovers(addCovers);
			printModel.setBranch(comboLocation.getValue().toString());
			printModel.setChassisNo(txtChassis.getValue().trim());
			printModel.setDate(new Date());
			printModel.setDetailFlow(dfList);
			printModel.setInsuredName(txtCusInitName.getValue().trim() + " " + txtCusLastName.getValue().trim());
			printModel.setMakeAndModel("");
			printModel.setNetPremium(net_pre);
			printModel.setPrintedDate(new Date());
//			printModel.setSalesStaff(comboSalesStaff.getValue() == null ? null : comboSalesStaff.getValue().toString());
			printModel.setSumInsured(Double.parseDouble(txtSumInsured.getValue().trim()));
			printModel.setTotalPremium(Double.parseDouble(lblTotPremium.getValue().replaceAll(",", "")));
			printModel.setTypeOdVehicle(comboProduct.getValue().toString());
			printModel.setUser(user.getUser().getName());
			printModel.setUsage(comboPolUsage.getValue().toString());

			long days = TimeUnit.DAYS.convert(dateExpireDate.getValue().getTime() - dateStartDate.getValue().getTime(),
					TimeUnit.MILLISECONDS);

			printModel.setValidityPeriod((int) days);
			printModel.setVehicleNo(vehicleNo);
//			printModel.setYearOfManufacture(Integer.parseInt(comboYOM.getValue().toString()));

			SimMotorQuotationSaveRequest saveRequest = new SimMotorQuotationSaveRequest();

			// set agent code if selected -- or branch code if direct
			// saveRequest.setAgentCode(agentCode);
			saveRequest.setId(loadedQuoId);
			saveRequest.setQuotationNo(loadedQuoNo);
			saveRequest.setCreatedBy(user.getUser().getName());
			saveRequest.setModifiedBy(user.getUser().getName());
			saveRequest.setCustomer(customer);

			// saveRequest.setDebtorCode(debtorCode);

			saveRequest.setEffectiveFrom(dateStartDate.getValue());
			saveRequest.setEffectiveTo(dateEndDate.getValue());
			saveRequest.setIsApproved(Approved.NO);
			saveRequest.setIsIssued(Issued.NO);
			saveRequest.setLocationId(mapLocations.get(comboLocation.getValue().toString()).getId());
			saveRequest.setMktInsClassId("161L");
			saveRequest.setMotorQuotationPrintModel(printModel);
			saveRequest.setPeCoverTypeId(mapCoverTypes.get(comboCoverType.getValue().toString()).getId());
			saveRequest.setPeProductCatId(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			saveRequest.setPeProductId(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			saveRequest.setPolicyTypeId(mapPePolcats.get(comboPolCategory.getValue().toString()).getPeObjectId());
			saveRequest.setPrintCount(loadedPrintCount);
			saveRequest.setStatus(Status.ACTIVE);
			saveRequest.setSumInsured(Double.parseDouble(txtSumInsured.getValue().trim()));
			saveRequest.setTotalPremium(Double.parseDouble(lblTotPremium.getValue().replaceAll(",", "").trim()));
			saveRequest.setVehicleNo(vehicleNo);

			SimMTQuotationReportRequest quotationReportRequest = new SimMTQuotationReportRequest();
			quotationReportRequest.setPrintCount(loadedPrintCount);

			saveRequest.setSimMTQuotationReport(quotationReportRequest);

// other data

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			SimQuotationSaveData saveData = new SimQuotationSaveData();

			SimQuotationPolicyData policyData = new SimQuotationPolicyData();
			policyData.setCoverTypeIdCtrl(mapCoverTypes.get(comboCoverType.getValue().toString()).getId());
			policyData.setLocationIdCtrl(mapLocations.get(comboLocation.getValue().toString()).getId());
			policyData.setLocationNameCtrl(comboLocation.getValue().toString());
			policyData.setProductIdCtrl(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			policyData.setPackageNameIdCtrl(mapPePackage.get(comboPePackageName.getValue().toString()).getPackageId());
			policyData.setPeriodOfCoverIdCtrl(mapPeriodCover.get(comboPeriodOfCover.getValue().toString()).getId());
			policyData
					.setPoliceCategoryIdCtrl(mapPePolcats.get(comboPolCategory.getValue().toString()).getPeObjectId());
			policyData.setQuotationExpireDateCtrl(sdf.format(dateExpireDate.getValue()));
			policyData.setStartDateCtrl(sdf.format(dateStartDate.getValue()));
			policyData.setEndDateCtrl(sdf.format(dateEndDate.getValue()));
//			policyData.setSalesStaffNameCtrl(
//					comboSalesStaff.getValue() == null ? null : comboSalesStaff.getValue().toString());
			// policyData.setShortPeriodOfOptionsIdCtrl(shortPeriodOfOptionsIdCtrl);

			policyData.setUsageIdCtrl(mapPePolUs.get(comboPolUsage.getValue().toString()).getUsageId());

			saveData.setPolicyData(policyData);

			SimQuotationNonRiskData nonRiskData = new SimQuotationNonRiskData();
			nonRiskData.setChassisIdCtrl(txtChassis.getValue().trim());
//			nonRiskData.setEnginCapacityCtrl(Integer.parseInt(comboEngineCC.getValue().trim()));
			nonRiskData.setEngineIdCtrl(txtEngineNo.getValue().trim());
//			nonRiskData.setFuelType(comboFuelType.getValue().toString());
//			nonRiskData.setManufacturedCtrl(Integer.parseInt(comboYOM.getValue().toString()));
			nonRiskData.setProvinceCode(comboVehProv.getValue() == null ? null : comboVehProv.getValue().toString());
			nonRiskData.setSeatCountIdCtrl(Integer.parseInt(txtSeatCount.getValue().trim()));
			nonRiskData.setVehicleMakeName("");
			nonRiskData.setVehicleNo(vehicleNo);
//			nonRiskData.setVehicleTypeNameCtrl(comboVehType.getValue().toString());

			saveData.setNonRiskdata(nonRiskData);

			List<SimQuotationCoverData> coverDatas = new ArrayList<>();
			for (Object col : tablePerils.getItemIds()) {

				SimQuotationCoverData coverData = new SimQuotationCoverData();

				CheckBox chb = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
				if (chb.getValue()) {

					String total = tablePerils.getContainerProperty(col, "Total").getValue().toString();

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					coverData.setType(type);

					// Cover calculation

					if (type.equals("Cover")) {

						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(cover.getAlias());
						coverData.setId(cover.getId());
						coverData.setName(cover.getPeCoverName());
						coverData.setOrder(cover.getCalculationOrder());

						if (cover.getPeCoverValueBasisAlias().equals("VSC")
								|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

							TextField txtF = (TextField) ((VerticalLayout) tablePerils
									.getContainerProperty(col, "Value").getValue()).getComponent(0);
							coverData.setTxtValue(txtF.getValue());

							if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);

								coverData.setSeatCount(txtSc.getValue());
							}
						} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
								|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							// set selected range value
							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}
							if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);
								coverData.setSeatCount(txtSc.getValue());

							}
						}

					} else if (type.equals("Discount")) {

						// Discount calculation
						Discount disc = (Discount) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(disc.getAlias());
						coverData.setId(disc.getId());
						coverData.setName(disc.getPeDiscountName());
						coverData.setOrder(disc.getCalculationOrder());

						if (disc.getIsRange() == 1) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}
						}

//						if (disc.getAlias().equals("DVO")) {
//							TextField txtSc = (TextField) ((VerticalLayout) tablePerils
//									.getContainerProperty(col, "Value").getValue()).getComponent(1);
//							coverData.setSeatCount(txtSc.getValue());
//						}

					} else if (type.equals("Loading")) {

						// Loading calculation

						Loading disc = (Loading) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(disc.getAlias());
						coverData.setId(disc.getId());
						coverData.setName(disc.getPeLoadingName());
						coverData.setOrder(disc.getCalculationOrder());

						// if (disc.getIsRange() == 1)

						Component cmp = ((VerticalLayout) tablePerils.getContainerProperty(col, "Value").getValue())
								.getComponent(0);

						if (cmp.getClass().equals(ComboBox.class)) {

							ComboBox cmb = (ComboBox) cmp;

							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}

						}

						// }

					}
					coverData.setValue(Double.parseDouble(total));
					coverDatas.add(coverData);

				}

			}
			for (Object xx : tablePmtLine.getItemIds()) {

				String name = (tablePmtLine.getContainerProperty(xx, "Name").getValue()).toString();

				String totalAmt = tablePmtLine.getContainerProperty(xx, "Amount").getValue().toString();

				PaymentLineResp lineResp = (PaymentLineResp) tablePmtLine.getContainerProperty(xx, "..").getValue();

				SimQuotationCoverData coverData = new SimQuotationCoverData();
				coverData.setAlias(lineResp.getAlias());
				coverData.setId(lineResp.getId());
				coverData.setName(lineResp.getName());
				coverData.setType("PaymentLine");
				coverData.setValue(Double.parseDouble(totalAmt));
				coverData.setIsDisplayed(lineResp.getIsDisplayed());

				coverDatas.add(coverData);

			}

			saveData.setCalculatedList(coverDatas);

			// exess

			Gson gson = new Gson();

			System.out.println((gson.toJson(saveRequest)));
			System.out.println((gson.toJson(saveData)));

			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation");
			// request.setHeader("Accept", "application/json");
			// request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("quotationUpdateModel", gson.toJson(saveRequest)));
			params.add(new BasicNameValuePair("otherQuotationData", gson.toJson(saveData)));

			request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//			
//			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//			builder.addTextBody("quotationModel", gson.toJson(saveRequest), ContentType.TEXT_PLAIN);
//			builder.addTextBody("otherQuotationData", "TEST DATA", ContentType.TEXT_PLAIN);
//			builder.addBinaryBody("nic", file, ContentType.MULTIPART_FORM_DATA, filename);
//			HttpEntity multipart = builder.build();
//
//			request.setEntity(multipart);

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

			if (i == 200) {

				Notification.show("Saved successfully !");

				isUpdate = false;

//				btnApprove.setEnabled(true);
//				buttonSave.setCaption("Save");

				SimQuotationResp resp = gson.fromJson(getRes, SimQuotationResp.class);
				savedQuoId = resp.getId();
				savedQuoNo = resp.getQuotationNo();

				System.out.println("Q id " + savedQuoId);
				System.out.println("Q No " + savedQuoNo);

				File pdf = new File(System.getProperty("user.home") + "/UW/temp/" + savedQuoNo + "-"
						+ System.currentTimeMillis() + ".pdf");

				FileUtils.copyURLToFile(new URL(ConstantData.baseUrl + "sim-m-quotation/print/" + savedQuoId), pdf);

				FileResource fr2 = new FileResource(pdf);

				getUI().getPage().open(fr2, "_blank", true);
//
//				clearForm();

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
			}
		}

	}

	// -------------------------------------------------------
	// SAVE--------------------------------//
//aaa
	void save() {

		if (!validated()) {
			return;
		}

		if (tablePmtLine.size() == 0) {

			Notification.show("Generate Payment Lines", Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		// calPaymntLine();
		// genCalBreakDown();

		int i = 0;

		try {

			SimMTCustomerRequest customer = new SimMTCustomerRequest();
			
			customer.setCustomerAddressArea(txtCusArea.getValue().trim());
			customer.setCustomerAddressCity("");
			customer.setCustomerAddressNo(txtCusAddressNo.getValue().trim());
			customer.setCustomerAddressStreet(txtCusStreet.getValue().trim());
			customer.setCustomerInitialName(txtCusInitName.getValue().trim());
			customer.setCustomerLastName(txtCusLastName.getValue().trim());
			customer.setCustomerNic(txtNIC.getValue().trim());
			customer.setCustomerPhone(txtMobile.getValue().trim());

			MotorQuotationPrintModel printModel = new MotorQuotationPrintModel();

			List<QuotationDetailFlow> dfList = new ArrayList<>();

			String addCovers = "";

			double life_protection = 0;
			double srcc_total = 0;
			double tc_total = 0;

			for (Object col : tablePerils.getItemIds()) {

				CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();

				if (chbSel.getValue()) {

					String name = ((Label) tablePerils.getContainerProperty(col, "Name").getValue()).getValue();

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					String totalAmt = tablePerils.getContainerProperty(col, "Total").getValue().toString();

					if (type.equals("Cover")) {

						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();

						if (name.contains("SRCC")) {

							srcc_total = srcc_total + (Double.parseDouble(totalAmt));

						} else if (name.contains("TC")) {

							tc_total = tc_total + (Double.parseDouble(totalAmt));

						}

						if (!cover.getAlias().equals("PBASCCC")) {

							String comp_value = "";
							String seat_count = "";

							if (cover.getAlias().equals("OTHLPMT") || cover.getAlias().equals("OTHLPOV")) {
								life_protection = Double.parseDouble(totalAmt);
							}

							if (cover.getPeCoverValueBasisAlias().equals("VSC")
									|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

								TextField txtF = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(0);
								comp_value = (txtF.getValue());

								if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

									TextField txtSc = (TextField) ((VerticalLayout) tablePerils
											.getContainerProperty(col, "Value").getValue()).getComponent(1);

									seat_count = (txtSc.getValue());
								}
							} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
									|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

								ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(0);

								comp_value = (cmb.getValue().toString());

								if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

									TextField txtSc = (TextField) ((VerticalLayout) tablePerils
											.getContainerProperty(col, "Value").getValue()).getComponent(1);
									seat_count = (txtSc.getValue());

								}
							}

							if (!comp_value.isEmpty() && seat_count.isEmpty()) {

								name = name + "(" + comp_value + ")";

							} else if (!seat_count.isEmpty() && !comp_value.isEmpty()) {
								name = name + "(" + comp_value + " x " + seat_count + ")";
							}

							addCovers = addCovers + name + ",";
						}

					}

				}
			}

			addCovers = addCovers.substring(0, addCovers.length() - 1);

			if (life_protection != 0) {
				QuotationDetailFlow lfpt = new QuotationDetailFlow();
				lfpt.setDescription("Life Protection Cover");
				lfpt.setAmount((life_protection));
				dfList.add(lfpt);
			}

			QuotationDetailFlow srcc = new QuotationDetailFlow();
			srcc.setDescription("SRCC");
			srcc.setAmount((srcc_total));

			QuotationDetailFlow tc = new QuotationDetailFlow();
			tc.setDescription("TC");
			tc.setAmount((tc_total));

			dfList.add(srcc);
			dfList.add(tc);

			for (Object c : tablePmtLine.getItemIds()) {

				String name = (tablePmtLine.getContainerProperty(c, "Name").getValue()).toString();

				String totalAmt = tablePmtLine.getContainerProperty(c, "Amount").getValue().toString();

				PaymentLineResp lineResp = (PaymentLineResp) tablePmtLine.getContainerProperty(c, "..").getValue();

				if (lineResp.getIsDisplayed() == 1) {
					QuotationDetailFlow flow = new QuotationDetailFlow();
					flow.setDescription(name);
					flow.setAmount(Double.parseDouble(totalAmt));
					dfList.add(flow);
				}
			}

			double net_pre = Double.parseDouble(lblNetPremium.getValue().replaceAll(",", "")) - srcc_total - tc_total;

			printModel.setAdditionalCovers(addCovers);
			printModel.setBranch(comboLocation.getValue().toString());
			printModel.setChassisNo(txtChassis.getValue().trim());
			printModel.setDate(new Date());
			printModel.setDetailFlow(dfList);
			printModel.setInsuredName(txtCusInitName.getValue().trim() + " " + txtCusLastName.getValue().trim());
			printModel.setMakeAndModel("");
			printModel.setNetPremium(net_pre);
			printModel.setPrintedDate(new Date());
			printModel.setSumInsured(Double.parseDouble(txtSumInsured.getValue().trim()));
			printModel.setTotalPremium(Double.parseDouble(lblTotPremium.getValue().replaceAll(",", "")));
			printModel.setTypeOdVehicle(comboProduct.getValue().toString());
			printModel.setUser(user.getUser().getName());
			printModel.setUsage(comboPolUsage.getValue().toString());

			long days = TimeUnit.DAYS.convert(dateExpireDate.getValue().getTime() - dateStartDate.getValue().getTime(),
					TimeUnit.MILLISECONDS);

			printModel.setValidityPeriod((int) days);
			printModel.setVehicleNo(vehicleNo);
			printModel.setYearOfManufacture(0);

			SimMotorQuotationSaveRequest saveRequest = new SimMotorQuotationSaveRequest();

			saveRequest.setCreatedBy(user.getUser().getName());
			saveRequest.setCustomer(customer);

			saveRequest.setEffectiveFrom(dateStartDate.getValue());
			saveRequest.setEffectiveTo(dateEndDate.getValue());
			saveRequest.setIsApproved(Approved.NO);
			saveRequest.setIsIssued(Issued.NO);
			saveRequest.setLocationId(mapLocations.get(comboLocation.getValue().toString()).getId());
			saveRequest.setMktInsClassId("161");
			saveRequest.setMotorQuotationPrintModel(printModel);
			saveRequest.setPeCoverTypeId(mapCoverTypes.get(comboCoverType.getValue().toString()).getId());
			saveRequest.setPeProductCatId(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			saveRequest.setPeProductId(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			saveRequest.setPolicyTypeId(mapPePolcats.get(comboPolCategory.getValue().toString()).getPeObjectId());
			saveRequest.setPrintCount(loadedPrintCount);
			saveRequest.setStatus(Status.ACTIVE);
			saveRequest.setSumInsured(Double.parseDouble(txtSumInsured.getValue().trim()));
			saveRequest.setTotalPremium(Double.parseDouble(lblTotPremium.getValue().replaceAll(",", "").trim()));
			saveRequest.setVehicleNo(vehicleNo);

			SimMTQuotationReportRequest quotationReportRequest = new SimMTQuotationReportRequest();
			quotationReportRequest.setPrintCount(loadedPrintCount);

			saveRequest.setSimMTQuotationReport(quotationReportRequest);

			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			SimQuotationSaveData saveData = new SimQuotationSaveData();

			SimQuotationPolicyData policyData = new SimQuotationPolicyData();
			policyData.setCoverTypeIdCtrl(mapCoverTypes.get(comboCoverType.getValue().toString()).getId());
			policyData.setLocationIdCtrl(mapLocations.get(comboLocation.getValue().toString()).getId());
			policyData.setLocationNameCtrl(comboLocation.getValue().toString());
			policyData.setProductIdCtrl(mapPeProdList.get(comboProduct.getValue().toString()).getPeProductCatId());
			policyData.setPackageNameIdCtrl(mapPePackage.get(comboPePackageName.getValue().toString()).getPackageId());
			policyData.setPeriodOfCoverIdCtrl(mapPeriodCover.get(comboPeriodOfCover.getValue().toString()).getId());
			policyData
					.setPoliceCategoryIdCtrl(mapPePolcats.get(comboPolCategory.getValue().toString()).getPeObjectId());
			policyData.setQuotationExpireDateCtrl(sdf.format(dateExpireDate.getValue()));
			policyData.setStartDateCtrl(sdf.format(dateStartDate.getValue()));
			policyData.setEndDateCtrl(sdf.format(dateEndDate.getValue()));

			policyData.setUsageIdCtrl(mapPePolUs.get(comboPolUsage.getValue().toString()).getUsageId());

			saveData.setPolicyData(policyData);

			SimQuotationNonRiskData nonRiskData = new SimQuotationNonRiskData();
			nonRiskData.setChassisIdCtrl(txtChassis.getValue().trim());
			nonRiskData.setEnginCapacityCtrl(1000);
			nonRiskData.setEngineIdCtrl(txtEngineNo.getValue().trim());
			nonRiskData.setFuelType("");
			nonRiskData.setManufacturedCtrl(0);
			nonRiskData.setProvinceCode(comboVehProv.getValue() == null ? null : comboVehProv.getValue().toString());
			nonRiskData.setSeatCountIdCtrl(Integer.parseInt(txtSeatCount.getValue().trim()));
			nonRiskData.setVehicleMakeName("");
			nonRiskData.setVehicleNo(vehicleNo);
//			nonRiskData.setVehicleTypeNameCtrl(comboVehType.getValue().toString());

			saveData.setNonRiskdata(nonRiskData);

			List<SimQuotationCoverData> coverDatas = new ArrayList<>();
			for (Object col : tablePerils.getItemIds()) {

				SimQuotationCoverData coverData = new SimQuotationCoverData();

				CheckBox chb = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
				if (chb.getValue()) {

					String total = tablePerils.getContainerProperty(col, "Total").getValue().toString();

					String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

					coverData.setType(type);

					// Cover calculation

					if (type.equals("Cover")) {

						Cover cover = (Cover) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(cover.getAlias());
						coverData.setId(cover.getId());
						coverData.setName(cover.getPeCoverName());
						coverData.setOrder(cover.getCalculationOrder());

						if (cover.getPeCoverValueBasisAlias().equals("VSC")
								|| cover.getPeCoverValueBasisAlias().equals("UEV")) {

							TextField txtF = (TextField) ((VerticalLayout) tablePerils
									.getContainerProperty(col, "Value").getValue()).getComponent(0);
							coverData.setTxtValue(txtF.getValue());

							if (cover.getPeCoverValueBasisAlias().equals("VSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);

								coverData.setSeatCount(txtSc.getValue());
							}
						} else if (cover.getPeCoverValueBasisAlias().equals("RNG")
								|| cover.getPeCoverValueBasisAlias().equals("RSC")) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							// set selected range value
							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}

							if (cover.getPeCoverValueBasisAlias().equals("RSC")) {

								TextField txtSc = (TextField) ((VerticalLayout) tablePerils
										.getContainerProperty(col, "Value").getValue()).getComponent(1);
								coverData.setSeatCount(txtSc.getValue());

							}
						}

					} else if (type.equals("Discount")) {

						// Discount calculation
						Discount disc = (Discount) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(disc.getAlias());
						coverData.setId(disc.getId());
						coverData.setName(disc.getPeDiscountName());
						coverData.setOrder(disc.getCalculationOrder());

						if (disc.getIsRange() == 1) {

							ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils.getContainerProperty(col, "Value")
									.getValue()).getComponent(0);

							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}
						}

					} else if (type.equals("Loading")) {

						// Loading calculation

						Loading disc = (Loading) tablePerils.getContainerProperty(col, "..").getValue();
						coverData.setAlias(disc.getAlias());
						coverData.setId(disc.getId());
						coverData.setName(disc.getPeLoadingName());
						coverData.setOrder(disc.getCalculationOrder());

						// if (disc.getIsRange() == 1)

						Component cmp = ((VerticalLayout) tablePerils.getContainerProperty(col, "Value").getValue())
								.getComponent(0);

						if (cmp.getClass().equals(ComboBox.class)) {

							ComboBox cmb = (ComboBox) cmp;
							if (cmb.getValue() != null) {
								coverData.setComboValue(cmb.getValue().toString());
							}

						}

						// }

					}
					coverData.setValue(Double.parseDouble(total));
					coverDatas.add(coverData);

				}

			}
			for (Object xx : tablePmtLine.getItemIds()) {

				String name = (tablePmtLine.getContainerProperty(xx, "Name").getValue()).toString();

				String totalAmt = tablePmtLine.getContainerProperty(xx, "Amount").getValue().toString();

				PaymentLineResp lineResp = (PaymentLineResp) tablePmtLine.getContainerProperty(xx, "..").getValue();

				SimQuotationCoverData coverData = new SimQuotationCoverData();
				coverData.setAlias(lineResp.getAlias());
				coverData.setId(lineResp.getId());
				coverData.setName(lineResp.getName());
				coverData.setType("PaymentLine");
				coverData.setValue(Double.parseDouble(totalAmt));
				coverData.setIsDisplayed(lineResp.getIsDisplayed());

				coverDatas.add(coverData);

			}

			saveData.setCalculatedList(coverDatas);

			// exess

			Gson gson = new Gson();

			System.out.println((gson.toJson(saveRequest)));
			System.out.println((gson.toJson(saveData)));

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(ConstantData.baseUrl + "sim-m-quotation");
			// request.setHeader("Accept", "application/json");
			// request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("quotationModel", gson.toJson(saveRequest)));
			params.add(new BasicNameValuePair("otherQuotationData", gson.toJson(saveData)));

			request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));


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

				SimQuotationResp resp = gson.fromJson(getRes, SimQuotationResp.class);
				savedQuoId = resp.getId();
				savedQuoNo = resp.getQuotationNo();

				Notification.show("Saved successfully !");

//				btnApprove.setEnabled(true);

				System.out.println("Q id " + savedQuoId);
				System.out.println("Q No " + savedQuoNo);

//				savedId = resp.getId();

				File pdf = new File(System.getProperty("user.home") + "/UW/temp/" + savedQuoNo + "-"
						+ System.currentTimeMillis() + ".pdf");

				FileUtils.copyURLToFile(new URL(ConstantData.baseUrl + "sim-m-quotation/print/" + savedQuoId), pdf);

				FileResource fr2 = new FileResource(pdf);

				getUI().getPage().open(fr2, "_blank", true);

				buttonSaveQuotation.setEnabled(false);
				
				buttonSaveCvrNote.setEnabled(true);

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
			}
		}

	}

	// ----------------------------------------- get by id
	// --------------------------------------//

	public void getById(String id) {

		int i = -1;

		SimMotorQuotationSaveRequest gotQt = null;

		System.out.println("Q id = " + id);

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation/" + id);
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

			if (i == 200) {

				System.out.println("got resp = " + getRes);
				
				gotQt = gson.fromJson(getRes, SimMotorQuotationSaveRequest.class);

				if (gotQt != null) {

					clearForm();

					System.out.println(gotQt.getQuotationData());

					SimQuotationSaveData saveData = gson.fromJson(gotQt.getQuotationData(), SimQuotationSaveData.class);
					
					// txtChassis.setValue(gotQt.getMotorQuotationPrintModel().getChassisNo());
					txtCusAddressNo.setValue(gotQt.getCustomer().getCustomerAddressNo() == null ? ""
							: gotQt.getCustomer().getCustomerAddressNo());
					txtCusArea.setValue(gotQt.getCustomer().getCustomerAddressArea() == null ? ""
							: gotQt.getCustomer().getCustomerAddressArea());
					txtCusInitName.setValue(gotQt.getCustomer().getCustomerInitialName() == null ? ""
							: gotQt.getCustomer().getCustomerInitialName());
					txtCusLastName.setValue(gotQt.getCustomer().getCustomerLastName() == null ? ""
							: gotQt.getCustomer().getCustomerLastName());
					txtCusStreet.setValue(gotQt.getCustomer().getCustomerAddressStreet() == null ? ""
							: gotQt.getCustomer().getCustomerAddressStreet());
					txtEngineNo.setValue(saveData.getNonRiskdata().getEngineIdCtrl());
					txtMobile.setValue(gotQt.getCustomer().getCustomerPhone() == null ? ""
							: gotQt.getCustomer().getCustomerPhone());
					txtNIC.setValue(
							gotQt.getCustomer().getCustomerNic() == null ? "" : gotQt.getCustomer().getCustomerNic());
					txtSeatCount.setValue(saveData.getNonRiskdata().getSeatCountIdCtrl() + "");
					txtSumInsured.setValue(new DecimalFormat("0.00").format(gotQt.getSumInsured()));
					
					if (gotQt.getIntmdCovernoteReasons() != null) {
						for (Object col : tableCNReason.getItemIds()) {
							
							String cnReasonName = tableCNReason.getContainerProperty(col, "Name").getValue().toString();
							
							if(gotQt.getIntmdCovernoteReasons().contains(cnReasonName)) {
								
								CheckBox cb=(CheckBox)tableCNReason.getContainerProperty(col, "+").getValue();
								cb.setValue(true);
							}
							
						}
					}

					String vehiNo = gotQt.getVehicleNo();

					comboVehProv.select(VehiNoPatternValidator.divide(vehiNo).get(0));
					txtVehLett.setValue(VehiNoPatternValidator.divide(vehiNo).get(1));
					txtVehNos.setValue(VehiNoPatternValidator.divide(vehiNo).get(2));

					comboVehProv.setEnabled(false);
					txtVehLett.setEnabled(false);
					txtVehNos.setEnabled(false);

					for (PeCoverType covertype : peCoverTypes) {
						if (gotQt.getPeCoverTypeId().equals(covertype.getId())) {
							comboCoverType.select(covertype.getName());
							break;
						}
					}

					comboCusCity.select(gotQt.getCustomer().getCustomerAddressCity());

					for (Location m : locations) {
						if (gotQt.getLocationId().equals(m.getId())) {
							comboLocation.select(m.getInitialName().toUpperCase());
							break;
						}
					}

//					System.out.println("vehi type - "+saveData.getNonRiskdata().getVehicleTypeNameCtrl());
					
//					comboVehType.select(saveData.getNonRiskdata().getVehicleTypeNameCtrl());
					comboMakeModel.select(saveData.getNonRiskdata().getVehicleMakeName());
					comboYOM.select(saveData.getNonRiskdata().getManufacturedCtrl());

					for (PeCoverType m : peCoverTypes) {
						if (gotQt.getPeCoverTypeId().equals(m.getId())) {
							comboCoverType.select(m.getName());
							break;
						}
					}

					for (PePolicyCategory m : pePolcats) {

						System.out.println("Pol Cat " + saveData.getPolicyData().getPoliceCategoryIdCtrl());
						System.out.println("Pol Cat* " + m.getPeObjectId());

						if (saveData.getPolicyData().getPoliceCategoryIdCtrl().equals(m.getPeObjectId())) {
							comboPolCategory.select(m.getPeObjectName());
							break;
						}
					}

					for (PePolicyUsage m : pePolUs) {
						if (saveData.getPolicyData().getUsageIdCtrl().equals(m.getUsageId())) {
							comboPolUsage.setValue(m.getUsageName());
							break;
						}
					}

					for (PeProductListItem m : peProdList) {
						// System.out.println("got prd "+m.getPeCategoryName()+"
						// "+m.getPeProductCatId());
						// System.out.println("svd prd "+saveData.getPolicyData().getProductIdCtrl());
						if (saveData.getPolicyData().getProductIdCtrl().equals(m.getPeProductCatId())) {
							comboProduct.setValue(m.getPeCategoryName());
							break;
						}
					}

					for (PePackage m : pePackage) {
						if (saveData.getPolicyData().getPackageNameIdCtrl().equals(m.getPackageId())) {
							comboPePackageName.setValue(m.getPackageName());
							break;
						}
					}

					for (PeriodOfCover m : periodCover) {
						if (saveData.getPolicyData().getPeriodOfCoverIdCtrl().equals(m.getId())) {
							comboPeriodOfCover.setValue(m.getName());
							break;
						}
					}

//					comboSalesStaff.select(saveData.getPolicyData().getAgentCodeCtrl());

					// comboShortPeriodOpions.setValue(null);

					comboYOM.select(saveData.getNonRiskdata().getManufacturedCtrl() + "");

//					labelCalNetRate.setValue("0.00");
//					labelDefNetRate.setValue("0.00");

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					dateExpireDate.setValue(sdf.parse(saveData.getPolicyData().getQuotationExpireDateCtrl()));
					dateStartDate.setValue(sdf.parse(saveData.getPolicyData().getStartDateCtrl()));
					dateEndDate.setValue(sdf.parse(saveData.getPolicyData().getEndDateCtrl()));

					for (SimQuotationCoverData peril : saveData.getCalculatedList()) {

						if (peril.getType().equals("Cover")) {

							// System.out.println("Cover : "+peril.getId() +" "+peril.getName());
							
							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Cover")) {

									Cover cvr = (Cover) tablePerils.getContainerProperty(col, "..").getValue();

									// System.out.println("Searching : "+cvr.getId()+" "+cvr.getPeCoverName());

									if (cvr.getId().equals(peril.getId())) {

										if (cvr.getPeCoverValueBasisAlias().equals("VSC")
												|| cvr.getPeCoverValueBasisAlias().equals("UEV")) {

											TextField txtF = (TextField) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											txtF.setValue(peril.getTxtValue());

											if (cvr.getPeCoverValueBasisAlias().equals("VSC")) {

												TextField txtSc = (TextField) ((VerticalLayout) tablePerils
														.getContainerProperty(col, "Value").getValue()).getComponent(1);

												txtSc.setValue(peril.getSeatCount());

											}
										} else if (cvr.getPeCoverValueBasisAlias().equals("RNG")
												|| cvr.getPeCoverValueBasisAlias().equals("RSC")) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());

											if (cvr.getPeCoverValueBasisAlias().equals("RSC")) {

												TextField txtSc = (TextField) ((VerticalLayout) tablePerils
														.getContainerProperty(col, "Value").getValue()).getComponent(1);
												txtSc.setValue(peril.getSeatCount());
											}
										}

										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);
										// System.out.println("found");

										break;
									}

								}

							}

						} else if (peril.getType().equals("Discount")) {

							// System.out.println("Discount : "+peril.getId() +" "+peril.getName());

							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Discount")) {

									Discount cvr = (Discount) tablePerils.getContainerProperty(col, "..").getValue();

									if (cvr.getId().equals(peril.getId())) {

										if (cvr.getIsRange() == 1 || cvr.getAlias().equals("DVO")
												|| cvr.getAlias().equals("DVM")) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());
										}

										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);

										// System.out.println("found");

										break;
									}
								}

							}

						} else if (peril.getType().equals("Loading")) {

							// System.out.println("Loading : "+peril.getId() +" "+peril.getName());

							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Loading")) {
									Loading cvr = (Loading) tablePerils.getContainerProperty(col, "..").getValue();

									if (cvr.getId().equals(peril.getId())) {

										if (peril.getComboValue() != null) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());
										}
										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);

										// System.out.println("found");

										break;
									}
								}

							}
						} else if (peril.getType().equals("PaymentLine")) {

							PaymentLineResp pl = new PaymentLineResp();
							pl.setId(peril.getId());
							pl.setAlias(peril.getAlias());
							pl.setIsDisplayed(peril.getIsDisplayed());

							tablePmtLine.addItem(new Object[] { peril.getName(),
									new DecimalFormat("0.00").format(peril.getValue()), peril.getOrder(), pl },
									tablePmtLine.size());
						}

					}

					lblTotPremium.setValue(new DecimalFormat("#,##0.00").format(gotQt.getTotalPremium()));
					savedQuoId = id;
					loadedQuoNo = gotQt.getQuotationNo();
					labelLoadedQuoNo.setValue(loadedQuoNo);

					buttonSaveQuotation.setCaption("Revise");
					buttonSaveCvrNote.setEnabled(true);

				}
				
				if (gotQt.getIntmdCnGenerated() == true) {
					
					buttonSaveCvrNote.setCaption("Print");
					
					disableComponenets();
					
					for (Object col : tablePerils.getItemIds()) {

						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();

						chbSel.setEnabled(false);

					}
					
					for (Object colCnReason : tableCNReason.getItemIds()) {

						CheckBox chbSel = (CheckBox) tableCNReason.getContainerProperty(colCnReason, "+").getValue();

						chbSel.setEnabled(false);

					}

				}

				isUpdate = true;

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
			}
		}
	}

	void quoteApprove() {

		int responseCode = 0;

		try {

			// data set
			SimMotorQuotationSaveRequest saveRequest = new SimMotorQuotationSaveRequest();

//			System.out.println("Revised Id");
//			System.out.println("Is Update :  " + isUpdate);

			if (isUpdate == false) {
				saveRequest.setId(savedQuoId);
				saveRequest.setIsApproved(Approved.YES);
			} else if (isUpdate == true) {
				saveRequest.setId(loadedQuoId);
				saveRequest.setIsApproved(Approved.YES);
			}

			// Put request
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation/approve");
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-Type", "application/json");
			request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			// only for post & Put
			StringEntity body = new StringEntity(gson.toJson(saveRequest));
			request.setEntity(body);

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
//				Notification.show("Sucsessfully Address Type Record Updated!");

				SimQuotationResp resp = gson.fromJson(getRes, SimQuotationResp.class);
//				
//				if (resp.getIsApproved().equals("YES")) {
//					lblApprovedBy.setValue(resp.getApprovedBy());
//				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);

		} finally {

			if (responseCode == 500) {
				Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (responseCode == 400) {
				Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (responseCode == 404) {
				Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
			} else if (responseCode == 401) {
				Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
			} else if (responseCode == 200) {

			} else {
				// Notification.show("Error", Integer.toString(responseCode),
				// Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}

	void quotePrint() {

		try {
			File pdf = new File(System.getProperty("user.home") + "/UW/temp/" + savedQuoNo + "-"
					+ System.currentTimeMillis() + ".pdf");

			if (isUpdate == true) {
				FileUtils.copyURLToFile(new URL(ConstantData.baseUrl + "sim-m-quotation/print/" + loadedQuoId), pdf);
			} else if (isUpdate == false) {
				FileUtils.copyURLToFile(new URL(ConstantData.baseUrl + "sim-m-quotation/print/" + savedQuoId), pdf);
			}

			FileResource fr2 = new FileResource(pdf);

			getUI().getPage().open(fr2, "_blank", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}

	}

	void selectDefaultCovers() {

		try {

			for (Object i : tablePerils.getItemIds()) {

				String name = ((Label) tablePerils.getContainerProperty(i, "Name").getValue()).getValue();
				String type = String.valueOf(tablePerils.getContainerProperty(i, "Type").getValue());
				String product = comboProduct.getValue().toString();
				
				System.out.println("Product Output - " + product);

//				if (product.equals("Comprehensive Private Motor Cycles Insurance Policy")) {					
//
//					if (type.equalsIgnoreCase("Cover") || name.equalsIgnoreCase("Samagi Insurance Discount - (BOC)")) {
//	
//						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(i, "+").getValue();
//	
//						chbSel.setValue(true);
//						chbSel.setEnabled(false);
//	
//					}
//					
//				} else {
					
					if (type.equalsIgnoreCase("Cover") || type.equalsIgnoreCase("Discount")) {
	
						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(i, "+").getValue();
	
						chbSel.setValue(true);
						chbSel.setEnabled(false);
	
					}
					
//				}

//				if (comboPolUsage.getValue().toString().toLowerCase().trim().startsWith("hiring")) {
//					if (name.toLowerCase().contains("exclusion cover")) {
//
//						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(i, "+").getValue();
//
//						chbSel.setValue(true);
//						chbSel.setEnabled(false);
//
//					}
//				}

//				if (comboPolUsage.getValue().toString().toLowerCase().trim().equals("hiring rent")) {
//
//					if (name.toLowerCase().contains("rent ")) {
//
//						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(i, "+").getValue();
//
//						chbSel.setValue(true);
//						chbSel.setEnabled(false);
//
//					}
//				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	void saveIntCvrNote() {

		int i = 0;

		try {
			
			String cvrReason = "";
			
			for(Object o:tableCNReason.getItemIds()) {
				
				if(((CheckBox)tableCNReason.getContainerProperty(o, "+").getValue()).getValue()) {
					cvrReason=cvrReason+tableCNReason.getContainerProperty(o, "Name").getValue().toString()+"\n";
				}				
				
			}
			cvrReason=cvrReason.trim();
			
			IntCoverNoteSaveRequest intCoverNoteSave = new IntCoverNoteSaveRequest();
			
			CoverNote coverNote = new CoverNote();
			
			coverNote.setAddress(txtCusAddressNo.getValue().toString().trim() + ", " + txtCusStreet.getValue().toString().trim() + ", " + txtCusArea.getValue().toString().trim() + ", " + comboCusCity.getValue().toString());
			coverNote.setChassisNo(txtChassis.getValue().toString().trim());
			coverNote.setCovernoteDate(new SimpleDateFormat("yyyy-MM-dd").format(dateStartDate.getValue()));
			coverNote.setCovernoteReson(cvrReason);
			coverNote.setDate(new SimpleDateFormat("yyyy-MM-dd").format(dateStartDate.getValue()));
			coverNote.setEngineNo(txtEngineNo.getValue().toString().trim());
			coverNote.setInsuranceClass("Motor");
			coverNote.setInsuredName(txtCusInitName.getValue().toString().trim() + " " + txtCusLastName.getValue().toString().trim());
			coverNote.setNoOfDays(30);
			coverNote.setPolicyNo("");
			coverNote.setProductOfInsurance(comboProduct.getValue().toString());
			coverNote.setSumInsured(txtSumInsured.getValue().toString().trim());
			coverNote.setTotalPremium(lblTotPremium.getValue().toString());
			coverNote.setVehiMake(comboMakeModel.getValue().toString());
			coverNote.setVehicleNo(comboVehProv.getValue().toString() + " " + txtVehLett.getValue().toString() + " " + txtVehNos.getValue().toString());
			coverNote.setYom(comboYOM.getValue().toString());
			
			intCoverNoteSave.setCoverNoteModel(coverNote);
			
			intCoverNoteSave.setQuotationId(savedQuoId);
		
			
			SimMTCustomerRequest customer = new SimMTCustomerRequest();
			
			customer.setCustomerAddressArea(txtCusArea.getValue().trim());
			customer.setCustomerAddressCity(comboCusCity.getValue().toString());
			customer.setCustomerAddressNo(txtCusAddressNo.getValue().trim());
			customer.setCustomerAddressStreet(txtCusStreet.getValue().trim());
			customer.setCustomerInitialName(txtCusInitName.getValue().trim());
			customer.setCustomerLastName(txtCusLastName.getValue().trim());
			customer.setCustomerNic(txtNIC.getValue().trim());
			customer.setCustomerPhone(txtMobile.getValue().trim());
			
			intCoverNoteSave.setSimMTCustomerRequest(customer);
			
			System.out.println("bbbbbb"+intCoverNoteSave);
			
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(ConstantData.baseUrl + "sim-m-quotation/PrintIntmdCoverNote");
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
				
				
//				InputStream is = response.getEntity().getContent();
//				File pdf=new File(System.getProperty("user.home") + "/UW/temp/"+ System.currentTimeMillis() + ".pdf");
//				FileOutputStream fos = new FileOutputStream(pdf);
				
				Notification.show("Saved successfully !");
				
				clearForm();
				
//				int read = 0;
//				byte[] buffer = new byte[32768];
//				while( (read = is.read(buffer)) > 0) {
//				  fos.write(buffer, 0, read);
//				}
//
//				fos.close();
//				is.close();
//				
//				FileResource fr2 = new FileResource(pdf);
//
//				getUI().getPage().open(fr2, "_blank", true);
				
				buttonSaveCvrNote.setEnabled(false);

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
			}
		}

	}
	
	void getByVehicleNo(String vehicleNo) {

		int i = 0;

		try {
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "/sim-m-quotation/ByVehicleNo/" + vehicleNo);
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
			i = ((org.apache.http.HttpResponse) response).getStatusLine().getStatusCode();

			if (i == 200) {
				quotationList = gson.fromJson(getRes, new TypeToken<List<SimQuotationListGet>>() {
				}.getType());
				
				
				if (quotationList.size() != 0) {
					Notification.show(
							"Quotation is already issued for selected vehicle number.\nPlease contact motor underwriting department to revise",
							Notification.TYPE_WARNING_MESSAGE);
					comboVehProv.setValue(null);
					txtVehLett.setValue("");
					txtVehNos.setValue("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);

		} finally {
			if (i == 500) {
				Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 400) {
				Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
			} else if (i == 404) {
				Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
			}
		}

	}
	
	public void getByQuotationNo(String quotationNo) {

		int i = 0;
		
		SimMotorQuotationSaveRequest gotQt = null;

		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "sim-m-quotation/ByQuotationNo/" + quotationNo);
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

			if (i == 200) {

				gotQt = gson.fromJson(getRes, SimMotorQuotationSaveRequest.class);
				if (gotQt != null) {

					clearForm();

					System.out.println(gotQt.getQuotationData());

					SimQuotationSaveData saveData = gson.fromJson(gotQt.getQuotationData(), SimQuotationSaveData.class);

					// txtChassis.setValue(gotQt.getMotorQuotationPrintModel().getChassisNo());
					txtCusAddressNo.setValue(gotQt.getCustomer().getCustomerAddressNo() == null ? ""
							: gotQt.getCustomer().getCustomerAddressNo());
					txtCusArea.setValue(gotQt.getCustomer().getCustomerAddressArea() == null ? ""
							: gotQt.getCustomer().getCustomerAddressArea());
					txtCusInitName.setValue(gotQt.getCustomer().getCustomerInitialName() == null ? ""
							: gotQt.getCustomer().getCustomerInitialName());
					txtCusLastName.setValue(gotQt.getCustomer().getCustomerLastName() == null ? ""
							: gotQt.getCustomer().getCustomerLastName());
					txtCusStreet.setValue(gotQt.getCustomer().getCustomerAddressStreet() == null ? ""
							: gotQt.getCustomer().getCustomerAddressStreet());
					txtEngineNo.setValue(saveData.getNonRiskdata().getEngineIdCtrl());
					txtMobile.setValue(gotQt.getCustomer().getCustomerPhone() == null ? ""
							: gotQt.getCustomer().getCustomerPhone());
					txtNIC.setValue(
							gotQt.getCustomer().getCustomerNic() == null ? "" : gotQt.getCustomer().getCustomerNic());
					txtSeatCount.setValue(saveData.getNonRiskdata().getSeatCountIdCtrl() + "");
					txtSumInsured.setValue(new DecimalFormat("0.00").format(gotQt.getSumInsured()));

					if (gotQt.getIntmdCovernoteReasons() != null) {
						for (Object col : tableCNReason.getItemIds()) {
							
							String cnReasonName = tableCNReason.getContainerProperty(col, "Name").getValue().toString();
							
							if(gotQt.getIntmdCovernoteReasons().contains(cnReasonName)) {
								
								CheckBox cb=(CheckBox)tableCNReason.getContainerProperty(col, "+").getValue();
								cb.setValue(true);
							}
							
						}
					}
					
					String vehiNo = gotQt.getVehicleNo();

					comboVehProv.select(VehiNoPatternValidator.divide(vehiNo).get(0));
					txtVehLett.setValue(VehiNoPatternValidator.divide(vehiNo).get(1));
					txtVehNos.setValue(VehiNoPatternValidator.divide(vehiNo).get(2));

					comboVehProv.setEnabled(false);
					txtVehLett.setEnabled(false);
					txtVehNos.setEnabled(false);

					for (PeCoverType covertype : peCoverTypes) {
						if (gotQt.getPeCoverTypeId().equals(covertype.getId())) {
							comboCoverType.select(covertype.getName());
							break;
						}
					}

					comboCusCity.select(gotQt.getCustomer().getCustomerAddressCity());

					for (Location m : locations) {
						if (gotQt.getLocationId().equals(m.getId())) {
							comboLocation.select(m.getInitialName().toUpperCase());
							break;
						}
					}

//					System.out.println("vehi type - "+saveData.getNonRiskdata().getVehicleTypeNameCtrl());
					
//					comboVehType.select(saveData.getNonRiskdata().getVehicleTypeNameCtrl());
					comboMakeModel.select(saveData.getNonRiskdata().getVehicleMakeName());

					for (PeCoverType m : peCoverTypes) {
						if (gotQt.getPeCoverTypeId().equals(m.getId())) {
							comboCoverType.select(m.getName());
							break;
						}
					}

					for (PePolicyCategory m : pePolcats) {

						System.out.println("Pol Cat " + saveData.getPolicyData().getPoliceCategoryIdCtrl());
						System.out.println("Pol Cat* " + m.getPeObjectId());

						if (saveData.getPolicyData().getPoliceCategoryIdCtrl().equals(m.getPeObjectId())) {
							comboPolCategory.select(m.getPeObjectName());
							break;
						}
					}

					for (PePolicyUsage m : pePolUs) {
						if (saveData.getPolicyData().getUsageIdCtrl().equals(m.getUsageId())) {
							comboPolUsage.setValue(m.getUsageName());
							break;
						}
					}

					for (PeProductListItem m : peProdList) {
						// System.out.println("got prd "+m.getPeCategoryName()+"
						// "+m.getPeProductCatId());
						// System.out.println("svd prd "+saveData.getPolicyData().getProductIdCtrl());
						if (saveData.getPolicyData().getProductIdCtrl().equals(m.getPeProductCatId())) {
							comboProduct.setValue(m.getPeCategoryName());
							break;
						}
					}

					for (PePackage m : pePackage) {
						if (saveData.getPolicyData().getPackageNameIdCtrl().equals(m.getPackageId())) {
							comboPePackageName.setValue(m.getPackageName());
							break;
						}
					}

					for (PeriodOfCover m : periodCover) {
						if (saveData.getPolicyData().getPeriodOfCoverIdCtrl().equals(m.getId())) {
							comboPeriodOfCover.setValue(m.getName());
							break;
						}
					}

//					comboSalesStaff.select(saveData.getPolicyData().getAgentCodeCtrl());

					// comboShortPeriodOpions.setValue(null);

					comboYOM.select(saveData.getNonRiskdata().getManufacturedCtrl() + "");

//					labelCalNetRate.setValue("0.00");
//					labelDefNetRate.setValue("0.00");

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					dateExpireDate.setValue(sdf.parse(saveData.getPolicyData().getQuotationExpireDateCtrl()));
					dateStartDate.setValue(sdf.parse(saveData.getPolicyData().getStartDateCtrl()));
					dateEndDate.setValue(sdf.parse(saveData.getPolicyData().getEndDateCtrl()));

					for (SimQuotationCoverData peril : saveData.getCalculatedList()) {

						if (peril.getType().equals("Cover")) {

							// System.out.println("Cover : "+peril.getId() +" "+peril.getName());

							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Cover")) {

									Cover cvr = (Cover) tablePerils.getContainerProperty(col, "..").getValue();

									// System.out.println("Searching : "+cvr.getId()+" "+cvr.getPeCoverName());

									if (cvr.getId().equals(peril.getId())) {

										if (cvr.getPeCoverValueBasisAlias().equals("VSC")
												|| cvr.getPeCoverValueBasisAlias().equals("UEV")) {

											TextField txtF = (TextField) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											txtF.setValue(peril.getTxtValue());

											if (cvr.getPeCoverValueBasisAlias().equals("VSC")) {

												TextField txtSc = (TextField) ((VerticalLayout) tablePerils
														.getContainerProperty(col, "Value").getValue()).getComponent(1);

												txtSc.setValue(peril.getSeatCount());

											}
										} else if (cvr.getPeCoverValueBasisAlias().equals("RNG")
												|| cvr.getPeCoverValueBasisAlias().equals("RSC")) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());

											if (cvr.getPeCoverValueBasisAlias().equals("RSC")) {

												TextField txtSc = (TextField) ((VerticalLayout) tablePerils
														.getContainerProperty(col, "Value").getValue()).getComponent(1);
												txtSc.setValue(peril.getSeatCount());
											}
										}

										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);
										// System.out.println("found");

										break;
									}

								}

							}

						} else if (peril.getType().equals("Discount")) {

							// System.out.println("Discount : "+peril.getId() +" "+peril.getName());

							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Discount")) {

									Discount cvr = (Discount) tablePerils.getContainerProperty(col, "..").getValue();

									if (cvr.getId().equals(peril.getId())) {

										if (cvr.getIsRange() == 1 || cvr.getAlias().equals("DVO")
												|| cvr.getAlias().equals("DVM")) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());
										}

										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);

										// System.out.println("found");

										break;
									}
								}

							}

						} else if (peril.getType().equals("Loading")) {

							// System.out.println("Loading : "+peril.getId() +" "+peril.getName());

							for (Object col : tablePerils.getItemIds()) {

								String type = tablePerils.getContainerProperty(col, "Type").getValue().toString();

								if (type.equals("Loading")) {
									Loading cvr = (Loading) tablePerils.getContainerProperty(col, "..").getValue();

									if (cvr.getId().equals(peril.getId())) {

										if (peril.getComboValue() != null) {

											ComboBox cmb = (ComboBox) ((VerticalLayout) tablePerils
													.getContainerProperty(col, "Value").getValue()).getComponent(0);

											cmb.select(peril.getComboValue());
										}
										CheckBox sel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();
										sel.setValue(true);

										// System.out.println("found");

										break;
									}
								}

							}
						} else if (peril.getType().equals("PaymentLine")) {

							PaymentLineResp pl = new PaymentLineResp();
							pl.setId(peril.getId());
							pl.setAlias(peril.getAlias());
							pl.setIsDisplayed(peril.getIsDisplayed());

							tablePmtLine.addItem(new Object[] { peril.getName(),
									new DecimalFormat("0.00").format(peril.getValue()), peril.getOrder(), pl },
									tablePmtLine.size());
						}

					}

					lblTotPremium.setValue(new DecimalFormat("#,##0.00").format(gotQt.getTotalPremium()));
					savedQuoId = gotQt.getId();
					loadedQuoNo = gotQt.getQuotationNo();
					labelLoadedQuoNo.setValue(loadedQuoNo);

					buttonSaveQuotation.setCaption("Revise");
					buttonSaveCvrNote.setEnabled(true);

				}
				
				if (gotQt.getIntmdCnGenerated() == true) {
					
					buttonSaveCvrNote.setCaption("Print");
					
					disableComponenets();
					
					for (Object col : tablePerils.getItemIds()) {

						CheckBox chbSel = (CheckBox) tablePerils.getContainerProperty(col, "+").getValue();

						chbSel.setEnabled(false);

					}
					
					for (Object colCnReason : tableCNReason.getItemIds()) {

						CheckBox chbSel = (CheckBox) tableCNReason.getContainerProperty(colCnReason, "+").getValue();

						chbSel.setEnabled(false);

					}

				}

				isUpdate = true;
				
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
			}
		}
	}

	void fileUpload() {

		class FileUploader implements Receiver, SucceededListener {

			private static final long serialVersionUID = 1L;
			public File folder;
			public File file;
			
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {

				FileOutputStream fos = null;

				try {
					
					folder = new File("C:/CustomerDetailsDocuments/" + savedQuoNo);
					
					boolean bool = folder.mkdirs();
							
					file = new File(folder, filename);
					
					fos = new FileOutputStream(file);

				} catch (final java.io.FileNotFoundException e) {

					new Notification("Could not open file", e.getMessage(), Notification.Type.ERROR_MESSAGE)
							.show(Page.getCurrent());

					return null;

				}

				return fos;

			}

			@Override
			public void uploadSucceeded(SucceededEvent event) {
				// Show the uploaded file in the image viewer
//				readExcel(file);
				
				int number = tableDocumentUpload.size();
				String docName = comboDocumentName.getValue().toString();
				
				tableDocumentUpload.addItem(new Object[] {
						number + 1,
						docName
					},
						tableDocumentUpload.size()
					);
			}
		};
		
		FileUploader receiver = new FileUploader();

		uploadDocument.setReceiver(receiver);
		uploadDocument.addSucceededListener(receiver);
		
	}

	void disableNonFleetComponents() {

		optionGroupRegNonReg.setEnabled(false);
		comboVehProv.setEnabled(false);
		txtVehNos.setEnabled(false);
		txtVehLett.setEnabled(false);
		txtEngineNo.setEnabled(false);
		txtChassis.setEnabled(false);
		txtSumInsured.setEnabled(false);
//		txtSeatCount.setEnabled(false);
		comboMakeModel.setEnabled(false);
		tablePerils.setEnabled(false);
		buttonCalTotalPre.setEnabled(false);
		tablePmtLine.setEnabled(false);

	}

	void enableNonFleetComponents() {

		optionGroupRegNonReg.setEnabled(true);
		comboVehProv.setEnabled(true);
		txtVehNos.setEnabled(true);
		txtVehLett.setEnabled(true);
		txtEngineNo.setEnabled(true);
		txtChassis.setEnabled(true);
		txtSumInsured.setEnabled(true);
//		txtSeatCount.setEnabled(true);
		comboMakeModel.setEnabled(true);
		tablePerils.setEnabled(true);
		buttonCalTotalPre.setEnabled(true);
		tablePmtLine.setEnabled(true);

	}
	
	void disableComponenets() {
		
		optionGroupRegNonReg.setEnabled(false);
		comboVehProv.setEnabled(false);
		txtVehLett.setEnabled(false);
		txtVehNos.setEnabled(false);
		txtChassis.setEnabled(false);
		txtEngineNo.setEnabled(false);
		txtSumInsured.setEnabled(false);
		txtSeatCount.setEnabled(false);
//		comboVehType.setEnabled(false);
		btnCalculatePerils.setEnabled(false);
		txtNIC.setEnabled(false);
		txtMobile.setEnabled(false);
		txtCusInitName.setEnabled(false);
		txtCusLastName.setEnabled(false);
		txtCusAddressNo.setEnabled(false);
		txtCusStreet.setEnabled(false);
		txtCusArea.setEnabled(false);
		comboCusCity.setEnabled(false);
		comboYOM.setEnabled(false);
		comboMakeModel.setEnabled(false);
		dateEndDate.setEnabled(false);
		buttonSaveQuotation.setEnabled(false);
		
	}

}
