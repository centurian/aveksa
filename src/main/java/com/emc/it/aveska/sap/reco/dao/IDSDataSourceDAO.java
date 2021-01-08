package com.emc.it.aveska.sap.reco.dao;

import com.emc.it.aveska.sap.reco.domain.IDSIdmDTO;
import com.emc.it.aveska.sap.reco.domain.IDSRoleMappingDTO;



public interface IDSDataSourceDAO 
{

    public static final String INSERT_IDM_ROLE_ITEM			= "insertIDMRecordToIDS";
    public static final String DELETE_IDM_ROLE_ITEM			= "deleteIDMRecordToIDS";
    public static final String INSERT_ROLE_MAPPING_ITEM			= "insertRoleMappingRecordToIDS";
    public static final String DELETE_ROLE_MAPPING_ITEM			= "deleteRoleMappingRecordToIDS";
    
    // insertIDMRecordToIDS
    public void insertIdmRoleItem(IDSIdmDTO idsRoleActionDto);
    
    // deleteIDMRecordToIDS
    public int deleteIdmRoleItem();
    
    // insertRoleMappingRecordToIDS
    public void insertRoleMappingItem(IDSRoleMappingDTO idsRoleMappingActionDto);
    
    // deleteIDMRecordToIDS
    public int deleteRoleMappingItem();
    
}
