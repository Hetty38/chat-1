package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.impl.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private UserServiceImpl userService;
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(String login, String password) {
        try {
            userService.addUser(login, password);


        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return "redirect:/login";

    }

}

