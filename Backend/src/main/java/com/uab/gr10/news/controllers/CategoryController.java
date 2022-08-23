package com.uab.gr10.news.controllers;


import com.uab.gr10.news.models.Category;
import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.services.CategoryService;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/categories")
@CrossOrigin(origins = "${vue.origin.url}")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") UUID id) {
        return categoryService.getCategoryById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(EntityNotFoundException::new);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,
        @PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Notice> deleteById(@PathVariable(value = "id") UUID id) {
        return categoryService.deleteCategoryById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
