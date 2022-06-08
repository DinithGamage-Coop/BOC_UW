package lk.ci.int_cn_system.UI.Auth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;

import lk.ci.int_cn_system.Utils.ConstantData;
import com.google.gson.Gson;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class PasswordResetSub extends PasswordReset{
	
	public PasswordResetSub() {		
	}
	
	String username;
	Window thisw;

	public void getDetails(Window ww,String username,String email) {
		// TODO Auto-generated method stub
		
//		label.setValue("Verification e-mail is sent to "+email+". "
//				+ "If it has not been recieved to inbox,Check the spams. "
//				+ "Copy the OTP from e-mail and paste it to text field below.");
//		
//		this.username=username;
//		thisw=ww;
		
		button_save.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				save();
			}
		});
		
	}
	
	void save() {
		
		//if(textField_OTP.getValue().trim().isEmpty()) {
			//Notification.show("Enter the OTP");
		if(passwordField_1.getValue().isEmpty() || passwordField_2.getValue().isEmpty()) {
			Notification.show("Enter the password");
		}else if(! passwordField_1.getValue().equals(passwordField_2.getValue())) {
			Notification.show("Passwords are not matched !");			
		}else {
			
			try {
				 
				 
				int responseCode = 0;
				try {
					 
					Gson gson = new Gson();
					HttpClient client = HttpClientBuilder.create().build();
					HttpPut request = new HttpPut(ConstantData.baseUrl+"users/PasswordReset/OTP/"
					+textField_OTP.getValue().trim()+"/username/"+username+"/password/"+passwordField_2.getValue());
					request.setHeader("Accept", "application/json");
					request.setHeader("Content-Type", "application/json");

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
						
						  Notification.show("Password reset successfull !");
						  thisw.close();

					} else if (responseCode == 400) {
						Notification.show("Error", "Sorry, Wrong OTP !", Notification.TYPE_ERROR_MESSAGE);
					} else if (responseCode == 404) {
						Notification.show("Error", "No records Found", Notification.TYPE_ERROR_MESSAGE);
					} else if (responseCode == 401) {
						Notification.show("Error", "Unauthorized", Notification.TYPE_ERROR_MESSAGE);
					} else if (responseCode == 500) {
						Notification.show("Error", "Server Error", Notification.TYPE_ERROR_MESSAGE);
					} else {
						Notification.show("Error", Integer.toString(responseCode), Notification.TYPE_ERROR_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
					Notification.show(e.toString(), Notification.Type.ERROR_MESSAGE);
				}
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}

}
