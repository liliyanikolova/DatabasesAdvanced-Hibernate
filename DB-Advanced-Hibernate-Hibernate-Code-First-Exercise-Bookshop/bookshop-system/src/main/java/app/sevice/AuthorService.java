package app.sevice;

import app.domain.Author;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface AuthorService {

    void save(Author author);

    void delete(Author author);

    void delete(Long id);

    Author findAuthor(Long id);

    Iterable<Author> findAuthors();

    Set<Author> findAuthorsByBookReleaseDate(Date date);

    List<Object[]> findAllAuthorsBooksCunt();
}
