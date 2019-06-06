package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.User;

import java.util.List;


public interface GroupChatService {
    GroupChat getGroupChatById(Long id);
    List<GroupChat> getChatsByUsersContains(User user);
    GroupChat createGroupChat(String[] checkbox,String name);
}
