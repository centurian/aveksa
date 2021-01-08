package com.emc.it.aveska.sap.reco.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.emc.it.aveska.sap.reco.dao.IDSLogger;
import com.emc.it.aveska.sap.reco.domain.IDSMail;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class FileTransferTasklet implements Tasklet {

	String localFilePath;
	String idmFileName;
	String roleFileName;
	String localFileExt;
	
	String ftpType;
	
	String sftphost;
    String username;
    String password;
    String sftpPort;
	
    String sftpfolder;
    
	public  IDSMail	idsIntegration_Mail;
	
	public  static String fileName;
	
	public  static String sftp_success = "**SUCCESS**";
	
	String targetString;
	
	public FileTransferTasklet(IDSMail idsIntegration_Mail) {
		super();
		this.idsIntegration_Mail = idsIntegration_Mail;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,ChunkContext chunkContext) throws Exception 
	{
		/*
		 *  waiting for I/O: 2 second
		 */
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		sftpFile();
		
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// send batch summary by email
		String szMsg = null;
		szMsg = idsIntegration_Mail.formatMailMsgBody(idmFileName);
		idsIntegration_Mail.sendMail(szMsg,  idmFileName );
		
		
		return RepeatStatus.FINISHED;
	}

    private void sftpFile() {
    	
         JSch jsch = null;
         Session session = null;
         Channel channel = null;
         ChannelSftp c = null;
         try {
             jsch = new JSch();
             session = jsch.getSession(username, sftphost, Integer.parseInt(sftpPort));
             
             java.util.Properties config = new java.util.Properties();
             config.put("StrictHostKeyChecking", "no");
             session.setConfig(config);
             
             session.setPassword(password);
             session.connect();

             channel = session.openChannel(ftpType);
             channel.connect();
             c = (ChannelSftp) channel;

        	 IDSLogger.LOGGER.info("Starting File Download:");
        	 
        	 String ftpFilePath = sftpfolder + idmFileName + localFileExt;

             String localFilePathDest = localFilePath;
             

             this.fileName = this.localFilePath;
        	 c.get(ftpFilePath, localFilePathDest);
        	 
        	 //Download the second file

        	 ftpFilePath = sftpfolder + roleFileName + localFileExt;

        	 c.get(ftpFilePath, localFilePathDest);
        	       	 
            
         } catch (Exception e) {	
        	 
        	 IDSLogger.LOGGER.error(ftpType + " failure");
        	 sftp_success = "**FAILURE**";
        	 e.printStackTrace();	
          }
         
         c.disconnect();
         session.disconnect();
    
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

	public String getRoleFileName() {
		return roleFileName;
	}
	
	public void setRoleFileName(String roleFileName) {
		this.roleFileName = roleFileName;
	}

	public String getLocalFileExt() {
		return localFileExt;
	}


	public void setLocalFileExt(String localFileExt) {
		this.localFileExt = localFileExt;
	}

	public String getSftphost() {
		return sftphost;
	}

	public void setSftphost(String sftphost) {
		this.sftphost = sftphost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSftpfolder() {
		return sftpfolder;
	}

	public void setSftpfolder(String sftpfolder) {
		this.sftpfolder = sftpfolder;
	}
	
	public String getSftpPort(){
		return sftpPort;
	}
	
	public void setSftpPort(String sftpPort){
		this.sftpPort = sftpPort;
	}
	
	public String getFtpType() {
		return ftpType;
	}

	public void setFtpType(String ftpType) {
		this.ftpType = ftpType;
	}


}
