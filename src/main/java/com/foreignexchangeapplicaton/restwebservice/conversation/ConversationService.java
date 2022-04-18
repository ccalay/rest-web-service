package com.foreignexchangeapplicaton.restwebservice.conversation;

import com.foreignexchangeapplicaton.restwebservice.Model.Conversation;
import com.foreignexchangeapplicaton.restwebservice.Model.ConversationDAOService;
import com.foreignexchangeapplicaton.restwebservice.exchange.ExchangeRateOutput;
import com.foreignexchangeapplicaton.restwebservice.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class ConversationService
{
    @Autowired
    ExchangeService exchangeService;

    @Autowired
    ConversationDAOService conversationDAOService;

    public ConversationOutput calculateCurrency(String sourceCurrency, String targetCurrency, Double sourceAmount)
    {
        ExchangeRateOutput exchangeRateOutput = exchangeService.getExchangeRate(sourceCurrency, targetCurrency);

        Double calculatedCurrency = exchangeRateOutput.getExchangeRate() * sourceAmount;
        String transactionId = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
        ConversationOutput conversationOutput = new ConversationOutput();
        conversationOutput.setAmount(calculatedCurrency);
        conversationOutput.setTransactionId(transactionId);

        Conversation conversation = new Conversation();
        conversation.setSourceCurrency(sourceCurrency);
        conversation.setTargetCurrency(targetCurrency);
        conversation.setTotalAmount(calculatedCurrency);
        conversation.setExchangeRate(exchangeRateOutput.getExchangeRate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            conversation.setTransactionDate(formatter.parse(formatter.format(new Date())));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        conversation.setTransactionId(transactionId);
        conversation.setSourceAmount(sourceAmount);

        conversationDAOService.saveConversation(conversation);

        return conversationOutput;
    }

    public Conversation getConversationByTransactionId(String transactionId)
    {
        return conversationDAOService.findBytransactionId(transactionId);
    }

    public List<Conversation> getConversationByTransactionDate(Date transactionDate, int pageNo, int pageSize)
    {
        return conversationDAOService.findBytransactionDate(transactionDate, pageNo, pageSize);
    }

}
