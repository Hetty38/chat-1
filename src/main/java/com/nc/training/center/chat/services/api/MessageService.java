package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;

import java.util.List;
import java.util.Map;

public interface MessageService {
    boolean addMessage(String text, User user, User addr);

    boolean addMessage(String text, User user, GroupChat groupChat);

    List<Message> chat(Map<String, Object> mod, String addressee, User user);

    User findAddressee(String addressee);

    Iterable<Message> MessagesInGroupChat(GroupChat groupChat);

}
