package com.uab.gr10.news.services;

import com.uab.gr10.news.models.Category;
import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.repository.CategoryRepository;
import java.util.Optional;
import java.util.UUID;
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
    public Category update(UUID id, Category category) {
        if (categoryRepository.existsById(id)) {
            Category categoryFound = categoryRepository.findById(id).get();
            categoryFound.setName(category.getName());
            categoryFound.setDescription(category.getDescription());
            return categoryRepository.save(categoryFound);
        }
        else{
            return null;
        }
    }
    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

}
