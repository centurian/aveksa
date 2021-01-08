package com.emc.it.aveska.sap.reco.dao.impl;

import com.emc.it.aveska.sap.reco.dao.IDSDataSourceDAO;
import com.emc.it.aveska.sap.reco.dao.IDSLogger;
import com.emc.it.aveska.sap.reco.dao.MySqlMapClientTemplate;

import com.emc.it.aveska.sap.reco.domain.IDSIdmDTO;
import com.emc.it.aveska.sap.reco.domain.IDSRoleMappingDTO;

import com.emc.it.aveska.sap.reco.tasklet.IdmFileItemWriter;
import com.emc.it.aveska.sap.reco.tasklet.RoleMappingFileItemWriter;

public class IDSDataSourceDAOImpl implements IDSDataSourceDAO 
{
	 MySqlMapClientTemplate mySqlMapClientTemplate;
	
	public IDSDataSourceDAOImpl(){}
	public IDSDataSourceDAOImpl(MySqlMapClientTemplate mySqlMapClientTemplate) {
			super();
			this.mySqlMapClientTemplate = mySqlMapClientTemplate;
	}

	@Override
	public void insertIdmRoleItem(IDSIdmDTO idsidm){
		try{
			mySqlMapClientTemplate.insert(IDSDataSourceDAO.INSERT_IDM_ROLE_ITEM,idsidm);
		}catch(Exception e){
			IDSLogger.LOGGER.error("Insert records to table IAM_SAP_IDM_DUMP failure");
			e.printStackTrace();
			IdmFileItemWriter.FAIL_EVENT_NUM++;
			
		}
		
	}
	
	@Override
	public int deleteIdmRoleItem(){
		int i = 0;
		try{
			i = mySqlMapClientTemplate.delete(IDSDataSourceDAO.DELETE_IDM_ROLE_ITEM);			
		}catch(Exception e){
			IDSLogger.LOGGER.error("Delete records to table IAM_SAP_IDM_DUMP failure");
			e.printStackTrace();
			
		}
		return i;
		
	}
	
	@Override
	public void insertRoleMappingItem(IDSRoleMappingDTO idsrolemapping){
		try{			
			mySqlMapClientTemplate.insert(IDSDataSourceDAO.INSERT_ROLE_MAPPING_ITEM,idsrolemapping);
		}catch(Exception e){
			IDSLogger.LOGGER.error("Insert records to table IAM_SAP_ROLE_MAPPING failure");
			e.printStackTrace();
			RoleMappingFileItemWriter.FAIL_EVENT_NUM++;
		}
		
	}
	
	@Override
	public int deleteRoleMappingItem(){
		int i = 0;
		try{
			i = mySqlMapClientTemplate.delete(IDSDataSourceDAO.DELETE_ROLE_MAPPING_ITEM);			
		}catch(Exception e){
			IDSLogger.LOGGER.error("Delete records to table IAM_SAP_ROLE_MAPPING failure");
			e.printStackTrace();
			
		}
		return i;
		
	}	
			
}