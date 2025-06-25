package com.example.database.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "book_rentals")
public class BookRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private Integer bookIsbn;
    @Column(nullable = false)
    private LocalDateTime rentalDate;
    @Column(nullable = false)
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    @Enumerated(EnumType.STRING)
    private RentalStatus status;
    public BookRental() {}
    public BookRental(Long id, User user, Integer bookIsbn, LocalDateTime rentalDate, LocalDateTime dueDate, LocalDateTime returnDate, RentalStatus status) {
        this.id = id;
        this.user = user;
        this.bookIsbn = bookIsbn;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    public static BookRentalBuilder builder() { return new BookRentalBuilder(); }
    public static class BookRentalBuilder {
        private Long id;
        private User user;
        private Integer bookIsbn;
        private LocalDateTime rentalDate;
        private LocalDateTime dueDate;
        private LocalDateTime returnDate;
        private RentalStatus status;
        public BookRentalBuilder id(Long id) { this.id = id; return this; }
        public BookRentalBuilder user(User user) { this.user = user; return this; }
        public BookRentalBuilder bookIsbn(Integer bookIsbn) { this.bookIsbn = bookIsbn; return this; }
        public BookRentalBuilder rentalDate(LocalDateTime rentalDate) { this.rentalDate = rentalDate; return this; }
        public BookRentalBuilder dueDate(LocalDateTime dueDate) { this.dueDate = dueDate; return this; }
        public BookRentalBuilder returnDate(LocalDateTime returnDate) { this.returnDate = returnDate; return this; }
        public BookRentalBuilder status(RentalStatus status) { this.status = status; return this; }
        public BookRental build() { return new BookRental(id, user, bookIsbn, rentalDate, dueDate, returnDate, status); }
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Integer getBookIsbn() { return bookIsbn; }
    public void setBookIsbn(Integer bookIsbn) { this.bookIsbn = bookIsbn; }
    public LocalDateTime getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDateTime rentalDate) { this.rentalDate = rentalDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }
    public RentalStatus getStatus() { return status; }
    public void setStatus(RentalStatus status) { this.status = status; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRental that = (BookRental) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
    @Override
    public String toString() {
        return "BookRental{" +
                "id=" + id +
                ", user=" + user +
                ", bookIsbn=" + bookIsbn +
                ", rentalDate=" + rentalDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
    public enum RentalStatus {
        RENTED, RETURNED, OVERDUE
    }
} 