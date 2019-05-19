
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


@Service
public class UserServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return user;
    }

    @Override
    public boolean addUser(String login, String pass) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
/*      user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));*/
        userRepository.save(user);
        return true;
    }
}

