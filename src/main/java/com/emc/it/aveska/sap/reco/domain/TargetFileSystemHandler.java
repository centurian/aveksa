package com.emc.it.aveska.sap.reco.domain;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TargetFileSystemHandler  
{
	private String 			fileName;
	private String 			pathName;
	private File			targetFile;
	private FileWriter		fileWriter;

	//********************************
	// Gets and Sets
	//********************************
	public void setPathName(String parmPathName) 
	{	pathName = parmPathName;	}

	public String getPathName() 
	{	return pathName;	}

	public String getFileName() 
	{	return fileName;	}

	public void setFileName(String parmFileName) 
	{	fileName = parmFileName;	}

	public File getTargetFile() 
	{	return targetFile;	}

	public FileWriter getFileWriter() 
	{	return fileWriter;		}

	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	//********************************
	// Methods
	//********************************
	public void createTargetFile(boolean parmBoolDateStamp) throws IOException
	{
		this.createDirectoryPath();
		this.createFileName(parmBoolDateStamp);
		this.createFileWriter();
	}
	
	public void closeTargetFile () {
		if (this.fileWriter != null ) {
			try {
				this.fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private  String generateFileName(boolean parmBoolDateStamp) 
	{
		StringBuilder szBuffer = null;
		SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyy-MM-dd");
		if ( parmBoolDateStamp )
		{	
			szBuffer = new StringBuilder(this.fileName + "_");
			szBuffer.append(dtFormatter.format(new Date()) + ".txt");
		}
		else
		{
			szBuffer = new StringBuilder(this.fileName + ".txt");
		}	
		return szBuffer.toString();
	}	

	
	private void createDirectoryPath()
	{
		File  pathHandler = new File(this.pathName);
					
		if ( (pathHandler != null) && (! pathHandler.exists()) )
			pathHandler.mkdir();
	}	
	
	private void createFileName(boolean parmBoolDateStamp) throws IOException
	{
		String szOutputFileName = this.generateFileName(parmBoolDateStamp);
		
		this.targetFile = new File(this.pathName ,szOutputFileName);
	
		if ( this.targetFile.exists())
			this.targetFile.delete();
	
		this.targetFile.createNewFile();
	}
	
	private void createFileWriter() throws IOException
	{
		this.setFileWriter(new FileWriter(this.targetFile));
	}

}
