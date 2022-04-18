package com.foreignexchangeapplicaton.restwebservice.Model;

import java.util.Date;
import java.util.List;

public interface ConversationDAOService
{
    Conversation findBytransactionId(String transactionId);

    List<Conversation> findBytransactionDate(Date transactionDate, int pageNo, int pageSize);

    void saveConversation(Conversation conversation);
}
