package com.usa.retos.crud;

import com.usa.retos.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientCrud extends CrudRepository<Client, Integer> {
}
