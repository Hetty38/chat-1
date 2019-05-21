
package com.nc.training.center.chat.controllers;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository msgRepo;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/UserPage")
    public String UserPage(Map<String, Object> mod) {
        Iterable<Message> messages = msgRepo.findAll();
        mod.put("messages", messages);
        return "UserPage";
    }

    @PostMapping("/UserPage")
    public String add(
            String text,
            Map<String, Object> model
    ) {
        Message message = new Message(text);

        msgRepo.save(message);

        Iterable<Message> messages = msgRepo.findAll();

        model.put("messages", messages);

        return "UserPage";
    }
}
