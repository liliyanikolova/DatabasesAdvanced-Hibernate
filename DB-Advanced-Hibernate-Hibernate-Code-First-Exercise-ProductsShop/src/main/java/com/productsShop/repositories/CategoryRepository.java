package com.productsShop.repositories;

import com.productsShop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query(value = "SELECT c.name, COUNT(p.id), AVG(p.price), SUM(p.price) " +
            "FROM Category As c " +
            "INNER JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(p.id)")
    List<Object[]> getCategoryStats();
}
