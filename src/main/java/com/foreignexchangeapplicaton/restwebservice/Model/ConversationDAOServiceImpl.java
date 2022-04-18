package com.foreignexchangeapplicaton.restwebservice.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class ConversationDAOServiceImpl implements ConversationDAOService
{
    @Autowired
    @Qualifier("conversationDAO")
    private ConversationDAO conversationDAO;

    @PersistenceContext
    public EntityManager em;

    @Override
    public Conversation findBytransactionId(String transactionId)
    {
        return conversationDAO.getById(transactionId);
    }

    @Override
    public List<Conversation> findBytransactionDate(Date transactionDate, int pageNo, int pageSize)
    {
        return em.createQuery("Select conversation from Conversation conversation where conversation.transactionDate=:transactionDate", Conversation.class)
                .setParameter("transactionDate", transactionDate)
                .setMaxResults(pageSize)
                .setFirstResult((pageNo-1) * pageSize)
                .getResultList();

    }

    @Override
    public void saveConversation(Conversation conversation)
    {
        conversationDAO.save(conversation);
    }
}
