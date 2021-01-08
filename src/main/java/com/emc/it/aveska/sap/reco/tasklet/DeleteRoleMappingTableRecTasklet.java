package com.emc.it.aveska.sap.reco.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


import com.emc.it.aveska.sap.reco.dao.impl.IDSDataSourceDAOImpl;
import com.emc.it.aveska.sap.reco.dao.IDSLogger;


public class DeleteRoleMappingTableRecTasklet implements Tasklet {

	private IDSDataSourceDAOImpl		idsDataSourceDAO;
	


	@Override
	public RepeatStatus execute(StepContribution contribution,ChunkContext chunkContext) throws Exception 
	{
		try
		{
		
			//Delete all the table records
			IDSLogger.LOGGER.info("Starting Deleting records from table IAM_SAP_ROLE_MAPPING");
			int i = this.idsDataSourceDAO.deleteRoleMappingItem();
			IDSLogger.LOGGER.info("No of Deleted records from table IAM_SAP_ROLE_MAPPING: "+i);
		}
		catch(Exception e)
		{
			IDSLogger.LOGGER.error("Delete records from table IAM_SAP_ROLE_MAPPING failure");
			e.printStackTrace();
		}

		return RepeatStatus.FINISHED;
	}
	
	public IDSDataSourceDAOImpl getIdsDataSourceDAO() {
		return idsDataSourceDAO;
	}


	public void setIdsDataSourceDAO(IDSDataSourceDAOImpl idsDataSourceDAO) {
		this.idsDataSourceDAO = idsDataSourceDAO;
	}


}
