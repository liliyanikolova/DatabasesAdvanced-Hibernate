package app.console;

import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestrictionType;
import app.domain.enums.EditionType;
import app.sevice.AuthorService;
import app.sevice.BookService;
import app.sevice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner{

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... strings) throws Exception {
        seedDatabase();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,1,1);
        Iterable<Book> books = this.bookService.findBooksAfter(calendar.getTime());
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        System.out.println("--------------------------------------------");

        calendar.set(1990,1,1);
        Iterable<Author> authors = this.authorService.findAuthorsByBookReleaseDate(calendar.getTime());
        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

        System.out.println("--------------------------------------------");

        List<Object[]> objects = this.authorService.findAllAuthorsBooksCunt();
        for (Object[] object : objects) {
            Author author = (Author) object[0];
            long copiesCount = (long) object[1];
            System.out.println(String.format("%s %s - %s", author.getFirstName(), author.getLastName(), copiesCount));
        }

        System.out.println("--------------------------------------------");

        List<Book> booksByAuthor = this.bookService.findByAuthorOrderByReleaseDate("George Powell");
        for (Book book : booksByAuthor) {
            System.out.println(String.format("%s, %s, %s", book.getTitle(), book.getReleaseDate(), book.getCopies()));
        }

    }

    private void seedDatabase() throws IOException, ParseException {
        InputStream authorData = getClass().getResourceAsStream("/authors.txt");
        BufferedReader authorReader = new BufferedReader(new InputStreamReader(authorData));
        List<Author> authors = new ArrayList<>();
        String line = authorReader.readLine();
        while((line = authorReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(data[0]);
            author.setLastName(data[1]);
            authors.add(author);
            authorService.save(author);
        }

        InputStream categoryData = getClass().getResourceAsStream("/categories.txt");
        BufferedReader categoryReader = new BufferedReader(new InputStreamReader(categoryData));
        List<Category> categories = new ArrayList<>();
        line = categoryReader.readLine();
        while((line = categoryReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            Category category = new Category();
            category.setName(data[0]);
            categories.add(category);
            categoryService.save(category);
        }
        
        
        InputStream bookData = getClass().getResourceAsStream("/books.txt");
        BufferedReader bookReader = new BufferedReader(new InputStreamReader(bookData));
        line = bookReader.readLine();
        while((line = bookReader.readLine()) != null){
            String[] data = line.split("\\s+");

            Random random = new Random();
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestrictionType ageRestriction = AgeRestrictionType.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            //TODO add random categories for current book

            bookService.save(book);
        }


    }
}
