package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addMessage(String text, User user, User addr) {

        Message message = new Message(text, user, addr);
        messageRepository.save(message);
        return true;
    }

    @Override
    public List<Message> chat(Map<String, Object> mod, String addressee, User user) {
        List<Message> messages = messageRepository.findAllByAuthor_LoginAndAddressee_Login(user.getLogin(), addressee);
        List<Message> messages2 = messageRepository.findAllByAuthor_LoginAndAddressee_Login(addressee, user.getLogin());
        messages.addAll(messages2);
        return messages;
    }

    @Override
    public User findAddressee(String addressee) {
        return userRepository.findByLogin(addressee);
    }

    @Override
    public List<Message> addUsersInChat() {
        Iterable<User> users = userRepository.findAll();

        return null;
    }


}
