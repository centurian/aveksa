package com.emc.it.aveska.sap.reco.domain;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import com.emc.it.aveska.sap.reco.dao.IDSLogger;
import com.emc.it.aveska.sap.reco.tasklet.FileTransferTasklet;
import com.emc.it.aveska.sap.reco.tasklet.IdmFileItemWriter;
import com.emc.it.aveska.sap.reco.tasklet.RoleMappingFileItemWriter;


public class IDSMail extends JavaMailSenderImpl  
{
	private SimpleMailMessage		_MailMsg;

	public void set_MailMsg(SimpleMailMessage parmMailMsg) 
	{
		this._MailMsg = parmMailMsg;
	}

	public SimpleMailMessage get_MailMsg() 
	{
		return _MailMsg;
	}
	
	public void sendMail(String parmBody, String parmAppName )
	{
		IDSLogger.LOGGER.info("Start - Send Mail at " + new Date().toString());
		IDSLogger.LOGGER.info("Mail Text = " + parmBody);
		
		if ( parmBody == null || parmBody.isEmpty() )
			return;
		
		try
		{
			Date dtNow = new Date();
		
			// Append the current Datetime to the Subject text
			
			String originalSubj = _MailMsg.getSubject();
			
			StringBuilder szBuffer = new StringBuilder(_MailMsg.getSubject());
			szBuffer.append(parmAppName); 
			szBuffer.append( " as of " + DateFormat.getDateTimeInstance().format(dtNow));		
			_MailMsg.setSubject(szBuffer.toString());
			_MailMsg.setSentDate(new Date());
			_MailMsg.setText(parmBody);
			this.send(_MailMsg);
			
			_MailMsg.setSubject(originalSubj);
		}
		catch(Exception ex)
		{
			IDSLogger.LOGGER.error("Error in IDSMail:SendMail");
    	}
		IDSLogger.LOGGER.info("End - Send Mail at " + new Date().toString());
	}
	
	public String formatMailMsgBodyForIDMtoIDS(String parmAppName){
		
		IDSLogger.LOGGER.info("Start - formatMailMsgBody at " + new Date().toString());
		
		
		String summary = null;
		
		summary = "****Summary of IDS Interface Processing from " +  parmAppName + "****\n\n";
		summary = summary + "***File Upload Status = " + FileTransferTasklet.sftp_success + "****\n\n";
		summary = summary + "***Total # IDS records extracted = " + IdmFileItemWriter.EVENT_READ_TOTAL + "****\n\n";
		summary = summary + "***Total # IDS records inserted = " + IdmFileItemWriter.SUCCESS_EVENT_NUM + "****\n\n";
		summary = summary + "***Total # IDS records failed = " +   IdmFileItemWriter.FAIL_EVENT_NUM + "****\n\n";		
		summary = summary + "***New Last Run Date saved = " + IdmFileItemWriter.lastRunDateDate + "****\n\n";
		summary = summary + "***New file created = " + FileTransferTasklet.fileName + "****\n\n";		
		
		IDSLogger.LOGGER.info("End - formatMailMsgBody at " + new Date().toString());
		return summary;
		
	}
	
	public String formatMailMsgBodyForRoleMappingtoIDS(String parmAppName){
		
		IDSLogger.LOGGER.info("Start - formatMailMsgBody at " + new Date().toString());
		
		
		String summary = null;
		
		summary = "****Summary of IDS Interface Processing from " +  parmAppName + "****\n\n";
		summary = summary + "***File Upload Status = " + FileTransferTasklet.sftp_success + "****\n\n";
		summary = summary + "***Total # IDS records extracted = " + RoleMappingFileItemWriter.EVENT_READ_TOTAL + "****\n\n";
		summary = summary + "***Total # IDS records inserted = " + RoleMappingFileItemWriter.SUCCESS_EVENT_NUM + "****\n\n";
		summary = summary + "***Total # IDS records failed = " +   RoleMappingFileItemWriter.FAIL_EVENT_NUM + "****\n\n";		
		summary = summary + "***New Last Run Date saved = " + RoleMappingFileItemWriter.lastRunDateDate + "****\n\n";
		summary = summary + "***New file created = " + FileTransferTasklet.fileName + "****\n\n";		
		
		IDSLogger.LOGGER.info("End - formatMailMsgBody at " + new Date().toString());
		return summary;
		
	}
	public String formatMailMsgBody(String parmAppName)
	{
		IDSLogger.LOGGER.info("Start - formatMailMsgBody at " + new Date().toString());
		
		String summary = null;
		
		summary = "****Summary of IDS Interface Processing from " +  parmAppName + "\n";
		//summary = summary + "***Total # IDS records skipped = " +   CustomItemProcessor.SKIP_EVENT_NUM + "\n";
		summary = summary + "***New file created = " + FileTransferTasklet.fileName + "\n";
		summary = summary + "***Sftp Download = " + FileTransferTasklet.sftp_success + "\n";

		
		IDSLogger.LOGGER.info("End - formatMailMsgBody at " + new Date().toString());
	//	 return szBuffer.toString();
		return summary;
		 
	 }
}
