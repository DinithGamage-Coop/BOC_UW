package lk.ci.int_cn_system.UI.SimQuotation;

import java.io.BufferedReader;
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
import org.apache.http.impl.client.HttpClientBuilder;

import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.Model.SimQuotation.Location;
import lk.ci.int_cn_system.Model.SimQuotation.SimQuotationListGet;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SimQuotationRegSub extends SimQuotationReg {

	DashBoardSub dashBsub;
	
	UserResponseModel loggedInUser; 
	
	List<SimQuotationListGet> quotationList;

	List<Location> locations;
	HashMap<String, Location> mapLocations = new HashMap<>();
	
	private String token;

	public SimQuotationRegSub() {
		// TODO Auto-generated constructor stub

		table.addContainerProperty("#", Integer.class, null);
		table.addContainerProperty("Quotation No", String.class, null);
		table.addContainerProperty("Vehicle No.", String.class, null);
		table.addContainerProperty("Sum Insured", String.class, null);
		table.addContainerProperty("Date Issued", String.class, null);
		table.addContainerProperty("Convert to Cover Note", Label.class, null);
		table.addContainerProperty("Action", HorizontalLayout.class, null);

		table.setColumnWidth("Quotation No", 215);
//		table.setColumnWidth("Cover Note", 110);
		table.setColumnWidth("Vehicle No.", 125);
		table.setColumnWidth("Sum Insured", 110);
		table.setColumnWidth("Date Issued", 200);
		table.setColumnWidth("Action", 145);
		table.setSortContainerPropertyId("Date Issued");
		table.setSortAscending(false);

		textFieldSearch.addValueChangeListener(new Property.ValueChangeListener() {

			/*
			 * Quotation search filter
			 */
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				loadTable(textFieldSearch.getValue().trim());
			}
		});
		/*
		 * Navigate to new Quotation
		 */
		button_.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dashBsub.setTreeValue("New Quotation");
			}
		});
	}

	/*
	 * Set dash board Object
	 */
	public void sendDashBoard(DashBoardSub boardSub,UserResponseModel loggedInUser) {
		dashBsub = boardSub;
		this.loggedInUser = loggedInUser;		
		this.token = loggedInUser.getToken();
		loadLocation();		
	}

	/*
	 *
	 */
	void loadTable(String filter_text) {
		try {
			String loc = null;
			if (filter_text.isEmpty()) {
//				if (!dashBsub.getLoggedInUser().getBranch().equals("HEAD OFFICE")) {
//					loc = mapLocations.get(dashBsub.getLoggedInUser().getBranch()).getId();
//					LoadQuotations(loc);
				if (!loggedInUser.getUser().getLocation().getInitialName().equals("HEAD OFFICE")) {
					loc = loggedInUser.getUser().getLocation().getId();
					LoadQuotations(loc);
				} else {
					LoadQuotations(loc);
				}
			} else {
				if (!loggedInUser.getUser().getLocation().getInitialName().equals("HEAD OFFICE")) {
					loc = loggedInUser.getUser().getLocation().getId();
					quotationSearchHeadOffice(filter_text, loc);
				} else {
					quotationSearchHeadOffice(filter_text, loc);
				}
			}
			table.removeAllItems();
			for (SimQuotationListGet item : quotationList) {
				addToTable(item);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Notification.show("Error", e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
		}
	}

	/*
	 *
	 */
	void quotationSearchHeadOffice(String texta, String loc) {
		table.removeAllItems();
		int i = -1;
		try {
			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			String url = ConstantData.baseUrl + "sim-m-quotation"
					+ (loc == null ? ("/Search/" + texta.replaceAll(" ", "%20"))
							: ("/ByLocationId/" + loc + "/Search/" + texta.replaceAll(" ", "%20")));
			HttpGet request = new HttpGet(url);
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
			}else if (i == 401) {
				Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
			}else if (i == 200) {
				
			}else {
				Notification.show("Error", Integer.toString(i), Notification.TYPE_ERROR_MESSAGE);
			}
		}
	}

	/*
	 *
	 */
	void addToTable(SimQuotationListGet listGet) {
		HorizontalLayout layout = new HorizontalLayout();
		Button edit_btn = new Button(FontAwesome.EDIT);
		edit_btn.setWidth("45px");
		edit_btn.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				SimQuotationSub quotationSub = new SimQuotationSub();
				quotationSub.setUser(loggedInUser);
				quotationSub.getById(listGet.getId());
				dashBsub.setFrame(quotationSub);
			}
		});

		Button delete_btn = new Button(FontAwesome.REMOVE);
		delete_btn.addStyleName("danger");
		delete_btn.setWidth("45px");

		Button rev_history_btn = new Button(FontAwesome.REFRESH);
		rev_history_btn.addStyleName("friendly");
		rev_history_btn.setWidth("45px");

		rev_history_btn.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				VehicleNoSearchWindowSub revisionNumberWindowSub = new VehicleNoSearchWindowSub();

				revisionNumberWindowSub.getRevisionHistory(listGet.getQuotationNo());

				if (revisionNumberWindowSub.quotationList!= null && revisionNumberWindowSub.quotationList.size() != 0) {

					Window quotationRevisionHistoryWindow = new Window();

					quotationRevisionHistoryWindow.center();
					quotationRevisionHistoryWindow.setClosable(true);
					quotationRevisionHistoryWindow.setModal(true);
					quotationRevisionHistoryWindow.setResponsive(true);
					quotationRevisionHistoryWindow.setResizable(false);
					quotationRevisionHistoryWindow.setWidth("800px");
					quotationRevisionHistoryWindow.setHeight("450px");

					revisionNumberWindowSub.getWindow1(quotationRevisionHistoryWindow, SimQuotationRegSub.this);
					quotationRevisionHistoryWindow.setContent(revisionNumberWindowSub);

					revisionNumberWindowSub.getRevisionHistory(listGet.getQuotationNo());

					revisionNumberWindowSub.loadToTable();
					UI.getCurrent().addWindow(quotationRevisionHistoryWindow);

				} else {
					Notification.show("There are no revision history for this quotation...",
							Notification.TYPE_WARNING_MESSAGE);
				}
			}
		});

		layout.addComponent(edit_btn, 0);
		layout.addComponent(rev_history_btn, 1);
		layout.addComponent(delete_btn, 2);
		
		Label cvrtCn = new Label();
		
		if (listGet.getIntmdCnGenerated() == true) {
			cvrtCn.setValue("YES");
		} else {
			cvrtCn.setValue("NO");
		}

		table.addItem(
				new Object[] { table.size() + 1, listGet.getQuotationNo(),
						listGet.getVehicleNo(), new DecimalFormat("0.00").format(listGet.getSumInsured()),
						new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(listGet.getIssuedDateTime()),
						cvrtCn, 
						layout },
				table.size());

	}

	/*
	 *
	 */
	void LoadQuotations(String locId) {
		table.removeAllItems();
		int i = -1;
		try {

			Gson gson = new Gson();
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					ConstantData.baseUrl + "sim-m-quotation" + (locId == null ? "" : ("/ByLocationId/" + locId)));
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
			}else if (i == 401) {
				Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
			}else if (i == 200) {
				
			}else {
				Notification.show("Error", Integer.toString(i), Notification.TYPE_ERROR_MESSAGE);
			}
			
		}
	}

	/*
	 *
	 */
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
						for (Location loc : locations) {
							mapLocations.put(loc.getInitialName().toUpperCase(), loc);
						}
						String loc = null;
						System.out.println(loggedInUser.getUser());
						
						if (!loggedInUser.getUser().getLocation().getInitialName().equals("HEAD OFFICE")) {
							loc = loggedInUser.getUser().getLocation().getId();
						}
						LoadQuotations(loc);
						loadTable("");
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
				// Notification.show("Error", "No records Found",
				// Notification.TYPE_ERROR_MESSAGE);
			}else if (i == 401) {
				Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
			}else if (i == 200) {
				
			}else {
				Notification.show("Error", Integer.toString(i), Notification.TYPE_ERROR_MESSAGE);
			}
		}
	}
}
