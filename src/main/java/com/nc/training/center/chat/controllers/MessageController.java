package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.ChatApplication;
import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.impl.GroupChatServiceImpl;
import com.nc.training.center.chat.services.impl.MessageServiceImpl;
import com.nc.training.center.chat.services.impl.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.logging.Logger;

@Controller
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupChatServiceImpl groupChatService;
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        // LOGGER.info("-------------------************************");
        return "login";

    }
/*
    @GetMapping("/UserPage")
    public String UserPage(Map<String, Object> mod) {
        Iterable<Message> messages = messageRepository.findAll();
        mod.put("messages", messages);
        return "UserPage";
    }*/

    /*@PostMapping("/UserPage")
    public String add(String text, Map<String, Object> mod, @AuthenticationPrincipal User user) {
        try {
            User addr = userRepository.findByLogin(addressee);
            messageService.addMessage(text, user, addr);
            Iterable<Message> messages = messageRepository.findAll();
            mod.put("messages", messages);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "UserPage";
    }*/

    @GetMapping("/UsersListPage")
    public String usersListPage(Map<String, Object> mod) {
        Iterable<User> users = userService.GetAllUsers();
        mod.put("users", users);
        return "UsersListPage";
    }

    @GetMapping("/GroupChat/{IdChat}")
    public String groupChat(Map<String, Object> mod, @AuthenticationPrincipal User user, @PathVariable Long IdChat) {
        GroupChat groupChat = groupChatService.getGroupChatById(IdChat);
        groupChat.getUsersInChat().add(user);
        Iterable<Message> messages = messageService.MessagesInGroupChat(groupChat);
        mod.put("messages", messages);
        return "GroupChat/{IdChat}";
    }

    @PostMapping("/GroupChat/{IdChat}")
    public String groupChatPost(Map<String, Object> mod, @AuthenticationPrincipal User user, @PathVariable Long IdChat, String text) {
        GroupChat groupChat = groupChatService.getGroupChatById(IdChat);
        groupChat.getUsersInChat().add(user);
        messageService.addMessage(text, user);
        Iterable<Message> messages = messageService.MessagesInGroupChat(groupChat);
        mod.put("messages", messages);
        return "GroupChat/{IdChat}";
    }

    @GetMapping("/PersonalChat/{addressee}")
    public String chatM(Map<String, Object> mod, @PathVariable("addressee") String addressee, @AuthenticationPrincipal User user) {

        List<Message> messages = messageService.chat(mod, addressee, user);
        mod.put("messages", messages);
        return "PersonalChat";
    }

    @GetMapping("/PersonalChat")
    public String chat(Map<String, Object> mod) {
        return "PersonalChat";
    }

    @GetMapping("/CreateChat")
    public String createChat(Map<String, Object> mod) {
        Iterable<User> users = userService.GetAllUsers();
        mod.put("users", users);
        return "CreateChat";
    }

    @PostMapping("/CreateChat")
    public String postCreateChat(String[] checkbox, String chat) {

        GroupChat groupChat = groupChatService.createGroupChat(checkbox, chat);


        return "GroupChat/" + groupChat.getId();

    }

    @GetMapping("groupChats")
    public String listGroupChats(Map<String, Object> mod, @AuthenticationPrincipal User user) {

        Iterable<GroupChat> groupChats = groupChatService.getChatsByUsersContains(user);
        mod.put("groupChats", groupChats);
        return "/CreateChat";
    }

    @PostMapping("/PersonalChat/{addressee}")
    public String addInChat(String text, Map<String, Object> mod, @AuthenticationPrincipal User user, @PathVariable("addressee") String addressee) {
        try {

            User addr = messageService.findAddressee(addressee);
            messageService.addMessage(text, user, addr);
            List<Message> messages = messageService.chat(mod, addressee, user);
            mod.put("messages", messages);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }

        return "redirect:/PersonalChat/" + addressee;
    }

    @PostMapping("filter")
    public String filter(String filter, Map<String, Object> model) {
        Iterable<User> users;

        if (filter != null && !filter.isEmpty()) {
            users = userService.FilterByLogin(filter);
        } else {
            users = userService.GetAllUsers();
        }

        model.put("users", users);

        return "/UsersListPage";
    }

}




