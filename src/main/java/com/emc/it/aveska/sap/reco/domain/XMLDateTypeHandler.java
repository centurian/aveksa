package com.emc.it.aveska.sap.reco.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class XMLDateTypeHandler extends IDSTypeHandler
{
	public Object valueOf(Date parmDate ) throws DatatypeConfigurationException
	{
		GregorianCalendar objCal =(GregorianCalendar) GregorianCalendar.getInstance();
		if ( parmDate != null)
		{
			objCal.setTime(parmDate);
			return ( DatatypeFactory.newInstance().newXMLGregorianCalendar(objCal));
		}
		else
			return parmDate;
	}	
	
	@Override
	public Object getResult(ResultSet parmRS, String parmColumn) throws SQLException
	{
		Date 	objDate = null;
		Object	objXMLDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try 
		{
			if ( parmRS.getTimestamp(parmColumn) != null )
			{	
				objDate = (Date) formatter.parse(parmRS.getTimestamp(parmColumn).toString());
				objXMLDate = this.valueOf(objDate);
			}
		}
		catch (ParseException ex) 
		{
			ex.printStackTrace();
		}
		catch (DatatypeConfigurationException e) 
		{
			e.printStackTrace();
		}
		return objXMLDate;
	}
	
	@Override
	public void setParameter(PreparedStatement parmStatement, int parmIndex, Object parmValue,String parmJDBCType)  
	{
		if ( parmValue != null )
		{ 
			XMLGregorianCalendar xmlCal = (XMLGregorianCalendar)parmValue;
			try 
			{
				parmStatement.setTimestamp(parmIndex,new java.sql.Timestamp(xmlCal.toGregorianCalendar().getTimeInMillis()));
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}			
		}	
		else
			throw new IllegalArgumentException("Invalid Date Value received. Value = NULL");
	}
}
