package com.example.technomakers.springbatch.exercice;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:job.xml")
public class BatchConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job employeeJob;


    public void runJob() throws Exception {
        jobLauncher.run(employeeJob, new JobParametersBuilder().toJobParameters());
    }

}