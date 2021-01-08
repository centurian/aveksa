# IAM IDS BATCH.PROPERTIES File
# Batch DB Connectivity Setting for DEV, TEST and PROD
#
#********************************************************************************

#********************************************************************************
# IAM - IDENTITY DATA STORE TEST ENV ( IDS DATABASE )
# IAM TEST ENV
# 
#******************************************************************************** 
ids.jdbc.username=IDDS
ids.jdbc.password=j0CPu9btTE1zJnZApc2/Nw==
ids.jdbc.class=oracle.jdbc.OracleDriver
ids.jdbc.url=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = esporadev01.isus.emc.com)(PORT = 1522))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =MUP1D_IDDS)))

#*******************************************************
# IAM Test ENV Spring Batch DB Settings
#*******************************************************
cache.jdbc.driver=oracle.jdbc.pool.OracleDataSource
cache.jdbc.url=jdbc:oracle:thin:@esporadev01.isus.emc.com:1522:MUP1D
cache.jdbc.user=SPRNGBTCH
cache.jdbc.password=springdev

#ids.jdbc.username=IDDS
#ids.jdbc.password=j0CPu9btTE1zJnZApc2/Nw==
#ids.jdbc.class=oracle.jdbc.pool.OracleDataSource
#ids.jdbc.url=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =esporatst01.isus.emc.com)(PORT = 1522)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =MUP1T_IDDS)))

#*******************************************************
# IAM Test ENV Spring Batch DB Settings
#*******************************************************
#cache.jdbc.driver=oracle.jdbc.pool.OracleDataSource
#cache.jdbc.url=jdbc:oracle:thin:@esporatst01.isus.emc.com:1522:MUP1T
#cache.jdbc.user=SPRNGBTCH
#cache.jdbc.password=f5$67hj


#*******************************************************
# EIS IDS INTERFACE MAIL PROPERTIES
#*******************************************************
ids.mail.hostname=mailhub.lss.emc.com
ids.mail.port=25
ids.mail.from=EIS_IDS@emc.com
ids.mail.to=senthuran.sivagurunathan@emc.com
#,suresh.sivananthan@emc.com,roshan.george@emc.com
ids.mail.subject=DEV Env - IDS batch Summary for 

ids.batch.idm.name=IDM
ids.batch.role.name= User_to_SAP_Role_Mapping
ids.local.dir=C:\\Users\\gadhap\\IDS\\
ids.local.dir.idm=C:\\Users\\gadhap\\IDS\\IDM.csv
ids.local.dir.rolemapping=C:\\Users\\gadhap\\IDS\\User_to_SAP_Role_Mapping.csv
ids.local.file.ext=.csv
