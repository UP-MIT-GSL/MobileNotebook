package com.example.mobilenotebookui;

public class User {
	  private String user_id;
	  private String token;

	  public String getUserId() {
	    return user_id;
	  }

	  public void setUserId(String user_id) {
	    this.user_id = user_id;
	  }

	  public String getToken(){
		  return token;
	  }
	  
	  public void setToken(String token){
		  this.token = token;
	  }
	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return user_id;
	  }
	} 
