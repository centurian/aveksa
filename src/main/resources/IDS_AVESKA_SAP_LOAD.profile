#********************************************************************************
# IAM - IDENTITY DATA STORE TEST ENV ( IDS DATABASE )
# IAM TEST ENV
# 
#******************************************************************************** 
#ids.jdbc.username=acrstg
#ids.jdbc.password=HtDZY7fR6EQuLflNIEJtvw==
#ids.jdbc.class=oracle.jdbc.pool.OracleDataSource
#ids.jdbc.url=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =esporatst01.isus.emc.com)(PORT = 1522)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =MUP1T)))

ids.jdbc.username=IGL_APP_USER
ids.jdbc.password=S7yJhbUDxcEFmaj8yyPOKA==
ids.jdbc.class=oracle.jdbc.pool.OracleDataSource
ids.jdbc.url=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =igloratst01.us.dell.com)(PORT = 1521)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =arct.tst.amer.dell.com)))

#*******************************************************
# IAM Test ENV Spring Batch DB Settings
#*******************************************************
cache.jdbc.driver=oracle.jdbc.pool.OracleDataSource
cache.jdbc.url=jdbc:oracle:thin:@esporatst01.isus.emc.com:1522:MUP1T
cache.jdbc.user=SPRNGBTCH
cache.jdbc.password=f5$67hj

#*******************************************************
# EIS IDS INTERFACE MAIL PROPERTIES
#*******************************************************
ids.mail.hostname=mailhub.lss.emc.com
ids.mail.port=25
ids.mail.from=EIS_IDS@emc.com
#ids.mail.to=ward_sean@emc.com,Muthyala_Ramakanth@emc.com,EIS_Managed_Support@emc.com,EBC_Support@emc.com
ids.mail.to=santosh.gottapu@emc.com
ids.mail.subject=Hosted TEST Env - IDS batch Summary for 


#*******************************************************
# IAM IDS SFTP DEV FOR IDS_SUB_INT_SAP_RECO
#*******************************************************
ids.ftp.type=sftp
ids.sftp.hostname=sftp.emc.com
ids.sftp.port=22
ids.sftp.user=sapidmreconn
ids.sftp.password=0MB0sMIHG
ids.sftp.remote.dir=/test/


ids.batch.idm.name=IDM
ids.batch.role.name= User_to_SAP_Role_Mapping_2016-01-06
ids.local.dir=/opt/iam/archive/IDS_SUB_INT_SAP_RECO/
ids.local.dir.test=/opt/iam/archive/IDS_SUB_INT_SAP_RECO/IDS_SUB_INT_SAP_RECO.csv
ids.local.file.ext=.csv



driverClassName=net.sourceforge.jtds.jdbc.Driver
msSqlUrl=jdbc:jtds:sqlserver://DBADEV30SQL2.corp.emc.com:62370;databaseName=ALMCourionTST
dbUsername=IDSAppUser
dbPassword=1Ds@99User

#****************************************************************
# UNITY CALL SET UP
#****************************************************************
iam.ws.url=http://soagtwtst.isus.emc.com:4400/sst/runtime.asvc/com.actional.intermediary.UnITyRequestManagement

iam.ws.user=unity_courion_acc_portal_user
iam.ws.pass=unity_courion_acc_portal_user

iam.unity.configurationItem=SAP PROD ACCESS REQUEST
iam.unity.description=IDS action attempted was:%Action% <br/>Role requested: %RequestSummary_Add%<br/>Action: %Action%<br/>Action requested on: %ActionRequested% <br/>Request Id: %RequestID% <br/>Desc: AccountPortal was not able to process the request %RequestID% The connector for SAP has reported an error: %LastError% <br/>Employee name: %FirstNameLastName% <br/>Employee NT LoginID: %ADUsername%
iam.unity.openedByUserId=APP.AccountPortal
iam.unity.priority=3
iam.unity.shortDescription=SAP IDM reconciliation to IDS/AP 
#${ADUsername} 
iam.unity.requestedForUserId=








