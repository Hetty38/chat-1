package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupChatRepository extends CrudRepository<GroupChat, Long> {
    // GroupChat findById(Long id);
    Optional<GroupChat> findById(Long id);
    List<GroupChat> findAllByUsersInChatContains(User user);
}
