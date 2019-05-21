
package com.nc.training.center.chat.repositories;

import com.nc.training.center.chat.domains.Message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {



}