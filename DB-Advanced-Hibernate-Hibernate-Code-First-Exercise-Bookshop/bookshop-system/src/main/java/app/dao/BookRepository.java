package app.dao;

import app.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
    Book findById(Long id);

    Iterable<Book> findAll();

    Iterable<Book> findAllByReleaseDateAfter(Date date);

    @Query(value = "SELECT b FROM Book AS b " +
            "INNER JOIN b.author AS a " +
            "WHERE CONCAT(a.firstName,' ', a.lastName) LIKE :authorName " +
            "ORDER BY b.releaseDate DESC, b.title")
    List<Book> findByAuthorOrderByReleaseDateDescThenByBookTitleAsc(@Param(value = "authorName") String authorName);
}
