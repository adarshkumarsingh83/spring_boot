
DROP TABLE IF EXISTS JOB_DETAILS;

CREATE TABLE  JOB_DETAILS (
        job_id INT AUTO_INCREMENT PRIMARY KEY,,
        abort_request boolean,
        completed_on timestamp(6),
        expected_completion timestamp(6),
        job_message varchar(255),
        job_name varchar(255),
        job_status enum ('ABORTED','COMPLETED','EXECUTING','FAILED','IN_QUEUE','REQUEST_FOR_ABORT','STARTING','WAITING'),
        last_iteration timestamp(6),
        started_by varchar(255),
        started_on timestamp(6),
    );

