package app.sevice;

import app.domain.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void save(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findBook(Long id);

    Iterable<Book> findBooks();

    Iterable<Book> findBooksAfter(Date date);

    List<Book> findByAuthorOrderByReleaseDate(String authorName);
}
