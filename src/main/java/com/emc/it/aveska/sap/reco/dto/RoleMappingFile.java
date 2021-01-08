package com.emc.it.aveska.sap.reco.dto;


public class RoleMappingFile {
	private String IdentityID;
	private String NTID;
	private String BusinessRole;
	private String SAPTechnicalRole;
	private String ModuleName;
	private String TechnicalRole;
	private String LastLogonDate;
	
	public String getIdentityID() {
		return IdentityID;
	}
	public void setIdentityID(String identityID) {
		IdentityID = identityID;
	}
	public String getNTID() {
		return NTID;
	}
	public void setNTID(String ntID) {
		NTID = ntID;
	}
	public String getBusinessRole() {
		return BusinessRole;
	}
	public void setBusinessRole(String businessRole) {
		BusinessRole = businessRole;
	}
	public String getSAPTechnicalRole() {
		return SAPTechnicalRole;
	}
	public void setSAPTechnicalRole(String sapTechnicalRole) {
		SAPTechnicalRole = sapTechnicalRole;
	}
	public String getModuleName() {
		return ModuleName;
	}
	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}
	public String getTechnicalRole() {
		return TechnicalRole;
	}
	public void setTechnicalRole(String technicalRole) {
		TechnicalRole = technicalRole;
	}	
	public String getLastLogonDate() {
		return LastLogonDate;
	}
	public void setLastLogonDate(String lastLogonDate) {
		LastLogonDate = lastLogonDate;
	}	
	
	

}
