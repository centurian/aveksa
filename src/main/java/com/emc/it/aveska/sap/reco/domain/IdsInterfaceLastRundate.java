package com.emc.it.aveska.sap.reco.domain;

import javax.xml.datatype.XMLGregorianCalendar;

public class IdsInterfaceLastRundate {

	public String INTERFACE_NAME;
	public String LAST_RUN_DATE;
	public String BATCH_ID;
	public XMLGregorianCalendar CREATE_DATE;
	public XMLGregorianCalendar LAST_UPDATE_DATE;
	
	public IdsInterfaceLastRundate() {
		// TODO Auto-generated constructor stub
	}

	public String getINTERFACE_NAME() {
		return INTERFACE_NAME;
	}

	public void setINTERFACE_NAME(String iNTERFACE_NAME) {
		INTERFACE_NAME = iNTERFACE_NAME;
	}

	public String getLAST_RUN_DATE() {
		return LAST_RUN_DATE;
	}

	public void setLAST_RUN_DATE(String lAST_RUN_DATE) {
		LAST_RUN_DATE = lAST_RUN_DATE;
	}

	public String getBATCH_ID() {
		return BATCH_ID;
	}

	public void setBATCH_ID(String bATCH_ID) {
		BATCH_ID = bATCH_ID;
	}

	public XMLGregorianCalendar getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(XMLGregorianCalendar cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public XMLGregorianCalendar getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}

	public void setLAST_UPDATE_DATE(XMLGregorianCalendar lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}

}
