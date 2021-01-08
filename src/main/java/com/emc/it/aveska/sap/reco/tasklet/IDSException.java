package com.emc.it.aveska.sap.reco.tasklet;

import java.util.ArrayList;
import java.util.List;

import com.emc.it.aveska.sap.reco.dao.IDSLogger;

public class IDSException   
{
	public static List<IDSException>	IDSExceptionList = new ArrayList<IDSException>();;
	private String						_szPersonID;
	private String						_szLastName;
	private String						_szFirstName;
	private String						_szIDType;
	private Exception 					_exception = null;
		
	//*******************************************************
	//*   Constructor 
	//*******************************************************
	public IDSException(Exception parmEx)
	{
		this._exception = parmEx;
		set_szPersonID(new String());
		set_szFirstName(new String());
		set_szLastName(new String());
		set_szIDType(new String());
	}
	
	//*******************************************************
	//*   Gets and Sets 
	//*******************************************************
	public void set_exception(Exception _exception) 
	{	this._exception = _exception;	}

	public Exception get_exception() 
	{		return _exception; 	 }
	
	public void set_szPersonID(String _szPersonID) 
	{		this._szPersonID = _szPersonID; 	}

	public String get_szPersonID() 
	{		return _szPersonID; 	}

	public void set_szLastName(String _szLastName) 
	{		this._szLastName = _szLastName; 	}

	public String get_szLastName() 
	{		return _szLastName; 	}

	public void set_szFirstName(String _szFirstName) 
	{		this._szFirstName = _szFirstName;	}

	public String get_szFirstName() 
	{		return _szFirstName;	}

	public void set_szIDType(String _szIDType) 
	{		this._szIDType = _szIDType; 	}

	public String get_szIDType() 
	{		return _szIDType;	}
	
	//********************************************************
	public void logError(String parmErrMsg )
	{
		if ( this._exception != null )
		{	
			if ( this.get_szPersonID().isEmpty() == false)
			{	
				IDSLogger.LOGGER.error("PersonID =  " + this.get_szPersonID());
				IDSLogger.LOGGER.error("LastName =  " + this.get_szLastName());
				IDSLogger.LOGGER.error("FirstName =  " + this.get_szFirstName());
				IDSLogger.LOGGER.error("Identity Type =  " + this.get_szIDType());
			}	

			if ( _exception.getStackTrace()[0] != null )
			{	
				IDSLogger.LOGGER.error("Error Description = " + parmErrMsg);
				IDSLogger.LOGGER.error("Error Msg = " + _exception.getMessage());
				IDSLogger.LOGGER.error("Exception Cause =  " + _exception.getCause());
				IDSLogger.LOGGER.error("Class Name =  " + _exception.getStackTrace()[0].getClassName());
				IDSLogger.LOGGER.error("File Name =  " + _exception.getStackTrace()[0].getFileName());
				IDSLogger.LOGGER.error("Method Name =  " + _exception.getStackTrace()[0].getMethodName());
				IDSLogger.LOGGER.error("Src Line Number =  " + _exception.getStackTrace()[0].getLineNumber());
			}
			IDSLogger.LOGGER.error("");        	
			IDSLogger.LOGGER.error("Stack Trace :");
			
			for(StackTraceElement objElement : _exception.getStackTrace())
			{
				IDSLogger.LOGGER.error(objElement.toString());
			}
			IDSExceptionList.add(this);
		}
	}	

	public void logError()
	{
		if ( this._exception != null )
		{	
			if ( this.get_szPersonID().isEmpty() == false)
			{	
				IDSLogger.LOGGER.error("PersonID =  " + this.get_szPersonID());
				IDSLogger.LOGGER.error("LastName =  " + this.get_szLastName());
				IDSLogger.LOGGER.error("FirstName =  " + this.get_szFirstName());
				IDSLogger.LOGGER.error("Identity Type =  " + this.get_szIDType());
			}	
			if ( _exception.getStackTrace()[0] != null )
			{	
				IDSLogger.LOGGER.error("Error Msg = " + _exception.getMessage());
				IDSLogger.LOGGER.error("Exception Cause =  " + _exception.getCause());
				IDSLogger.LOGGER.error("Class Name =  " + _exception.getStackTrace()[0].getClassName());
				IDSLogger.LOGGER.error("File Name =  " + _exception.getStackTrace()[0].getFileName());
				IDSLogger.LOGGER.error("Method Name =  " + _exception.getStackTrace()[0].getMethodName());
				IDSLogger.LOGGER.error("Src Line Number =  " + _exception.getStackTrace()[0].getLineNumber());
			}
			IDSLogger.LOGGER.error("");        	
			IDSLogger.LOGGER.error("Stack Trace :");
			
			for(StackTraceElement objElement : _exception.getStackTrace())
			{
				IDSLogger.LOGGER.error(objElement.toString());
			}
			IDSExceptionList.add(this);
		}
	}

	}
