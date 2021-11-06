package com.usa.retos.service;

import com.usa.retos.model.Category;
import com.usa.retos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Esta clase es el servicio de Category
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Este metodo obtiene toda la lista de categorias
     * @return
     */
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    /**
     * Este metodo obtiene una categoria por id
     * @param id
     * @return
     */
    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    /**
     * Este metodo guarda una categoria
     * @param category
     * @return
     */
    public Category save(Category category) {
        if (category.getId()== null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> ct = categoryRepository.getCategory(category.getId());
            if (ct.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    /**
     * Este metodo actualiza una categoria
     * @param category
     * @return
     */
    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> g = categoryRepository.getCategory(category.getId());
            if (!g.isEmpty()) {
                if (category.getDescription() != null) {
                    g.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    g.get().setName(category.getName());
                }
                categoryRepository.save(g.get());
                return g.get();

            } else {
                return category;
            }
        } else{
            return category;
        }
    }

    /**
     * Este metodo elimina una categoria
     * @param id
     * @return
     */
    public boolean deleteCategory(int id){
        Boolean aBoolean=getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return aBoolean;
    }

}
