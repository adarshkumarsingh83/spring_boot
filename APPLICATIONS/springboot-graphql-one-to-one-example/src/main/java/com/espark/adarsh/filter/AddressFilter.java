package com.espark.adarsh.filter;

import lombok.Data;


@Data
public class AddressFilter {

    private FilterField id;
    private FilterField street;
    private FilterField state;
    private FilterField country;

}
