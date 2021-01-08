package com.emc.it.aveska.sap.reco.tasklet;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;


import com.emc.it.aveska.sap.reco.dao.IDSLogger;
import com.emc.it.aveska.sap.reco.dao.impl.IDSDataSourceDAOImpl;
import com.emc.it.aveska.sap.reco.domain.IDSRoleMappingDTO;
import com.emc.it.aveska.sap.reco.domain.IDSMail;
import com.emc.it.aveska.sap.reco.dto.RoleMappingFile;;


public class RoleMappingFileItemWriter implements ItemWriter<RoleMappingFile>, StepExecutionListener {


	private IDSRoleMappingDTO		idsRoleMappingDTO;
	private IDSDataSourceDAOImpl idDataSourceImpl;
	public static int EVENT_READ_TOTAL = 0;
	public  static int	FAIL_EVENT_NUM = 0;
	public  static int	SUCCESS_EVENT_NUM = 0;
	public  IDSMail	idsIntegration_Mail;
	String localFilePath;
	String roleMappingFileName;
	String localFileExt;
	String fileName;
	String targetString;
	
	private StepExecution 	stepExecution;
	final String LAST_RUN_DATE = "lastRunDate";
	
	public  static XMLGregorianCalendar lastRunDateDate = null;
	
	public RoleMappingFileItemWriter(IDSMail idsIntegration_Mail) {
		this.idsIntegration_Mail = idsIntegration_Mail;
	}
	

	@Override
	public void write(List<? extends RoleMappingFile> reconItem) throws Exception {
		int index = 0;
		IDSLogger.LOGGER.info("Writing to IAM_SAP_ROLE_MAPPING Table");
		
		try
		{
						
			while(index < reconItem.size()){
				EVENT_READ_TOTAL++;
				doInsertInIDS(reconItem.get(index));
				SUCCESS_EVENT_NUM++;
				index++;
			}
		}
		catch(Exception e)
		{
			IDSLogger.LOGGER.error("Insert records to table IAM_SAP_ROLE_MAPPING failure");
			e.printStackTrace();
			index = reconItem.size();
			FAIL_EVENT_NUM++;
		}		
		
		index = 0;
		
	}
	
	@Override
	public void beforeStep(StepExecution parmStepExecution) 
	{
		stepExecution = parmStepExecution;
	}
	
	
	/**
	 * do 3 things:
	 * 1. update last run date
	 * 2. change file name
	 * 3. send the batch summary 
	 */
	@Override
	public ExitStatus afterStep(StepExecution parmStepExecution) 
	{
		
		IdentityLogger.LOGGER.info("After the step call.");
		
	//	System.out.println(">>>>>>> After Step ");
		ExecutionContext stepContext = parmStepExecution.getExecutionContext();
		
	    lastRunDateDate = (XMLGregorianCalendar )stepContext.get(LAST_RUN_DATE);		
		
		//change file name
		changeFileName();
		// send batch summary by email
		sendMail();
		
		EVENT_READ_TOTAL = 0;
		SUCCESS_EVENT_NUM = 0;
		FAIL_EVENT_NUM = 0;
		lastRunDateDate = null;
		FileTransferTasklet.fileName = null;
		FileTransferTasklet.sftp_success = null;
	    
		return stepExecution.getExitStatus();	
	}
	
	private void sendMail() {
		try
		{

			// send batch summary by email
			IDSLogger.LOGGER.info("Sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");
			String szMsg = null;
			szMsg = idsIntegration_Mail.formatMailMsgBodyForRoleMappingtoIDS("IDS_AVESKA_SAP_LOAD - RoleMapping");
			idsIntegration_Mail.sendMail(szMsg,  "IDS_AVESKA_SAP_LOAD - RoleMapping" );
			IDSLogger.LOGGER.info("Finished sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");			
			
		}
		catch(Exception e)
		{
			IDSLogger.LOGGER.error("Error in sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");
			e.printStackTrace();
		}
	}
	
	 private void changeFileName() {
			
	    	String localFileStr = this.localFilePath + this.roleMappingFileName + this.localFileExt;
			
			File localTempFile = new File(localFileStr);
			
			XMLGregorianCalendar lastRunDateDate = null; //IdItemProcessor.lastRunDateDate;
			
			GregorianCalendar c = new GregorianCalendar();
			try {
				//c.setTime(yourDate);
				lastRunDateDate = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(c);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			int year = lastRunDateDate.getYear();
			int month = lastRunDateDate.getMonth();
			int day = lastRunDateDate.getDay();
			int hour = lastRunDateDate.getHour();
			int min = lastRunDateDate.getMinute();
			
			String fileName = this.roleMappingFileName + "_" + 
			           year + 
			           makeZeroFrefix(month) + 
			           makeZeroFrefix(day)+ 
			           makeZeroFrefix(hour) + 
			           makeZeroFrefix(min) + this.localFileExt;
			
			this.fileName = targetString = this.localFilePath + fileName;
			
			File newfile =new File(targetString);
			
			if(localTempFile.renameTo(newfile)){
				IDSLogger.LOGGER.info("Rename succesful");
			}else{
				IDSLogger.LOGGER.info("Rename failed");
			}
	    }
	 private String makeZeroFrefix(int inputNumber ) {
	    	String result = null;
	    	
	    	if ( inputNumber < 10 ) {
	    		result = "0" + inputNumber;
	    	} else {
	    		result = "" + inputNumber;
	    	}
	    	
	    	return result;
	    }
	
	public void doInsertInIDS(RoleMappingFile rf) throws ParseException{
				
		this.idsRoleMappingDTO.setNT_ID(rf.getNTID());
		this.idsRoleMappingDTO.setIDENTITY_ID(rf.getIdentityID());		
		this.idsRoleMappingDTO.setBUSINESS_ROLE(rf.getBusinessRole());
		this.idsRoleMappingDTO.setSAP_TECHNICAL_ROLE(rf.getSAPTechnicalRole());
		this.idsRoleMappingDTO.setMODULE_NAME(rf.getModuleName());
		this.idsRoleMappingDTO.setTECHNICAL_ROLE(rf.getTechnicalRole());
		this.idsRoleMappingDTO.setSAP_TECHNICAL_ROLE(rf.getSAPTechnicalRole());
		this.idsRoleMappingDTO.setSAP_TECHNICAL_ROLE(rf.getSAPTechnicalRole());
	
		this.idDataSourceImpl.insertRoleMappingItem(this.idsRoleMappingDTO);
	}
	
	public XMLGregorianCalendar convertToXMLGregorianCalendar(String dateTime){
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(dateTime.equals("now")) {
				java.util.Date created = sdf.parse( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
				GregorianCalendar gregoryCreated = new GregorianCalendar();
				gregoryCreated.setTime(created);
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregoryCreated);
			}else{
				java.util.Date created = sdf.parse( dateTime );
				GregorianCalendar gregoryCreated = new GregorianCalendar();
				gregoryCreated.setTime(created);
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregoryCreated);
			}
			
		} catch (ParseException e) {
			IDSLogger.LOGGER.error("method[convertToXMLGregorianCalendar] IDM ModifyTime incorrect format: Expect:yyyy-MM-dd hh:mm:ss" );
		} catch (DatatypeConfigurationException e) {
			IDSLogger.LOGGER.error("method[convertToXMLGregorianCalendar]" + e.getMessage());
		}
		
		return null; 
	}




public IDSDataSourceDAOImpl getIdsDataSourceDAO() {
	return idDataSourceImpl;
}


public void setIdsDataSourceDAO(IDSDataSourceDAOImpl idDataSourceImpl) {
	this.idDataSourceImpl = idDataSourceImpl;
}


public String getLocalFilePath() {
	return localFilePath;
}


public void setLocalFilePath(String localFilePath) {
	this.localFilePath = localFilePath;
}


public String getRoleMappingFileName() {
	return roleMappingFileName;
}


public void setRoleMappingFileName(String roleMappingFileName) {
	this.roleMappingFileName = roleMappingFileName;
}


public String getLocalFileExt() {
	return localFileExt;
}


public void setLocalFileExt(String localFileExt) {
	this.localFileExt = localFileExt;
}


public IDSRoleMappingDTO getIdsRoleMappingDTO() {
	return idsRoleMappingDTO;
}


public void setIdsRoleMappingDTO(IDSRoleMappingDTO idsRoleMappingDTO) {
	this.idsRoleMappingDTO = idsRoleMappingDTO;
}
	

}
