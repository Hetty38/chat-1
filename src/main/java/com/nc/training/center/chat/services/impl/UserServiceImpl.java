
package com.nc.training.center.chat.services.impl;

import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;
import com.nc.training.center.chat.repositories.UserRepository;
import com.nc.training.center.chat.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    @Override
    public boolean addUser(String login, String pass) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }

    @Override
    public Iterable<User> getAllUsers(User user) {
        Iterable<User> users = userRepository.findAllByLoginNot(user.getLogin());
        return users;
    }
    @Override
    public Iterable<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> filterByLogin(String filter) {
        return userRepository.findAllByLogin(filter);
    }

    @Override
    public List<User> getUserByLogin(List<String> logins) {
        return userRepository.findAllByLogin(logins);

    }

}

