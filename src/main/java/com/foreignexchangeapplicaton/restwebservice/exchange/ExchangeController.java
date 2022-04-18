package com.foreignexchangeapplicaton.restwebservice.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExchangeController
{
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchangeRate")
    public ExchangeRateOutput getExchangeRate(@RequestParam String fromRate, @RequestParam String toRate)
    {
        return exchangeService.getExchangeRate(fromRate, toRate);
    }
}
