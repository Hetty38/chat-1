package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
