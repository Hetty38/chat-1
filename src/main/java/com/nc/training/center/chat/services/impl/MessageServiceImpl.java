package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository msgRepo;

    @Override
    public boolean addMessage(String text, User user, User addr) {

        Message message = new Message(text, user, addr );
        msgRepo.save(message);
        return  true;
    }
}
