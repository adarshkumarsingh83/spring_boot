package com.espark.adarsh.record;

import java.io.Serializable;

public record Employee(String id, String name, String email) implements Serializable {
}
