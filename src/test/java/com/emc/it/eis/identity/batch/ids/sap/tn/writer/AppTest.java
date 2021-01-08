package com.emc.it.eis.identity.batch.ids.sap.tn.writer;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:application-context.xml",
    "classpath:job-runner-context.xml"})
public class AppTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    
    //@Test
    public void launchJob() throws Exception {

	//testing a job
        //JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        //assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
        
	//Testing a individual step
    	
    	//JobExecution jobExecution1 = jobLauncherTestUtils.launchStep("fileDownloadTasklet");
    	//JobExecution jobExecution2 = jobLauncherTestUtils.launchStep("deleteIdsTableRec");
        //JobExecution jobExecution3 = jobLauncherTestUtils.launchStep("upLoadFileToIdsTable");
        //JobExecution jobExecution4 = jobLauncherTestUtils.launchStep("deleteRoleMappingTableRec");
    	//JobExecution jobExecution5 = jobLauncherTestUtils.launchStep("upLoadFileToRoleMappingTable");
    	
        //assertEquals(BatchStatus.COMPLETED, jobExecution1.getStatus());
        //assertEquals(BatchStatus.COMPLETED, jobExecution2.getStatus());
        //assertEquals(BatchStatus.COMPLETED, jobExecution3.getStatus());
        //assertEquals(BatchStatus.COMPLETED, jobExecution4.getStatus());
        //assertEquals(BatchStatus.COMPLETED, jobExecution5.getStatus());
    	
        
    }
}