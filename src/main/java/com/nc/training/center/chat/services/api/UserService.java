package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

import java.util.List;

public interface UserService {

    boolean addUser(String login, String password);

    Iterable<User> GetAllUsers(User user);
    Iterable<User> GetAllUsers();

    List<User> FilterByLogin(String filter);

    List<User> getUserByLogin(List<String> logins);


}
