

package com.hackthon.bean;

public class UserInfoBean {
	
	private String userName;
	private String passwd;
	private String Qid;

	
	public UserInfoBean(String userName, String Qid , String passwd){
		this.setUserName(userName);
		this.setQid(Qid);
		this.setPasswd(passwd);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQid() {
		return Qid;
	}

	public String setPasswd(String passwd){
		return passwd;
	}
	public void setQid(String qid) {
		this.Qid = qid;
	}
	public String getPassWord() {
		return passwd;
	}

	public void setPassWord(String passWord) {
		this.passwd = passWord;
	}

}
