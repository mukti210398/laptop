package com.example.laptopium.networkmanager.authentication.model;

import com.google.gson.annotations.SerializedName;

public class UserDetails{

    public UserDetails() {
    }

    @SerializedName("userImageUrl")
	private String userImageUrl;

	@SerializedName("loginTimeStamp")
	private String loginTimeStamp;

	@SerializedName("userMail")
	private String userMail;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;


	public void setUserImageUrl(String userImageUrl){
		this.userImageUrl = userImageUrl;
	}

	public String getUserImageUrl(){
		return userImageUrl;
	}

	public void setLoginTimeStamp(String loginTimeStamp){
		this.loginTimeStamp = loginTimeStamp;
	}

	public String getLoginTimeStamp(){
		return loginTimeStamp;
	}

	public void setUserMail(String userMail){
		this.userMail = userMail;
	}

	public String getUserMail(){
		return userMail;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"UserDetails{" + 
			"userImageUrl = '" + userImageUrl + '\'' + 
			",loginTimeStamp = '" + loginTimeStamp + '\'' + 
			",userMail = '" + userMail + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}