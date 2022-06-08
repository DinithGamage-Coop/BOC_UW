package lk.ci.int_cn_system.UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.vaadin.dialogs.ConfirmDialog;

import lk.ci.int_cn_system.Model.Auth.RoleCheck;
import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.UI.Auth.PasswordResetSub;
import lk.ci.int_cn_system.UI.Auth.UserPasswordResetSub;
import lk.ci.int_cn_system.UI.Auth.UserRegistrationSub;
import lk.ci.int_cn_system.UI.SimQuotation.BusinessReportSub;
import lk.ci.int_cn_system.UI.SimQuotation.SimQuotationPaymentApprovalSub;
import lk.ci.int_cn_system.UI.SimQuotation.SimQuotationRegSub;
import lk.ci.int_cn_system.UI.SimQuotation.SimQuotationSub;
import lk.ci.int_cn_system.Utils.ConstantData;

import com.google.gson.Gson;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class DashBoardSub extends DashBoard {

	private String token;

	SimQuotationSub simQuotationSub;
	UserRegistrationSub userRegistrationSub;
	UserPasswordResetSub userPasswordResetSub;
	BusinessReportSub businessReportSub;
	SimQuotationPaymentApprovalSub simQuotationPaymentApprovalSub;
	private Window win;
	private String FullName;
	private DashBoardSub mainUI;

	Command commandSettings;
	Command commandProfile;

//	User loggedInUser;
	UserResponseModel user = new UserResponseModel();

	public void setLoggedInUser(UserResponseModel loggedInUser) {

		user = loggedInUser;

		this.token = loggedInUser.getToken();
		loadUserDetails(loggedInUser);

		treeMain.addValueChangeListener(new ValueChangeListener() {

			// @Override
			public void valueChange(ValueChangeEvent event) {

				if (treeMain.getValue() != null) {

					if (treeMain.getValue().toString().equals("Quotation Register")) {

						verticalLayout.removeAllComponents();

						SimQuotationRegSub simQuotationRegSub = new SimQuotationRegSub();

						simQuotationRegSub.sendDashBoard(DashBoardSub.this, loggedInUser);

						verticalLayout.addComponent(simQuotationRegSub);

					} else if (treeMain.getValue().toString().equals("New Quotation")) {

						verticalLayout.removeAllComponents();

						if (simQuotationSub == null) {

							simQuotationSub = new SimQuotationSub();

							simQuotationSub.setUser(loggedInUser);

						}

						verticalLayout.addComponent(simQuotationSub);

					}
					else if (treeMain.getValue().toString().equals("Approval list")) {

						verticalLayout.removeAllComponents();

						if (simQuotationPaymentApprovalSub == null) {

							simQuotationPaymentApprovalSub = new SimQuotationPaymentApprovalSub();

							simQuotationPaymentApprovalSub.setUser(loggedInUser);

						}

						verticalLayout.addComponent(simQuotationPaymentApprovalSub);

					}

					else if (treeMain.getValue().toString().equals("Add New User")) {

						verticalLayout.removeAllComponents();

						if (userRegistrationSub == null) {

							userRegistrationSub = new UserRegistrationSub();

							userRegistrationSub.setUser(loggedInUser);

						}

						verticalLayout.addComponent(userRegistrationSub);
//
					}
					
					else if (treeMain.getValue().toString().equals("Bussiness Report")) {

						verticalLayout.removeAllComponents();

						if (businessReportSub == null) {

							businessReportSub = new BusinessReportSub();

							businessReportSub.setUser(loggedInUser);

						}

						verticalLayout.addComponent(businessReportSub);
//
					}
					

					else {

						verticalLayout.removeAllComponents();

					}

				}

			}
		});

//		treeMain.expandItem("Motor");
//
//		treeMain.setValue("Quotation Register");
		
		treeMain.expandItem("BOC Quotation");
		
		treeMain.setValue("Approval list");

		commandPasswordReset = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {

				// verticalLayout.removeAllComponents();

				Window dd = new Window();

				if (userPasswordResetSub == null) {

					userPasswordResetSub = new UserPasswordResetSub();

					 userPasswordResetSub.setUser(loggedInUser);
					
					 
				
				}

				UserPasswordResetSub us = new UserPasswordResetSub();
				us.setUser(loggedInUser);
				dd.setContent(us);
				dd.center();
				dd.setWidth("450px");
				dd.setHeight("450px");
				dd.setResizable(false);
				dd.setModal(true);
				
				UI.getCurrent().addWindow(dd);

				// verticalLayout.addComponent(userPasswordResetSub);

			}
		};

		commandLogOut = new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {

				getSession().close();
				Page.getCurrent().open("http://172.20.11.177:8081/BOC-UW/", null);

			}
		};

//		menuBar.addItem("Settings", FontAwesome.GEAR, commandSettings);
		MenuItem profile = menuBar.addItem("", FontAwesome.USER, null);
		profile.addItem("Change Password", commandPasswordReset);
		profile.addItem("Logout", commandLogOut);

	}

	public void setTreeValue(String val) {

		treeMain.setValue(val);

	}

	Command commandPasswordReset;
	Command commandLogOut;

	public DashBoardSub() {
		// TODO Auto-generated constructor stub

		treeMain.addItem("BOC Quotation");
		treeMain.setItemIcon("Motor", FontAwesome.CAR);

//		treeMain.addItem("Quotation Register");
//		treeMain.setParent("Quotation Register", "Motor");
//		treeMain.setChildrenAllowed("Quotation Register", false);
//		
//		treeMain.addItem("New Quotation");
//		treeMain.setParent("New Quotation", "Motor");
//		treeMain.setChildrenAllowed("New Quotation", false);
		
		treeMain.addItem("Approval list");
		treeMain.setParent("Approval list", "BOC Quotation");
		treeMain.setChildrenAllowed("Approval list", false);
		
		treeMain.addItem("Bussiness Report");
		treeMain.setParent("Bussiness Report", "BOC Quotation");
		treeMain.setChildrenAllowed("Bussiness Report", false);

	
//		treeMain.addItem("Non-Motor");
//
//		treeMain.addItem("Quotation Reg");
//		treeMain.setParent("Quotation Reg", "Non-Motor");
//		treeMain.setChildrenAllowed("Quotation Reg", false);
//
//		treeMain.addItem("New Quote");
//		treeMain.setParent("New Quote", "Non-Motor");
//		treeMain.setChildrenAllowed("New Quote", false);
//		
//		
//
//		treeMain.addItem("User Registration");
//
//		treeMain.addItem("Add New User");
//		treeMain.setParent("Add New User", "User Registration");
//		treeMain.setChildrenAllowed("Add New User", false);

	}

	public void setFrame(Component component) {

		verticalLayout.removeAllComponents();
		verticalLayout.addComponent(component);

	}

	void loadUserDetails(UserResponseModel loggedInUser) {
		// TODO Auto-generated method stub
		try {

			File photo = new java.io.File(
					"\\\\172.20.10.20\\GENERAL\\pics\\" + loggedInUser.getUser().getUsername() + ".png");
			if (photo.exists()) {

				FileResource fr = new FileResource(photo);
				fr.setCacheTime(0);

				imageProfile.setSource(fr);

			} else {

				FileResource fr = new FileResource(new File("C:/APPS/app_icons/facebook.png"));
				fr.setCacheTime(0);

				imageProfile.setSource(fr);
			}

			labelUserName.setValue(loggedInUser.getUser().getName());
//			labelUserBranch.setValue(loggedInUser.getBranch());

		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			
			
			
		}
	}

}
