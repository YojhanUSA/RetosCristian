package com.usa.retos.crud;

import com.usa.retos.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrud extends CrudRepository<Message, Integer> {
}
