package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.GroupChat;
import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByAuthor_LoginAndAddressee_Login(String author, String addressee);

    List<Message> findAllByGroupChat(GroupChat groupChat);
}
