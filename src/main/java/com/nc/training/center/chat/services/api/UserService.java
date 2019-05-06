package com.nc.training.center.chat.services.api;

import com.nc.training.center.chat.domains.Role;
import com.nc.training.center.chat.domains.User;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;

public interface UserService {
    User addUser(User user);

    void delete(long id);

    User getByName(String name);

    User editUser(User user);

    List<User> getAll();


}
