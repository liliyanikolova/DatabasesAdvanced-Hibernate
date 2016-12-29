package com.productsShop.repositories;

import com.productsShop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value = "SELECT u FROM User AS u " +
            "INNER JOIN u.soldProducts AS p " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u " +
            "HAVING COUNT(p.id) >= 1 " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findWithMoreThanOneBuyer();
}
