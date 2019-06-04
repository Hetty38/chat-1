package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.User;

import java.util.List;

public interface UserService {

    boolean addUser(String login, String password);

    Iterable<User> GetAllUsers();

    Iterable<User> FilterByLogin(String filter);
    User getUserByLogin(String username);

   /* void delete(long id);

    User getByName(String name);

    User editUser(User user);

    List<User> getAll();*/


}
