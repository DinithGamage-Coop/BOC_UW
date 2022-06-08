package lk.ci.int_cn_system.UI.SimQuotation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.Model.SimQuotation.Cover;
import lk.ci.int_cn_system.Model.SimQuotation.Discount;
import lk.ci.int_cn_system.Model.SimQuotation.Excess;
import lk.ci.int_cn_system.Model.SimQuotation.Loading;
import lk.ci.int_cn_system.Model.SimQuotation.Location;
import lk.ci.int_cn_system.Model.SimQuotation.MakeModel;
import lk.ci.int_cn_system.Model.SimQuotation.PaymentLineResp;
import lk.ci.int_cn_system.Model.SimQuotation.PeCoverType;
import lk.ci.int_cn_system.Model.SimQuotation.PePackage;
import lk.ci.int_cn_system.Model.SimQuotation.PePolicyCategory;
import lk.ci.int_cn_system.Model.SimQuotation.PePolicyUsage;
import lk.ci.int_cn_system.Model.SimQuotation.PeProductListItem;
import lk.ci.int_cn_system.Model.SimQuotation.PeriodOfCover;
import lk.ci.int_cn_system.Model.SimQuotation.SalesStaff;
import lk.ci.int_cn_system.Model.SimQuotation.SimMotorQuotationSaveRequest;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationCoverData;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationListGet;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationSaveData;
import lk.ci.int_cn_system.Model.SimQuotation.VehicleType;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;
import lk.ci.int_cn_system.Utils.VehiNoPatternValidator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VehicleNoSearchWindowSub extends VehicleNoSearchWindow {

	private Window win;
	private SimQuotationSub mainUI;
	private SimQuotationRegSub mainUI1;
	private boolean isVehicleSearch;
	UserResponseModel loggedInUser = new UserResponseModel();
	private String token;

	List<SimQuotationListGet> quotationList;

	/*
	 * Set dash board Object
	 */
	public void sendUsers(UserResponseModel loggedInUser) {
		this.loggedInUser = loggedInUser;
		this.token = loggedInUser.getToken();

	}

	public VehicleNoSearchWindowSub() {

		tblQtListVehicleNo.addContainerProperty("#", Integer.class, null);
		tblQtListVehicleNo.addContainerProperty("Quotation No.", String.class, null);
		tblQtListVehicleNo.addContainerProperty("Vehicle No.", String.class, null);
		tblQtListVehicleNo.addContainerProperty("Location", String.class, null);
		tblQtListVehicleNo.addContainerProperty("Total Premium", Double.class, null);
		tblQtListVehicleNo.addContainerProperty("Action", Button.class, null);
	}

	public void getWindow(Window w, SimQuotationSub mUI) {
		win = w;
		mainUI = mUI;
		isVehicleSearch = true;
	}

	public void getWindow1(Window w, SimQuotationRegSub mUI1) {
		win = w;
		mainUI1 = mUI1;
		isVehicleSearch = false;
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

	void rowAdd(SimQuotationListGet existingQuotationList) {

		int number = tblQtListVehicleNo.size();
		String id = existingQuotationList.getId();
		String qtNum = existingQuotationList.getQuotationNo();
		String vehiNum = existingQuotationList.getVehicleNo();
		String location = getLocation(existingQuotationList.getLocationId());
		Double totPremium = existingQuotationList.getTotalPremium();

		Button btnEdit = new Button(FontAwesome.EDIT);
		btnEdit.addStyleName("primary");
		btnEdit.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				SimQuotationSub qtSub = new SimQuotationSub();
				qtSub.getById(id);
				win.close();

			}
		});

		if (isVehicleSearch) {
			if (!loggedInUser.getUser().getLocation().getInitialName().equalsIgnoreCase(location)) {
				btnEdit.setEnabled(false);
			}
			if (loggedInUser.getUser().getLocation().getInitialName().equalsIgnoreCase("HEAD OFFICE")) {
				btnEdit.setEnabled(true);
			}
		}

		tblQtListVehicleNo.addItem(new Object[] { number + 1, qtNum, vehiNum, location, totPremium, btnEdit }, number);
	}

	String getLocation(String lid) {

		String location = "";
		int i = 0;

		try {
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "location/" + lid);
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
				Location loc = gson.fromJson(getRes, Location.class);
				location = loc.getInitialName();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);

		}

		return location;
	}

	void loadToTable() {

		try {
			tblQtListVehicleNo.removeAllItems();
			for (SimQuotationListGet existingQuotationList : quotationList) {
				rowAdd(existingQuotationList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}
	}

	void getRevisionHistory(String quoteNo) {

		int i = 0;

		try {
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(ConstantData.baseUrl + "/sim-m-quotation/RevisionHistory/" + quoteNo);
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

	 

}
