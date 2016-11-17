package app.serviceImpl;

import app.dao.BookRepository;
import app.domain.Book;
import app.sevice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Primary
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.delete(id);
    }

    @Override
    public Book findBook(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Iterable<Book> findBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Iterable<Book> findBooksAfter(Date date) {
        return this.bookRepository.findAllByReleaseDateAfter(date);
    }

    @Override
    public List<Book> findByAuthorOrderByReleaseDate(String authorName) {
        return this.bookRepository.findByAuthorOrderByReleaseDateDescThenByBookTitleAsc(authorName);
    }
}
