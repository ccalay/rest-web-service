package com.foreignexchangeapplicaton.restwebservice.exchange;

import com.foreignexchangeapplicaton.restwebservice.exception.ExchangeRateNotFoundException;
import com.foreignexchangeapplicaton.restwebservice.fixerservice.ExchangeRateServiceResult;
import com.foreignexchangeapplicaton.restwebservice.util.AppConstants;
import lombok.Setter;
import org.assertj.core.util.Preconditions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ExchangeService
{
    @Setter
    RestTemplate restTemplate;

    public ExchangeRateOutput getExchangeRate(String fromRate, String toRate)
    {
        ExchangeRateOutput exchangeRateOutput = new ExchangeRateOutput();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(AppConstants.BASEURL)
                .queryParam("access_key", "{access_key}")
                .queryParam("base", "{base}")
                .queryParam("symbols", "{symbols}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("access_key", AppConstants.ACCESS_KEY);
        params.put("base", fromRate);
        params.put("symbols", toRate);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<ExchangeRateServiceResult> exchangeRateServiceResult =
                restTemplate.exchange(urlTemplate,
                        HttpMethod.GET, requestEntity, ExchangeRateServiceResult.class, params);

        if (exchangeRateServiceResult.getStatusCode().equals(HttpStatus.OK))
        {
            if (exchangeRateServiceResult.getBody().isSuccess())
            {
                Double rate = exchangeRateServiceResult.getBody().getRates().get(toRate);
                if(Objects.isNull(rate))
                {
                    throw new ExchangeRateNotFoundException("Cannot get exchangeRate of currency from:" + fromRate + " to:" + toRate);
                }

                exchangeRateOutput.setExchangeRate(rate);
            }
            else
            {
                throw new ExchangeRateNotFoundException("Cannot get exchangeRate of currency from:" + fromRate + " to:" + toRate);
            }
        }

        return exchangeRateOutput;
    }
}
