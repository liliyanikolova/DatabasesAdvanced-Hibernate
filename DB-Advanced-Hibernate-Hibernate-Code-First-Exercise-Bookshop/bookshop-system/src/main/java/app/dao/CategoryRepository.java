package app.dao;

import app.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<app.domain.Category, Long>{
    Category findById(Long id);

    Iterable<Category> findAll();
}
