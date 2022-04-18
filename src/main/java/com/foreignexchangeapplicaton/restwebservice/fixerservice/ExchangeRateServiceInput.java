package com.foreignexchangeapplicaton.restwebservice.fixerservice;

import lombok.Data;

@Data
public class ExchangeRateServiceInput
{
    private String base;
    private String symbols;
}
