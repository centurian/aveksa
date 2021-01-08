package com.emc.it.aveska.sap.reco.domain;


public class IDSRoleMappingDTO {
	
	private String IDENTITY_ID;
	private String NT_ID;
	private String BUSINESS_ROLE;
	private String SAP_TECHNICAL_ROLE;
	private String MODULE_NAME;
	private String TECHNICAL_ROLE;
	
	public String getIDENTITY_ID() {
		return IDENTITY_ID;
	}
	public void setIDENTITY_ID(String identityID) {
		IDENTITY_ID = identityID;
	}
	public String getNT_ID() {
		return NT_ID;
	}
	public void setNT_ID(String ntID) {
		NT_ID = ntID;
	}
	public String getBUSINESS_ROLE() {
		return BUSINESS_ROLE;
	}
	public void setBUSINESS_ROLE(String businessRole) {
		BUSINESS_ROLE = businessRole;
	}
	public String getSAP_TECHNICAL_ROLE() {
		return SAP_TECHNICAL_ROLE;
	}
	public void setSAP_TECHNICAL_ROLE(String sapTechnicalRole) {
		SAP_TECHNICAL_ROLE = sapTechnicalRole;
	}
	public String getMODULE_NAME() {
		return MODULE_NAME;
	}
	public void setMODULE_NAME(String moduleName) {
		MODULE_NAME = moduleName;
	}
	public String getTECHNICAL_ROLE() {
		return TECHNICAL_ROLE;
	}
	public void setTECHNICAL_ROLE(String technicalRole) {
		TECHNICAL_ROLE = technicalRole;
	}
	
}
