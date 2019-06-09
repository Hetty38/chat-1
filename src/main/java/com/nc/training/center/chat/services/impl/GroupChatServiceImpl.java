package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.GroupChatRepository;
import com.nc.training.center.chat.services.api.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class GroupChatServiceImpl implements GroupChatService {
    @Autowired
    GroupChatRepository groupChatRepository;
    @Autowired
    UserServiceImpl userService;

    @Override
    public GroupChat getGroupChatById(Long id) {

        return groupChatRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<GroupChat> getChatsByUsersContains(User user) {
        return groupChatRepository.findAllByUsersInChatContains(user);
    }

    @Override
    public GroupChat createGroupChat(String[] checkbox, String name,User user) {
        GroupChat groupChat = new GroupChat(name);
        List<User> grChatusers = new ArrayList<>();

        grChatusers.addAll(userService.getUserByLogin(Arrays.asList(checkbox)));
        grChatusers.add(user);
        groupChat.setUsersInChat(grChatusers);
        groupChatRepository.save(groupChat);
        return groupChat;
    }
}
