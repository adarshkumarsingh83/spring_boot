package com.espark.adarsh;

import com.espark.adarsh.util.FilterUpdateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class ApplicationMain {

	private static final String EQUALS = "=";
	private static final String SPLIT_PATTERN = "||";

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ApplicationMain.class, args);
		String rawCommandLineArgs = getCommandLineArgs(args);
		log.info("label=ContentBatchApplication:main - rawCommandLineArgs {}", rawCommandLineArgs);
		String jobName = rawCommandLineArgs.contains(SPLIT_PATTERN) ? rawCommandLineArgs.substring(0,
				rawCommandLineArgs.indexOf(SPLIT_PATTERN)) : rawCommandLineArgs;

		String params = rawCommandLineArgs.contains(SPLIT_PATTERN)
				? rawCommandLineArgs.substring(rawCommandLineArgs.indexOf(SPLIT_PATTERN) + 2) : "";
		JobLauncher jobLauncher = ctx.getBean("jobLauncher", JobLauncher.class);
		Job job = ctx.getBean(jobName, Job.class);

		// Job parameters
		Map<String, JobParameter> parameters = new HashMap<>();
		JobParameters jobParameters;

		if (StringUtils.isNotBlank(params)) {
			jobParameters = new JobParameters(FilterUpdateUtil.getJobParameters(params));
		} else {
			jobParameters = new JobParameters(parameters);
		}

		JobExecution jobExecution = null;
		try {
			jobExecution = jobLauncher.run(job, jobParameters);
			BatchStatus batchStatus = jobExecution.getStatus();

			while (batchStatus.isRunning()) {
				log.info(" Job is still running {} ", jobExecution.getJobId());
				Thread.sleep(100);
			}
			JobInstance jobInstance = jobExecution.getJobInstance();
			log.info(String.format("********* Name of the job %s", jobInstance.getJobName()));

			log.info(String.format("*********** job instance Id: %d", jobInstance.getId()));
		} catch (JobExecutionAlreadyRunningException jobExeAlreadyRunningExp) {
			log.error("******** JobExecutionAlreadyRunningException : "
					+ jobExeAlreadyRunningExp.getMessage());
		} catch (JobRestartException jobRestartExp) {
			log.error("******** JobRestartException : " + jobRestartExp.getMessage());
		} catch (JobInstanceAlreadyCompleteException jonInsAlreadyComExp) {
			log.error("******** JobInstanceAlreadyCompleteException : "
					+ jonInsAlreadyComExp.getMessage());
		} catch (JobParametersInvalidException jobParamsInvalidExp) {
			log.error("******** JobParametersInvalidException : "
					+ jobParamsInvalidExp.getMessage());
		} catch (InterruptedException inturruptedExp) {
			log.error("******** InterruptedException : " + inturruptedExp.getMessage());
			Thread.currentThread().interrupt();
		} finally {
			System.exit(0);
		}
		log.info(String.format("*********** JOB Exit status: %s", jobExecution.getExitStatus().getExitCode()));
		JobInstance jobInstance = jobExecution.getJobInstance();
		log.info(String.format("********* Name of the job %s", jobInstance.getJobName()));

		log.info(String.format("*********** job instance Id: %d", jobInstance.getId()));
		System.exit(0);
	}

	private static String getCommandLineArgs(String[] args) {
		return Arrays.asList(args)
				.stream()
				.filter(str -> str.startsWith("--command.line.args"))
				.map(str -> str.substring(str.indexOf(EQUALS) + 1))
				.collect(Collectors.joining());
	}

}
