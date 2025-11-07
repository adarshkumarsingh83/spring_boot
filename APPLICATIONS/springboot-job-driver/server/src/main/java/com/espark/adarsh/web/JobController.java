package com.espark.adarsh.web;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.config.JobConfigDetails;
import com.espark.adarsh.config.JobDetails;
import com.espark.adarsh.exception.JobConfigurationNotFound;
import com.espark.adarsh.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class JobController {

    private JobService jobService;

    private JobConfigDetails jobConfigDetails;

    public JobController(JobService jobService,
                         JobConfigDetails jobConfigDetails) {
        this.jobService = jobService;
        this.jobConfigDetails = jobConfigDetails;
    }

    @PostMapping("/job/start")
    public ResponseBean<JobConfig> jobStart(@RequestBody RequestBean<JobConfig> requestBean){
        if(jobConfigDetails.getJobTypes().containsKey(requestBean.getData().getJobName())){
              JobDetails jobDetails = jobConfigDetails.getJobTypes().get(requestBean.getData().getJobName());
            JobConfig jobConfig = this.jobService.getJobStart().apply(jobDetails,requestBean.getData());
            return new ResponseBean<JobConfig>().buildData(jobConfig).buildMessage(jobConfig.getMessage());
        }
        throw new JobConfigurationNotFound("Request Job configuration is not Found | Invalid job requested ");
    }

    @GetMapping("/job/status/{jobId}")
    public ResponseBean<JobConfig> jobStatusById(@PathVariable("jobId") String jobId){
        JobConfig jobConfig =  this.jobService.jobStatusById.apply(jobId);
        return new ResponseBean<JobConfig>().buildData(jobConfig).buildMessage(jobConfig.getMessage());
    }


    @PostMapping("/job/status")
    public ResponseBean<List<JobConfig>> jobStatusByName(@RequestBody RequestBean<String> requestBean){
        List<JobConfig> jobConfig =  this.jobService.jobStatusByName.apply(requestBean.getData());
        return new ResponseBean< List<JobConfig>>().buildData(jobConfig).buildMessage(jobConfig.isEmpty() ? "No Job Found" : "Job Status Request Successfully");
    }

    @PutMapping("/job/abort")
    public ResponseBean< List<JobConfig>> jobAbort(@RequestBody RequestBean<JobConfig> requestBean){
        if(jobConfigDetails.getJobTypes().containsKey(requestBean.getData().getJobName())) {
            JobDetails jobDetails = jobConfigDetails.getJobTypes().get(requestBean.getData().getJobName());
            List<JobConfig> jobConfig = this.jobService.getJobAbort().apply(jobDetails,requestBean.getData().getJobName());
            return new ResponseBean<List<JobConfig>>().buildData(jobConfig).buildMessage(jobConfig.isEmpty() ? "No Job Found" : "Job Aborted Request Successfully");
        }
        throw new JobConfigurationNotFound("Request Job configuration is not Found | Invalid job requested ");
    }
}
