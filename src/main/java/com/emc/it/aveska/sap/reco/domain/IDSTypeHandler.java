package com.emc.it.aveska.sap.reco.domain;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ibatis.sqlmap.engine.type.BaseTypeHandler;
import com.ibatis.sqlmap.engine.type.TypeHandler;

public abstract class IDSTypeHandler extends BaseTypeHandler implements	TypeHandler 
{
	public boolean equals(Object arg0, String arg1) 
	{
		return false;
	}

	public Object getResult(ResultSet arg0, String arg1) throws SQLException 
	{
		return null;
	}

	public Object getResult(ResultSet arg0, int arg1) throws SQLException 
	{
		return null;
	}

	public Object getResult(CallableStatement arg0, int arg1) throws SQLException 
	{
		return null;
	}

	public void setParameter(PreparedStatement parmStatement, int parmIndex, Object parmValue,String parmJDBCType) throws SQLException 
	{
		if ( parmValue != null )
			parmStatement.setString(parmIndex, parmValue.toString());
		else
			parmStatement.setString(parmIndex, "");
	}

	public Object valueOf(String arg0) 
	{
		return null;
	}
}
