package com.foreignexchangeapplicaton.restwebservice.fixerservice;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExchangeRateServiceResult
{
    private boolean success;
    private boolean historical;
    private Date date;
    private String timestamp;
    private String base;
    private Map<String, Double> rates = new HashMap<String, Double>();
}
