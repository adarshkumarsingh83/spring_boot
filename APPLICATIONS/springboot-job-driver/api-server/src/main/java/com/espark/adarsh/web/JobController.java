package com.espark.adarsh.web;

import com.espark.adarsh.entities.JobDetail;
import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.exception.JobConfigurationNotFoundException;
import com.espark.adarsh.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class JobController {

    private final JobService jobService;

    private final JobsConfigDetails jobsConfigDetails;

    public JobController(JobService jobService,
                         JobsConfigDetails jobsConfigDetails) {
        this.jobService = jobService;
        this.jobsConfigDetails = jobsConfigDetails;
    }

 @PostMapping("/job/start")
    public ResponseBean<JobDetail> jobStart(@RequestBody RequestBean<JobDetail> requestBean){
        if(jobsConfigDetails.getOnRequestJobTypes().containsKey(requestBean.getData().getJobName())){
              OnRequestJobConfig onRequestJobConfig = jobsConfigDetails.getOnRequestJobTypes().get(requestBean.getData().getJobName());
            JobDetail jobDetail = this.jobService.getJobStart().apply(onRequestJobConfig,requestBean.getData());
            return new ResponseBean<JobDetail>().buildData(jobDetail).buildMessage(jobDetail.getJobMessage());
        }
        throw new JobConfigurationNotFoundException("Request Job configuration is not Found | Invalid job requested ");
    }


    @GetMapping("/job/status/{jobId}")
    public ResponseBean<JobDetail> jobStatusById(@PathVariable("jobId") Long jobId){
        JobDetail jobDetail =  this.jobService.jobStatusById.apply(jobId);
        return new ResponseBean<JobDetail>().buildData(jobDetail).buildMessage(jobDetail.getJobMessage());
    }

    @PostMapping("/job/status")
    public ResponseBean<JobDetail> jobStatusByName(@RequestBody RequestBean<String> requestBean){
        JobDetail jobDetail =  this.jobService.jobStatusByName.apply(requestBean.getData());
        return new ResponseBean< JobDetail>().buildData(jobDetail).buildMessage((jobDetail==null)
                ? "No Job Found" : "Job Status Request Successfully");
    }

    @PutMapping("/job/abort")
    public ResponseBean<JobDetail> jobAbort(@RequestBody RequestBean<JobDetail> requestBean){
        if(jobsConfigDetails.getOnRequestJobTypes().containsKey(requestBean.getData().getJobName())) {
            OnRequestJobConfig onRequestJobConfig = jobsConfigDetails.getOnRequestJobTypes().get(requestBean.getData().getJobName());
            JobDetail jobDetail = this.jobService.getJobAbort().apply(onRequestJobConfig,requestBean.getData().getJobName());
            return new ResponseBean<JobDetail>().buildData(jobDetail)
                    .buildMessage( (jobDetail == null) ? "No Job Found" : "Job Aborted Request Successfully");
        }
        throw new JobConfigurationNotFoundException("Request Job configuration is not Found | Invalid job requested ");
    }
}
