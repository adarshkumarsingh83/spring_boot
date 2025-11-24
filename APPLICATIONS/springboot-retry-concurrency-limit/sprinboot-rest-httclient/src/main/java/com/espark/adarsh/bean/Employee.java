package com.espark.adarsh.bean;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    Long id;
    String name;
    String email;

}
