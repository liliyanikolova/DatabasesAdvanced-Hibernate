package app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> booksByAuthor;

    public Author() {
        this.setBooksByAuthor(new HashSet<>());
    }

    public Author(String firstName, String lastName, Set<Book> booksByAuthor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.setBooksByAuthor(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooksByAuthor() {
        return booksByAuthor;
    }

    public void setBooksByAuthor(Set<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }
}
