package com.example.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;

	@Bean
	public Job getJob() {
		return new JobBuilder("job-1", jobRepository).start(getStep()).
				listener(myJobListener()).build();
	}

	@Bean
	public Step getStep() {
		StepBuilder builder = new StepBuilder("step-1", jobRepository);
		return builder.<String, String>chunk(2, platformTransactionManager).reader(reader()).processor(processor())
				.writer(write()).build();
	}

	@Bean
	public Reader reader() {
		return new Reader();
	}

	@Bean
	public Processor processor() {
		return new Processor();
	}

	@Bean
	public Write write() {
		return new Write();
	}

	@Bean
	public MyJobListener myJobListener() {
		return new MyJobListener();
	}

}
