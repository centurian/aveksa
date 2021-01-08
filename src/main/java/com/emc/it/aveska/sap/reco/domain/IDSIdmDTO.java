package com.emc.it.aveska.sap.reco.domain;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;




public class IDSIdmDTO {

	public String SID ;
	public String USER_NAME;
	public XMLGregorianCalendar LAST_LOGON_DATE;
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public XMLGregorianCalendar getLAST_LOGON_DATE() {
		return LAST_LOGON_DATE;
	}
	public void setLAST_LOGON_DATE(XMLGregorianCalendar lAST_LOGON_DATE) {
		LAST_LOGON_DATE = lAST_LOGON_DATE;
	}
	
	
}
