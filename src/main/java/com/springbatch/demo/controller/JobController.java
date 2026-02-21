package com.springbatch.demo.controller;

import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.job.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobLauncher jobLauncher;
    private final Job customerJob;

    public JobController(JobLauncher jobLauncher, Job customerJob) {
        this.jobLauncher = jobLauncher;
        this.customerJob = customerJob;
    }

    @GetMapping("/run-job")
    public String runJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(customerJob, params);
            return "Job started successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to start job: " + e.getMessage();
        }
    }
}
