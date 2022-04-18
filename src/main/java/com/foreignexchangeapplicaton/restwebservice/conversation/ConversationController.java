package com.foreignexchangeapplicaton.restwebservice.conversation;

import com.foreignexchangeapplicaton.restwebservice.Model.Conversation;
import com.foreignexchangeapplicaton.restwebservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class ConversationController
{
    @Autowired
    private ConversationService conversationService;

    @GetMapping("/conversation")
    public ConversationOutput conversation(@RequestParam String sourceCurrency, @RequestParam String targetCurrency, @RequestParam Double sourceAmount)
    {
        return conversationService.calculateCurrency(sourceCurrency, targetCurrency, sourceAmount);
    }

    @GetMapping("/conversationList")
    public ConversationServiceResult getConversationList(@RequestParam(required = false) String transactionId,
                                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date transactionDate,
                                                         @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize)
    {
        ConversationServiceResult conversationServiceResult = new ConversationServiceResult();
        if(Objects.nonNull(transactionId))
        {
            Conversation conversation = conversationService.getConversationByTransactionId(transactionId);
            List<Conversation> conversationList = new ArrayList<>();
            conversationList.add(conversation);
            conversationServiceResult.setConversationList(conversationList);
        }
        else if(Objects.nonNull(transactionDate))
        {
            List<Conversation> conversationList = conversationService.getConversationByTransactionDate(transactionDate, pageNo, pageSize);
            conversationServiceResult.setConversationList(conversationList);
        }
        return conversationServiceResult;

    }
}
