package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
     Message findAllByAuthor_Login(String name);
    List<Message> findAllByAuthor_LoginAndAddressee_Login(String author,String addressee);


        }
