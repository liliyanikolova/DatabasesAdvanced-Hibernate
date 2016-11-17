package app.dao;

import app.domain.Author;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>{
    Author findById(Long id);

    Iterable<Author> findAll();

    Set<Author> findByBooksByAuthorReleaseDateBefore(Date date);

  @Query("SELECT a, SUM(b.copies) AS copies FROM Book as b INNER JOIN b.author AS a GROUP BY b.author ORDER BY copies DESC")
   List<Object[]> findAllAuthorsBooksCunt();
}
