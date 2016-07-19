package com.hackthon.bean;

/**
 * Created by xiamingxing on 2014/5/7.
 */
public class LoginInfo {
    private String userName;
    private String passWord;
    
    public LoginInfo(String userName, String passWord){
    	this.userName = userName;
    	this.passWord = passWord;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
