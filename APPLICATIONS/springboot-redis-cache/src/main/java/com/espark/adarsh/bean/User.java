package com.espark.adarsh.bean;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("user")
public class User implements Serializable {

    String id;
    String userName;

}
