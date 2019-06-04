package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.impl.MessageServiceImpl;
import com.nc.training.center.chat.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
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
    public String UsersListPage(Map<String, Object> mod) {
        Iterable<User> users = userService.GetAllUsers();
        mod.put("users", users);
        return "UsersListPage";
    }

    @GetMapping("/Chat/{addressee}")
    public String ChatM(Map<String, Object> mod, @PathVariable("addressee") String addressee, @AuthenticationPrincipal User user) {

        List<Message> messages = messageService.chat(mod, addressee, user);
        mod.put("messages", messages);
        return "Chat";
    }

    @GetMapping("/Chat")
    public String Chat(Map<String, Object> mod) {
        return "Chat";
    }

    @GetMapping("/CreateChat")
    public String CreateChat(Map<String, Object> mod) {
        Iterable<User> users = userService.GetAllUsers();
        mod.put("users", users);
        return "CreateChat";
    }

    @PostMapping("/CreateChat")
    public String PostCreateChat(String[] checkbox, String chat) {

        GroupChat groupChat = new GroupChat(chat);
// groupChat.setUsersInChat(Arrays.asList(checkbox));
        List<User> grChatusers = new ArrayList<>();
        for (String check : checkbox) {
            grChatusers.add(userService.getUserByLogin(check));
        }
        groupChat.setUsersInChat(grChatusers);


        return "CreateChat";
    }

    @PostMapping("/Chat/{addressee}")
    public String addInChat(String text, Map<String, Object> mod, @AuthenticationPrincipal User user, @PathVariable("addressee") String addressee) {
        try {

            User addr = messageService.findAddressee(addressee);
            messageService.addMessage(text, user, addr);
            List<Message> messages = messageService.chat(mod, addressee, user);
            mod.put("messages", messages);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/Chat/" + addressee;
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




