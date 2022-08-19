package com.uab.gr10.news.repository;

import com.uab.gr10.news.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

        import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
