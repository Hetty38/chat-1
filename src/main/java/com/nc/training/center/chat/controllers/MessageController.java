package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.MessageService;
import com.nc.training.center.chat.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
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
        Iterable<User> users = userRepository.findAll();
        mod.put("users", users);
        return "UsersListPage";
    }

    @GetMapping("/Chat/{addressee}")
    public String ChatM(Map<String, Object> mod, @PathVariable("addressee") String addressee, @AuthenticationPrincipal User user) {
        Iterable<Message> messages = messageRepository.findAllByAuthor_LoginOrAddressee_LoginAndAddressee_LoginOrAuthor_Login(user.getLogin(), addressee,addressee,user.getLogin());
        mod.put("messages", messages);
        return "Chat";
    }

    @GetMapping("/Chat")
    public String Chat(Map<String, Object> mod) {
        return "Chat";
    }

    @PostMapping("/Chat/{addressee}")
    public String addInChat(String text, Map<String, Object> mod, @AuthenticationPrincipal User user, @PathVariable("addressee") String addressee) {
        try {
            User addr = userRepository.findByLogin(addressee);
            messageService.addMessage(text, user, addr);
            Iterable<Message> messages = messageRepository.findAllByAuthor_LoginOrAddressee_LoginAndAddressee_LoginOrAuthor_Login(user.getLogin(), addressee,addressee,user.getLogin());//тут проблема
           // ((List<Message>) messages).addAll(messageRepository.findAllByAuthor_LoginAndAddressee_Login(addressee, user.getLogin()));
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
            users = userRepository.findAllByLogin(filter);
        } else {
            users = userRepository.findAll();
        }

        model.put("users", users);

        return "/UsersListPage";
    }
}




