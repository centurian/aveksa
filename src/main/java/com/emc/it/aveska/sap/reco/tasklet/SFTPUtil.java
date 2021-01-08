package com.emc.it.aveska.sap.reco.tasklet;

import java.io.File;



import com.emc.it.aveska.sap.reco.dao.IDSLogger;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPUtil {
	
	String sftphost;
    String username;
    String password;
    String sftpfolder;
      
    final static String FTP_TYPE = "sftp";
    
    Session session = null;
    Channel channel = null;
    ChannelSftp c = null;
  
    
    //@SuppressWarnings("null")
	public boolean uploadFile(String filePath, String rawFileName, String  fileExt, String encryptName) {
    	
         
         try {
        	 
        	 initialFtp ();
             c = (ChannelSftp) channel;
             
             //System.out.println("Starting File Upload:");
        	 IDSLogger.LOGGER.info("Starting File Upload:");
             
        	 String encrptedFileName = rawFileName + encryptName;
             String fsrc = filePath + encrptedFileName;
             
          // check if the batch result file is existed or not
     		File f = new File(fsrc);
     		if ( ! f.exists()) {
     			// log error
     			IDSLogger.LOGGER.error("The file is not existed in " + fsrc);
     			return false;
     		}
           
            String fdest = sftpfolder + encrptedFileName;  //  sftp site file name is always InterfaceName.csv
             
            // IdentityLogger.LOGGER.info("SFTP File: " + fdest);
             c.put(fsrc, fdest);
          
          //   c.get(fdest, "/tmp/testfile.bin");
           //  System.out.println("done.");
             IDSLogger.LOGGER.info("sftp succesful");
         
         } catch (Exception e) {	
        	 
        	 IDSLogger.LOGGER.error("sftp failure");
 //       	 sftp_success = "**FAILURE**";
        	 e.printStackTrace();	
        	 
        	 return false;
          }
         
         c.disconnect();
         session.disconnect();
         
         return true;
    	
    }
    
	private void initialFtp () throws Exception {

		JSch jsch = new JSch();
		session = jsch.getSession(username, sftphost, 22);

		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.setPassword(password);
		session.connect();

		channel = session.openChannel(FTP_TYPE);
		channel.connect();
	}
    
    @SuppressWarnings("null")
	public void downloadFile(String filePath, String rawFileName, String  fileExt, String encryptName){
    	  
         String ftpFilePath = sftpfolder + rawFileName + encryptName + fileExt;
         String localFilePath = filePath + rawFileName + "_fromFtp" + fileExt;
         
         try {
        	 initialFtp ();
             c = (ChannelSftp) channel;
              
        	// System.out.println("@@@@@@@@@@@@ start to get ftp file");
        	 
        	 c.get(ftpFilePath, localFilePath);
        	 
        	 
        	// System.out.println("@@@@@@@@@@@@ftp gets file done");
        	
          
         } catch (Exception e) {	
        	 
 //       	 IDSLogger.LOGGER.error("sftp failure");
        	// sftp_success = "**FAILURE**";
        	 e.printStackTrace();	
          }
         
         c.disconnect();
         session.disconnect();
    	
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

	
}
