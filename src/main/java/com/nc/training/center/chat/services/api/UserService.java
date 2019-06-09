package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

import java.util.List;

public interface UserService {

    boolean addUser(String login, String password);

    Iterable<User> getAllUsers(User user);

    Iterable<User> getAllUsers();

    List<User> filterByLogin(String filter);

    List<User> getUserByLogin(List<String> logins);


}
