package com.espark.adarsh.repository;

import com.espark.adarsh.entities.JobDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobRepository  extends CrudRepository<JobDetail, Long> {


    @Query(nativeQuery = true,value = "SELECT * FROM JOB_DETAILS WHERE JOB_NAMES = :name")
    public JobDetail findByJobName(@Param("name") String name);

    @Query(nativeQuery = true,value = "SELECT * FROM JOB_DETAILS WHERE JOB_STATUS IN (:statusList)")
     public List<JobDetail> findByJobStatusIn(@Param("statusList")  List<String> statusList);

    @Query(nativeQuery = true,value = "SELECT * FROM JOB_DETAILS WHERE JOB_NAME IN (:nameList) AND JOB_STATUS IN (:statusList)")
    public List<JobDetail> findByJobNameInAndJobStatusIn(@Param("nameList") List<String> nameList, @Param("statusList") List<String> statusList);

}
