package com.foreignexchangeapplicaton.restwebservice.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Conversation
{
    @Id
    private String transactionId;

    private Date transactionDate;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private Double sourceAmount;
    private Double totalAmount;
}
