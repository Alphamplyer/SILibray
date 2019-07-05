package com.library.batch_checkloan.configuration;

import com.library.batch_checkloan.BatchTasklet;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration extends DefaultBatchConfigurer {

    // VARIABLES ////////////////////////////////////////////

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private JobLauncher jobLauncher;
    private BatchTasklet batchTasklet;

    // SETTETS //////////////////////////////////////////////

    @Autowired
    public void setJobs(JobBuilderFactory jobs) {
        this.jobBuilderFactory = jobs;
    }

    @Autowired
    public void setSteps(StepBuilderFactory steps) {
        this.stepBuilderFactory = steps;
    }

    @Autowired
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Autowired
    public void setBatchTasklet(BatchTasklet batchTasklet) {
        this.batchTasklet = batchTasklet;
    }

    // METHODS //////////////////////////////////////////////

    @Override
    public void setDataSource(DataSource dataSource) { }

    /**
     * Repeter la tâche dans la classe BatchTasklet tous les jours à 12H.
     **/
    @Scheduled(cron = "0 0 12 * * 1-7")
    public void checkLoanOutOfDate () throws Exception {
        JobParameters parameters = new JobParametersBuilder().addLong("currentTime", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job(), parameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }


    /**
     * Defini un travail qui lance une étape.
     */
    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job").start(step1()).build();
    }

    /**
     * Defini une étape ou on lance la tâche dans la classe BatchTasklet.
     */
    @Bean
    protected Step step1() throws Exception {
        return stepBuilderFactory.get("step1").tasklet(batchTasklet).build();
    }
}
