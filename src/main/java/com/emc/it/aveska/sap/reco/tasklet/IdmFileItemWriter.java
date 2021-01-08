package com.emc.it.aveska.sap.reco.tasklet;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;


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
import com.emc.it.aveska.sap.reco.domain.IDSIdmDTO;
import com.emc.it.aveska.sap.reco.domain.IDSMail;
import com.emc.it.aveska.sap.reco.dto.IdmFile;


public class IdmFileItemWriter implements ItemWriter<IdmFile>, StepExecutionListener {


	private IDSIdmDTO		idsIdmDTO;
	private IDSDataSourceDAOImpl idDataSourceImpl;
	public static int EVENT_READ_TOTAL = 0;
	public  static int	FAIL_EVENT_NUM = 0;
	public  static int	SUCCESS_EVENT_NUM = 0;
	public  IDSMail	idsIntegration_Mail;
	String localFilePath;
	String idmFileName;
	String localFileExt;
	String fileName;
	public static String targetString;
	
	private StepExecution 	stepExecution;
	final String LAST_RUN_DATE = "lastRunDate";
	
	public  static XMLGregorianCalendar lastRunDateDate = null;
	
	public IdmFileItemWriter(IDSMail idsIntegration_Mail) {
		this.idsIntegration_Mail = idsIntegration_Mail;
	}
	

	@Override
	public void write(List<? extends IdmFile> reconItem) throws Exception {
		int index = 0;
		IDSLogger.LOGGER.info("Writing to IAM_SAP_IDM_DUMP Table");
		
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
			IDSLogger.LOGGER.error("Insert records to table IAM_SAP_IDM_DUMP failure");
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
		
	    
		return stepExecution.getExitStatus();	
	}
	
	private void sendMail() {
		try
		{

			// send batch summary by email
			IDSLogger.LOGGER.info("Sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");
			String szMsg = null;
			szMsg = idsIntegration_Mail.formatMailMsgBodyForIDMtoIDS("IDS_AVESKA_SAP_LOAD - IDM");
			idsIntegration_Mail.sendMail(szMsg,  "IDS_AVESKA_SAP_LOAD - IDM" );
			IDSLogger.LOGGER.info("Finished sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");			
			
		}
		catch(Exception e)
		{
			IDSLogger.LOGGER.error("Error in sending mail status for the inserted records to the table IAM_SAP_IDM_DUMP");
			e.printStackTrace();
		}
	}
	
	public void doInsertInIDS(IdmFile rf) throws ParseException{
		
		Date last_logon_date = null;
		String lastLogonDate = rf.getLastLogonDate();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd/yyyy");
		
		if(lastLogonDate.equals("00/00/0000"))
			this.idsIdmDTO.setLAST_LOGON_DATE(null);
		else
			this.idsIdmDTO.setLAST_LOGON_DATE(convertToXMLGregorianCalendar(rf.getLastLogonDate()));
			
		
		this.idsIdmDTO.setSID(rf.getSID());
		this.idsIdmDTO.setUSER_NAME(rf.getUserName());
		//this.idsIdmDTO.setLAST_LOGON_DATE(convertToXMLGregorianCalendar(rf.getLastLogonDate()));
	
		this.idDataSourceImpl.insertIdmRoleItem(this.idsIdmDTO);
	}
	
	
	
	 private void changeFileName() {
			
	    	String localFileStr = this.localFilePath + this.idmFileName + this.localFileExt;
			
			File localTempFile = new File(localFileStr);
			
			
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
			
			String fileName = this.idmFileName + "_" + 
			           year + 
			           makeZeroFrefix(month) + 
			           makeZeroFrefix(day)+ 
			           makeZeroFrefix(hour) + 
			           makeZeroFrefix(min) + this.localFileExt;
			
			this.fileName = targetString = this.localFilePath + fileName;
					
			File newfile =new File(targetString);
			
			if(localTempFile.renameTo(newfile)){
				IDSLogger.LOGGER.info(newfile+": Rename succesful");
			}else{
				IDSLogger.LOGGER.info(newfile+": Rename failed");
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
	
	
	 public XMLGregorianCalendar convertToXMLGregorianCalendar(String dateTime){
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd-dd hh:mm:ss");
				if(dateTime.equals("now")) {
					java.util.Date created = sdf.parse( new SimpleDateFormat("yyyy-MM-dd hh:mm:sss").format(Calendar.getInstance().getTime()));
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
				IDSLogger.LOGGER.error("method[convertToXMLGregorianCalendar] IDM ModifyTime incorrect format: Expect:yyyy-MM-dd hh:mm:sss" );
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


public String getIdmFileName() {
	return idmFileName;
}


public void setIdmFileName(String idmFileName) {
	this.idmFileName = idmFileName;
}


public String getLocalFileExt() {
	return localFileExt;
}


public void setLocalFileExt(String localFileExt) {
	this.localFileExt = localFileExt;
}


public IDSIdmDTO getIdsIdmDTO() {
	return idsIdmDTO;
}


public void setIdsIdmDTO(IDSIdmDTO idsIdmDTO) {
	this.idsIdmDTO = idsIdmDTO;
}
	

}
