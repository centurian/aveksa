package com.emc.it.aveska.sap.reco.domain;

public class ParamsInput {

	String NT_ID;
	String IDENTITY_ID;
	String ROLE_NAME;
	
	String INTERFACE_NAME;
	String LAST_RUN_DATE;
	String BATCH_ID;
	String NEXT_BATCH_ID;
	String CREATE_DATE;
	String LAST_UPDATE_DATE;
	
	public ParamsInput() {
		// TODO Auto-generated constructor stub
	}

	public String getNTID() {
		return NT_ID;
	}

	public void setNTID(String nTID) {
		NT_ID = nTID;
	}

	public String getIDENTITY_ID() {
		return IDENTITY_ID;
	}

	public void setIDENTITY_ID(String iDENTITY_ID) {
		IDENTITY_ID = iDENTITY_ID;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
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

	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public String getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}

	public void setLAST_UPDATE_DATE(String lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}

	public String getNEXT_BATCH_ID() {
		return NEXT_BATCH_ID;
	}

	public void setNEXT_BATCH_ID(String nEXT_BATCH_ID) {
		NEXT_BATCH_ID = nEXT_BATCH_ID;
	}

}
