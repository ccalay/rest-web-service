package com.foreignexchangeapplicaton.restwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExchangeRateNotFoundException extends RuntimeException
{
    public ExchangeRateNotFoundException(String message)
    {
        super(message);
    }
}
