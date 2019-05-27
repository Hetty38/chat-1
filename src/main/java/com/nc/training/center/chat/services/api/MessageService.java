package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

public interface MessageService {
    boolean addMessage(String text, User user, User addr);
}
