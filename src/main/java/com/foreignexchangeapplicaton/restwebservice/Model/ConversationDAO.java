package com.foreignexchangeapplicaton.restwebservice.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("conversationDAO")
public interface ConversationDAO extends JpaRepository<Conversation, String>
{
}
