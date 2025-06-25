package com.example.database.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name="isbn")
    private Long isbn;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="year")
    private int year;

    @Column(name="price")
    private int price;

    @Column(name="available")
    private boolean available = true;

    public BookEntity() {}

    public BookEntity(Long isbn, String title, String author, int year, int price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.available = true;
    }

    public static BookEntityBuilder builder() { return new BookEntityBuilder(); }

    public static class BookEntityBuilder {
        private Long isbn;
        private String title;
        private String author;
        private int year;
        private int price;
        private boolean available = true;

        public BookEntityBuilder isbn(Long isbn) { this.isbn = isbn; return this; }
        public BookEntityBuilder title(String title) { this.title = title; return this; }
        public BookEntityBuilder author(String author) { this.author = author; return this; }
        public BookEntityBuilder year(int year) { this.year = year; return this; }
        public BookEntityBuilder price(int price) { this.price = price; return this; }
        public BookEntityBuilder available(boolean available) { this.available = available; return this; }
        public BookEntity build() { return new BookEntity(isbn, title, author, year, price); }
    }

    public Long getIsbn() { return isbn; }
    public void setIsbn(Long isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() {
        return "BookEntity{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
} 