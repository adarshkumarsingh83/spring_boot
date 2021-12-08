package com.espark.adarsh.beans;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Data
@Validated
public class RequestBean implements Serializable {

    @NotBlank(message = "mqtt topic is mandatory properties in configuration ")
    @NotNull(message = "mqtt topic is mandatory properties in configuration ")
    String topic;

    @NotBlank(message = "mqtt topic value is mandatory properties in configuration ")
    @NotNull(message = "mqtt topic value is mandatory properties in configuration ")
    String data;
}
