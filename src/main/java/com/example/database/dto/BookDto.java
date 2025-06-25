package com.example.database.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDto {
    
    @NotNull(message = "ISBN은 필수입니다")
    @Min(value = 1, message = "ISBN은 1 이상이어야 합니다")
    private Integer isbn;
    
    @NotBlank(message = "제목은 필수입니다")
    private String title;
    
    @NotNull(message = "출판년도는 필수입니다")
    @Min(value = 1900, message = "출판년도는 1900년 이후여야 합니다")
    private Integer year;
    
    @NotNull(message = "가격은 필수입니다")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다")
    private Integer price;
    
    private String author;
    private String publisher;
    private String category;
    private String description;
    private Integer stock;

    public Integer getIsbn() { return isbn; }
    public void setIsbn(Integer isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
} 