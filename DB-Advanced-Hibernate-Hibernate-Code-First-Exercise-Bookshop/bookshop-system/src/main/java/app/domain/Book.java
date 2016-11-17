package app.domain;

import app.domain.enums.AgeRestrictionType;
import app.domain.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = true)
    private String description;

    @Column(name = "edition_type")
    @Enumerated(value = EnumType.STRING)
    private EditionType editionType;

    @Basic
    private BigDecimal price;

    @Basic
    private long copies;

    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @Column(name = "age_restriction")
    @Enumerated(value = EnumType.STRING)
    private AgeRestrictionType ageRestriction;

    public Book() {
        this.setCategories(new HashSet<>());
    }

    public Book(String title,
                String description,
                EditionType editionType,
                BigDecimal price,
                long copies,
                Date releaseDate,
                Author author,
                Set<Category> categories,
                AgeRestrictionType ageRestriction) {
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.author = author;
        this.setCategories(new HashSet<>());
        this.ageRestriction = ageRestriction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tittle) {
        this.title = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCopies() {
        return copies;
    }

    public void setCopies(long copies) {
        this.copies = copies;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public AgeRestrictionType getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestrictionType ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
}
