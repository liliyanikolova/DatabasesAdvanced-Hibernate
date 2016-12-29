package com.productsShop.repositories;

import com.productsShop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(value = "SELECT p FROM Product As p " +
            "LEFT JOIN p.buyer AS b " +
            "WHERE p.price BETWEEN 500 AND 1000 " +
//            "AND p.buyer IS NULL " +
            "ORDER BY p.price")
    List<Product> findWithoutBuyer();
}
