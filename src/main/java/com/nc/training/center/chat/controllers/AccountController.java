package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

   /* @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        try {

            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login.html";

    }*/


    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String addUser(
            @RequestParam("login") String login,

            @RequestParam("password") String pass ){
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(new BCryptPasswordEncoder().encode(pass));
            user.setActive(true);
           // user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login.html";


    }
}

