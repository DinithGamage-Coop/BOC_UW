package lk.ci.int_cn_system.UI.Auth;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer.Form;
import java.util.FormatterClosedException;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.atmosphere.cpr.ApplicationConfig;
import org.vaadin.dialogs.ConfirmDialog;

import lk.ci.int_cn_system.MyUI;
import lk.ci.int_cn_system.Model.Auth.UserModel;
import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.UI.DashBoardSub;
import lk.ci.int_cn_system.Utils.ConstantData;

import com.google.gson.Gson;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.LoginForm;

public class UserPasswordResetSub extends UserPasswordReset {
	// List<UserPasswordReset> UserPasswordReset;
	private Window win;
	String namm;
	String idd;
	String username;
	String epf;
	String token = "";

	public UserPasswordResetSub() {
		// TODO Auto-generated method stub

		button_change_pw.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

				if (passwordField_new.getValue().equals("")) {

					Notification.show("Enter a password");

				} else if (passwordField_confirm.getValue().trim().equals(passwordField_new.getValue().trim())) {

					
					ConfirmDialog.show(getUI(), "Confirmation", "Are you sure to confirm", "Yes", "No",
							new ConfirmDialog.Listener() {
								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {									
										getSession().close(); 
										Page.getCurrent().open("http://localhost:8081/int-cn-system/", null);
										Notification.show("Your password has been changed successfully");
//									
					///////////////////////////////////////////////

					int responseCode = 0;
					try {
						UserModel cn = new UserModel();

						cn.setId(idd);
						// cn.setName(namm);
						cn.setUsername(username);

						// cn.setEpfNo(epf);
						cn.setPassword(passwordField_confirm.getValue());

						//

						Gson gson = new Gson();
						HttpClient client = HttpClientBuilder.create().build();
						HttpPut request = new HttpPut(ConstantData.baseUrl + "users");

						request.setHeader("Accept", "application/json");
						request.setHeader("Content-Type", "application/json");
						request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
						System.out.println(gson.toJson(cn));

						// only for post & Put
						StringEntity body = new StringEntity(gson.toJson(cn));
						request.setEntity(body);

						HttpResponse response = client.execute(request);
						//InputStream inputStream = (InputStream) response.getEntity().getContent();
						InputStream inputStream = response.getEntity().getContent();

						BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
						String inputLine;
						String getRes = "";
						while ((inputLine = in.readLine()) != null) {
							getRes = getRes + inputLine + "\n";
						}
						in.close();
						responseCode = response.getStatusLine().getStatusCode();

						System.out.println(responseCode);

						if (responseCode == 200) {
							System.out.println(getRes);

						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						// Notification.show("Error", e.getMessage(),
						// Notification.TYPE_ERROR_MESSAGE);

					} finally {

						if (responseCode == 500) {
							Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
						} else if (responseCode == 400) {
							Notification.show("Error", "Validation Error", Notification.TYPE_ERROR_MESSAGE);
						} else if (responseCode == 404) {
							Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
						}
					}

//											
//								});
//						      	
//										
//									
//									
//									
//									
//									

					//////////////////////////////////////////////////
									}
								}
							});

				}

				else {
					Notification.show("Those passwords didn’t match. Try again.");
					String id = passwordField_new.getValue();
					// getById(id);

				}

			}
		});

	}

	public void setUser(UserResponseModel loggedInUser) {
		// TODO Auto-generated method stub
		try {

			usernamet.setValue(loggedInUser.getUser().getName());
			namm = loggedInUser.getUser().getName();
			idd = loggedInUser.getUser().getId();
			username = loggedInUser.getUser().getUsername();
			token = loggedInUser.getToken();

			epf = loggedInUser.getUser().getEpfNo();

			System.out.println("login user" + namm);
			// labelUserName.setValue(loggedInUser.getUser().getName());
			// labelUserBranch.setValue(loggedInUser.getBranch());

		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

}
