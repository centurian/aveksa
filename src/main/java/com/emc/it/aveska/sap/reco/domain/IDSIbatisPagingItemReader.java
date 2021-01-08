package com.emc.it.aveska.sap.reco.domain;

import java.sql.SQLException;

import org.springframework.batch.item.database.IbatisPagingItemReader;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;

public class IDSIbatisPagingItemReader extends IbatisPagingItemReader<Object>
{
	private SqlMapClientTemplate	      sqlMapClientTemplate;
	
	public IDSIbatisPagingItemReader()
	{
		super();
	}	
	
	public SqlMapClientTemplate getSqlMapClientTemplate() 
	{
		return sqlMapClientTemplate;
	}

	//*********************************************************************************************
	// Decrypt Properties File Password and Update DataSource
	//*********************************************************************************************
	public void setSqlMapClientTemplate(SqlMapClientTemplate parmSqlMapClientTemplateIDS) 
	{
		// Local Member SqlMapClientTemplate Var
		this.sqlMapClientTemplate = parmSqlMapClientTemplateIDS;

		IDSCryptoUtil.updateDataSourcePwd(this.sqlMapClientTemplate);
	}

	@Override
	public void setSqlMapClient(SqlMapClient parmSQLMapClient) 
	{
		try 
		{
			parmSQLMapClient.setUserConnection(this.sqlMapClientTemplate.getDataSource().getConnection());
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		super.setSqlMapClient(parmSQLMapClient);
	}
	
}
