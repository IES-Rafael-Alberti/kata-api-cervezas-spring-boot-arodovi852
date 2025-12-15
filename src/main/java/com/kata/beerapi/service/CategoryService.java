package com.kata.beerapi.service;

import com.kata.beerapi.exception.ResourceNotFoundException;
import com.kata.beerapi.model.Category;
import com.kata.beerapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    public List<Category> searchCategoriesByName(String name) {
        return categoryRepository.findByCatNameContainingIgnoreCase(name);
    }
}
