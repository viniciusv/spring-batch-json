package br.com.ubs.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchJsonApplication implements CommandLineRunner{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchJsonApplication.class);
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("partitionerJob")
	private Job partitionerJob;

	public static void main(String[] args) {
		SpringApplication.run(BatchJsonApplication.class, args);
	}

	
    @Override
    public void run(String... args) throws Exception {
    	try {
    		final JobExecution execution = jobLauncher.run(partitionerJob, new JobParametersBuilder().addLong("JobID",System.currentTimeMillis()).toJobParameters());
            LOGGER.info("Job Status : {}", execution.getStatus());	
    	}catch (final Exception e) {
            e.printStackTrace();
            LOGGER.error("Job failed {}", e.getMessage());
        }        
    }
}
