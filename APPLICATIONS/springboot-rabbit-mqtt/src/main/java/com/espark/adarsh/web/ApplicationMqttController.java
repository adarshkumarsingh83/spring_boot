package com.espark.adarsh.web;

import com.espark.adarsh.beans.RequestBean;
import com.espark.adarsh.service.ApplicationMqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */

@Slf4j
@RestController
public class ApplicationMqttController {

    private static final String PROCESSED_FAILED = "PROCESSED-FAILED";
    private static final String PROCESSED_SUCCESS = "PROCESSED-SUCCESS";

    @Autowired
    ApplicationMqttService applicationMqttService;

    @PostMapping("/mqtt")
    public String postNodeData(@Valid @RequestBody RequestBean data) throws Exception {
        try {
            this.applicationMqttService.publish(data.getTopic(), data.getData(), 1, false);
        } catch (Exception e) {
            log.error("Exception e={}", e.getMessage());
            return PROCESSED_FAILED;
        }
        return PROCESSED_SUCCESS;
    }
}
