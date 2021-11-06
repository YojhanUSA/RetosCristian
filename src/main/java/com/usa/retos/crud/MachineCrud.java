package com.usa.retos.crud;

import com.usa.retos.model.Machine;
import org.springframework.data.repository.CrudRepository;

public interface MachineCrud extends CrudRepository<Machine, Integer> {
}
