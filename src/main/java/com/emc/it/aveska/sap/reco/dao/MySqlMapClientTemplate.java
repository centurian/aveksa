package com.emc.it.aveska.sap.reco.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.emc.it.aveska.sap.reco.domain.IDSCryptoUtil;

/**
 * 
 * @author huoj1
 * the class is designed for decrypting the DB password
 */
public class MySqlMapClientTemplate extends SqlMapClientTemplate implements InitializingBean {
	
	
	public void afterPropertiesSet() {  
		IDSCryptoUtil.updateDataSourcePwd(this);
	}
}
