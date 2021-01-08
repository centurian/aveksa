package com.emc.it.aveska.sap.reco.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


//import java.crypto.CryptoException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.emc.it.aveska.sap.reco.dao.IDSLogger;

public class IDSCryptoUtil 
{
	    private static byte[] AES_KEY = {
	    	0x50, 0x26, 0x73, 0x7A, 0x2B, 0x21, 0x62, 0x54, 0x34, 0x24, 0x25, 0x5E, 0x37, 0x78, 0x73, 0x39
	    };
	    
	    //private static  String  		KEY = "P&sz+!bT4$%^7xs9";
	    private static  Cipher  		CIPHER = null;
	    private static  SecretKeySpec 	KEYSPEC = new SecretKeySpec(AES_KEY, "AES");
	    
	    
	    static
	    {
	    	try 
	    	{
				CIPHER = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			    
			} 
	    	catch (Exception ex) 
	    	{
	    		IDSLogger.LOGGER.error("Error in IDSCryptoUtil static init creating Ciper instance");
	    	}
	    }	

	    public static String encryptText(String parmUnEncryptedText)
	    {
	        try
	        {
	            CIPHER.init(Cipher.ENCRYPT_MODE, KEYSPEC);
	            byte[] encryptedBuffer= CIPHER.doFinal(parmUnEncryptedText.getBytes());
	            return new sun.misc.BASE64Encoder().encode(encryptedBuffer);
	        }
	        catch (Exception ex)
	        {
	        	IDSLogger.LOGGER.error("Error in IDSCryptoUtil:encryptText. Err Msg = " + ex.getMessage());
	        	//System.out.println("Error in IDSCryptoUtil:encryptText. Err Msg = " + ex.getMessage());
	        }
	        return null;

	    }

	    @SuppressWarnings("restriction")
		public static String decryptText(String parmEncryptedText)
	    {
	        try
	        {
	        	byte[] inputBuffer = new sun.misc.BASE64Decoder().decodeBuffer(parmEncryptedText);
	            CIPHER.init(Cipher.DECRYPT_MODE, KEYSPEC);
	            byte[] decryptedBuffer= CIPHER.doFinal(inputBuffer);
	            return new String(decryptedBuffer);
	        }
	        catch (Exception ex)
	        {
	        	IDSLogger.LOGGER.error("Error in IDSCryptoUtil:decryptText. Err Msg = " + ex.getMessage());
	        	//System.out.println("Error in IDSCryptoUtil:decryptText. Err Msg = " + ex.getMessage());
	        }
	        return null;
	    }

	    public static void updateDataSourcePwd(SqlMapClientTemplate parmSqlMapClientTemplateIDS) 
		{
			BasicDataSource    dataSource=null;
			String szPwd=null;
			
			dataSource = (BasicDataSource)parmSqlMapClientTemplateIDS.getDataSource();
			
			if ( dataSource.getPassword().length() < 20 )
				return;
			
			szPwd = IDSCryptoUtil.decryptText(dataSource.getPassword());
			
			if ( szPwd != null )
				dataSource.setPassword(szPwd);
			else
			{	
				//System.out.println("Error in IDSCryptoUtil:decryptText. Null value returned. Cfg Parm Value = " + dataSource.getPassword());
				IDSLogger.LOGGER.error("Error in IDSCryptoUtil:decryptText. Null value returned. Cfg Parm Value = " + dataSource.getPassword());
				throw new IllegalArgumentException("Error in IDSCryptoUtil:decryptText. Null value returned.");
			}	
			// Set the updated DataSource with decrypted Pwd
			parmSqlMapClientTemplateIDS.setDataSource(dataSource);
		}
	    
	    //######################################################################################################
	    // for file encryption and decryption --Jack
	    
	    private static final String ALGORITHM = "AES";
	    private static final String TRANSFORMATION = "AES";

	    public static void encrypt( File inputFile, File outputFile) throws CryptoException {
	    	doCrypto(Cipher.ENCRYPT_MODE, new String(AES_KEY), inputFile, outputFile);
	    }

	    public static void decrypt(File inputFile, File outputFile) throws CryptoException {
	    	doCrypto(Cipher.DECRYPT_MODE, new String(AES_KEY), inputFile, outputFile);
	    }

	    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws CryptoException {

	    	try {
	    		Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

	    		Cipher cipher = null;

	    		try {
	    			cipher = Cipher.getInstance(TRANSFORMATION);
	    			cipher.init(cipherMode, secretKey);
	    		} catch (InvalidKeyException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (NoSuchAlgorithmException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (NoSuchPaddingException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	    		FileInputStream inputStream = new FileInputStream(inputFile);
	    		byte[] inputBytes = new byte[(int) inputFile.length()];
	    		inputStream.read(inputBytes);

	    		byte[] outputBytes = null;
	    		try {
	    			outputBytes = cipher.doFinal(inputBytes);
	    		} catch (IllegalBlockSizeException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (BadPaddingException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	    		FileOutputStream outputStream = new FileOutputStream(outputFile);
	    		outputStream.write(outputBytes);

	    		inputStream.close();
	    		outputStream.close();

	    	} catch ( IOException ex) {
	    		throw new CryptoException("Error encrypting/decrypting file", ex);
	    	}
	    }

}
