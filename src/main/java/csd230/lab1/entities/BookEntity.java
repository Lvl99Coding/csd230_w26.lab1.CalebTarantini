package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity @DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {
    private String author;
    private String isbn;
    public BookEntity() {}
    public BookEntity(String t, double p, int c, String a) { super(t, p, c); this.author = a; }
    public BookEntity(String t, double p, int c, String a, String i) { super(t, p, c); this.author = a; this.isbn = i; }
    public String getAuthor() { return author; }
    public void setAuthor(String a) { this.author = a; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn_10) { this.isbn = isbn_10; }
    @Override public String toString() { return "Book{author='" + author + "', " + super.toString() + "}"; }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BookEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAuthor(), that.getAuthor());
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor());
    }
}
