package com.example.database.controller;

import com.example.database.domain.OnlineBookStore.PublisherEntity;
import com.example.database.service.OnlineBookStore.BookService;
import com.example.database.service.OnlineBookStore.PublishedByService;
import com.example.database.service.OnlineBookStore.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/onlinebookstore")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private PublishedByService publishedByService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private BookService bookService;

    @GetMapping("/insert_book")
    public String insertBookForm() {
        return "insert_book";
    }

    @GetMapping("/book_prices")
    public ModelAndView getBookPrices() {
        ModelAndView modelAndView = new ModelAndView("book_prices");
        modelAndView.addObject("averagePrice", publishedByService.avgPriceYear());
        modelAndView.addObject("yearlyPrices", publishedByService.avgPriceBookByYear());
        return modelAndView;
    }

    @GetMapping("/author_statistics")
    public ModelAndView getAuthorStatistics() {
        ModelAndView modelAndView = new ModelAndView("author_statistics");
        modelAndView.addObject("authorStats", publishedByService.bookPriceByName());
        return modelAndView;
    }

    @GetMapping("/stock_above/search")
    public ModelAndView searchByStock(@RequestParam(required = false) Integer stock) {
        ModelAndView modelAndView = new ModelAndView("stock_management");
        if (stock != null) {
            modelAndView.addObject("books", bookService.getBooksByStock(stock));
            modelAndView.addObject("minStock", stock);
        }
        return modelAndView;
    }

    @PostMapping("/stock_above/discount")
    public String applyDiscount(@RequestParam int minStock, @RequestParam int discountRate) {
        try {
            bookService.applyDiscountToBooks(minStock, discountRate);
            return "redirect:/onlinebookstore/stock_above/search?stock=" + minStock + "&success=true";
        } catch (Exception e) {
            logger.error("Error applying discount: ", e);
            return "redirect:/onlinebookstore/stock_above/search?stock=" + minStock + "&error=true";
        }
    }

    @PostMapping("/insert_book")
    public String insertBook(int isbn, String title, int year, int price,
                           String authorName, String authorAddress, String authorUrl,
                           String code, String wareAddress, String warePhone, int num) {
        try {
            // 1. 도서 정보 등록
            logger.info("Attempting to insert book with ISBN: {}", isbn);
            publishedByService.insertBook(isbn, title, year, price);
            
            // 2. 저자 정보 등록
            logger.info("Inserting author: {}", authorName);
            publishedByService.insertAuthor(authorName, authorAddress, authorUrl);
            
            // 3. 저자-도서 관계 등록
            logger.info("Linking author with book");
            publishedByService.insertWrittenBy(authorName, authorAddress, isbn);
            
            // 4. 창고 정보 등록
            logger.info("Inserting warehouse: {}", code);
            publishedByService.insertWarehouse(code, wareAddress, warePhone);
            
            // 5. 재고 정보 등록
            logger.info("Adding stock information");
            publishedByService.insertStocks(isbn, code, num);

            // 6. 출판사 정보 등록 (authorName을 출판사로 사용)
            logger.info("Inserting publisher information");
            publisherService.save(PublisherEntity.builder()
                .name(authorName)
                .address(authorAddress)
                .phone(warePhone)
                .url(authorUrl)
                .build());

            // 7. 출판사-도서 관계 등록
            logger.info("Linking publisher with book");
            publishedByService.insertPublishedBy(authorName, isbn);
            
            logger.info("Book registration completed successfully");
            return "redirect:/onlinebookstore/insert_book?success=true";
        } catch (Exception e) {
            logger.error("Error during book registration: ", e);
            return "redirect:/onlinebookstore/insert_book?error=true";
        }
    }
} 