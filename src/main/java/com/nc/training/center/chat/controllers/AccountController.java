package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(String login, String password) {
        try {
            userService.addUser(login, password);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login.html";

    }
   /* @GetMapping("/login.html")
  public String login() {
        return "login.html";
    }*/



}

