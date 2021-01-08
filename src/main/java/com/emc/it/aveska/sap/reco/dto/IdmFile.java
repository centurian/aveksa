package com.emc.it.aveska.sap.reco.dto;


public class IdmFile {
	private String SID;
	private String UserName;
	private String LastLogonDate;
	
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getLastLogonDate() {
		return LastLogonDate;
	}
	public void setLastLogonDate(String lastLogonDate) {
		LastLogonDate = lastLogonDate;
	}

}
