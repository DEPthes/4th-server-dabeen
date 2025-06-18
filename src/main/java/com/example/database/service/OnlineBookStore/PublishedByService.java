package com.example.database.service.OnlineBookStore;

import com.example.database.domain.OnlineBookStore.*;
import com.example.database.repository.OnlineBookStore.BookRepository;
import com.example.database.repository.OnlineBookStore.PublishedByRepository;
import com.example.database.repository.OnlineBookStore.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishedByService {
    @Autowired
    private PublishedByRepository publishedByRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StocksRepository stocksRepository;

    public List<PublishedByEntity> findAll() { return publishedByRepository.findAll(); }

    // 2-(b)
    public List<PublishedByRepository.SearchPublisherInterface> searchPublisher(String name) {
        return publishedByRepository.searchPublisher(name);}

    // 2-(d)
    public void insertBook(int isbn, String title, int year, int price) {
        publishedByRepository.insertBook(isbn, title, year, price);
    }

    public void insertAuthor(String authorName, String authorAddress, String authorUrl) {
        publishedByRepository.insertAuthor(authorName, authorAddress, authorUrl);
    }

    public void insertWrittenBy(String authorName, String authorAddress, int isbn) {
        publishedByRepository.insertWrittenBy(authorName, authorAddress, isbn);
    }

    public void insertWarehouse(String code, String wareAddress, String warePhone) {
        publishedByRepository.insertWarehouse(code, wareAddress, warePhone);
    }

    public void insertStocks(int isbn, String code, int num) {
        publishedByRepository.insertStocks(isbn, code, num);
    }

    public void insertPublishedBy(String publisherName, int isbn) {
        publishedByRepository.insertPublishedBy(publisherName, isbn);
    }

    // 2-(e)
    public double avgPriceYear() { return bookRepository.getAvgPriceYear();}
    public List<PublishedByRepository.AvgPriceBookByYearInterface> avgPriceBookByYear() {
        return publishedByRepository.avgPriceBookByYear();}

    // 2-(f)
    public List<PublishedByRepository.BookPriceByNameInterface> bookPriceByName() {
        return publishedByRepository.bookPriceByName();}

}
