package com.usa.retos.repository;

import com.usa.retos.crud.CategoryCrud;
import com.usa.retos.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @Autowired
    private CategoryCrud categoryCrud;

    public List<Category> getAll() {
        return (List<Category>) categoryCrud.findAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryCrud.findById(id);
    }

    public Category save (Category category) {
        return categoryCrud.save(category);
    }

    public void delete(Category category) {
        categoryCrud.delete(category);
    }
}
