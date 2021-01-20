package com.example.laptopium.networkmanager.authentication.model;

import com.google.gson.annotations.SerializedName;

public class UserRegistered{

	@SerializedName("userImageUrl")
	private String userImageUrl;

	@SerializedName("loginTimeStamp")
	private String loginTimeStamp;

	@SerializedName("userPswd")
	private String userPswd;

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

	public void setUserPswd(String userPswd){
		this.userPswd = userPswd;
	}

	public String getUserPswd(){
		return userPswd;
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
			"UserRegistered{" + 
			"userImageUrl = '" + userImageUrl + '\'' + 
			",loginTimeStamp = '" + loginTimeStamp + '\'' + 
			",userPswd = '" + userPswd + '\'' + 
			",userMail = '" + userMail + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}