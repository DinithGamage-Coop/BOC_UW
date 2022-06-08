package lk.ci.int_cn_system.UI.Auth;

import lk.ci.int_cn_system.Model.Auth.UserResponseModel;
import lk.ci.int_cn_system.UI.DashBoardSub;

public class UserRegistrationSub extends UserRegistration {

	DashBoardSub dashBsub;
	
	UserResponseModel loggedInUser; 
	
	private String token;
	UserResponseModel user;
	
	
	public UserRegistrationSub() {
		
		
	}
	
	public void setUser(UserResponseModel user) {
		this.user = user;

		this.token = user.getToken();
	}
	
}
