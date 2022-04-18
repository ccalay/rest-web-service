package com.foreignexchangeapplicaton.restwebservice.exchange;

public class ExchangeRateOutput
{
    private Double exchangeRate;
    public Double getExchangeRate()
    {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    protected ExchangeRateOutput()
    {
    }

    public ExchangeRateOutput(Double exchangeRate)
    {
        super();
        this.exchangeRate = exchangeRate;
    }
}
