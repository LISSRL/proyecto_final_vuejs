package com.uab.gr10.news.services;

import com.uab.gr10.news.models.Category;
import com.uab.gr10.news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category Category) {
        return categoryRepository.save(Category);
    }
}
