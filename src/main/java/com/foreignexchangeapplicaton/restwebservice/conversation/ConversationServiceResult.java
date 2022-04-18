package com.foreignexchangeapplicaton.restwebservice.conversation;

import com.foreignexchangeapplicaton.restwebservice.Model.Conversation;
import lombok.Data;

import java.util.List;

@Data
public class ConversationServiceResult
{
    private List<Conversation> conversationList;
}
