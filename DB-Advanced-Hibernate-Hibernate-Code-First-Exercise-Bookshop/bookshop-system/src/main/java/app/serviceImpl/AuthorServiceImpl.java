package app.serviceImpl;

import app.dao.AuthorRepository;
import app.domain.Author;
import app.sevice.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.delete(id);
    }

    @Override
    public Author findAuthor(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Iterable<Author> findAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Set<Author> findAuthorsByBookReleaseDate(Date date) {
        return this.authorRepository.findByBooksByAuthorReleaseDateBefore(date);
    }

    @Override
    public List<Object[]> findAllAuthorsBooksCunt() {
        return this.authorRepository.findAllAuthorsBooksCunt();
    }
}
