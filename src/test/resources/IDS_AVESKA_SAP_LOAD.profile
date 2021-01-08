ids.jdbc.username=acrstg
#ids.jdbc.password=HtDZY7fR6EQuLflNIEJtvw==
ids.jdbc.password=JyPmrlkxlyVWyLdFKAFdqg==
ids.jdbc.class=oracle.jdbc.pool.OracleDataSource
ids.jdbc.url=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST =esporadev01.isus.emc.com)(PORT = 1522)) (CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =MUP1D)))


#*******************************************************
# IAM Test ENV Spring Batch DB Settings
#*******************************************************
cache.jdbc.driver=oracle.jdbc.pool.OracleDataSource
cache.jdbc.url=jdbc:oracle:thin:@esporadev01.isus.emc.com:1522:MUP1D
cache.jdbc.user=SPRNGBTCH
cache.jdbc.password=springdev


#*******************************************************
# EIS IDS INTERFACE MAIL PROPERTIES
#*******************************************************
ids.mail.hostname=mailhub.lss.emc.com
ids.mail.port=25
ids.mail.from=EIS_IDS@emc.com
ids.mail.to=prem.gadhanki@emc.com
#santosh.gottapu@emc.com
#,suresh.sivananthan@emc.com,roshan.george@emc.com
ids.mail.subject=DEV Env - IDS batch Summary for 



#*******************************************************
# IAM IDS SFTP DEV FOR IDS_AVESKA_SAP_LOAD
#*******************************************************
ids.ftp.type=sftp
ids.sftp.hostname=sftp.emc.com
ids.sftp.port=22
ids.sftp.user=sapaveksanonprd
ids.sftp.password=c!RIxX1YN
ids.sftp.remote.dir=/DEV/IAM/SAPAVESKA/


ids.batch.idm.name=IDM
ids.batch.role.name= User_to_SAP_Role_Mapping
ids.local.dir=C:\\Users\\gadhap\\IDS\\
ids.local.dir.idm=C:\\Users\\gadhap\\IDS\\IDM.csv
ids.local.dir.rolemapping=C:\\Users\\gadhap\\IDS\\User_to_SAP_Role_Mapping.csv
ids.local.file.ext=.csv






