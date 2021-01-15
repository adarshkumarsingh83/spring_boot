package com.hazelcast.samples.serialization.hazelcast.airlines;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Someone who is on a flight. Note that this object is
 * {@link java.io.Serializable} even if the object that
 * holds it is not.
 * </p>
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class Person implements Serializable {

    private String name;
}
