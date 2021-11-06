package com.usa.retos.crud;

import com.usa.retos.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryCrud extends CrudRepository<Category, Integer> {
}
