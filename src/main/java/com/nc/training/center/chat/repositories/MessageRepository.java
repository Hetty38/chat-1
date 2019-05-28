package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Message;
import com.nc.training.center.chat.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
     Message findAllByAuthor_Login(String name);
  //  List<Message> findAllByAuthor_LoginAndAddressee_Login(String author,String addressee);
    List<Message> findAllByAuthor_LoginOrAddressee_LoginAndAddressee_LoginOrAuthor_Login(String author,String addressee,String author2,String addressee2);

        }
