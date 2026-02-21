package com.springbatch.demo.config;

import com.springbatch.demo.batch.CustomerProcessor;
import com.springbatch.demo.model.Customer;
import com.springbatch.demo.repository.CustomerRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Customer> customerReader(){
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .resource(new FileSystemResource("src/main/resources/data/input.csv"))
                .linesToSkip(1) // skip header
                .delimited()
                .names("id", "name", "email", "age")
                .targetType(Customer.class)
                .build();
    }

    @Bean
    public CustomerProcessor customerProcessor() {
        return new CustomerProcessor();
    }

    @Bean
    public ItemWriter<Customer> customerWriter(CustomerRepository customerRepository) {
        return customerRepository::saveAll;
    }

    @Bean
    public Step CustomerStep(JobRepository jobRepository,
                             PlatformTransactionManager transactionManager,
                             FlatFileItemReader<Customer> reader,
                             ItemProcessor<Customer, Customer> processor,
                             ItemWriter<Customer> writer){
        return new StepBuilder("customerStep", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job customerJob(JobRepository jobRepository, Step customerStep) {
        return new JobBuilder("customerJob", jobRepository)
                .start(customerStep)
                .build();
    }

}
