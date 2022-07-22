package com.espark.adarsh.bean;

import java.io.Serializable;

public record ApplicationMessage(String name, String data) implements Serializable {
}
