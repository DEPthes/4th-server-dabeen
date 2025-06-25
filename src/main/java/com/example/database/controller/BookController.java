package com.example.database.controller;

import com.example.database.domain.BookEntity;
import com.example.database.domain.BookRental;
import com.example.database.domain.User;
import com.example.database.dto.BookDto;
import com.example.database.service.BookService;
import com.example.database.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    private final UserService userService;
    
    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }
    
    // 책 목록 페이지
    @GetMapping
    public String bookList(@RequestParam(required = false) String keyword, Model model) {
        List<BookEntity> books;
        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookService.searchBooks(keyword);
        } else {
            books = bookService.getAvailableBooks();
        }
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "books/list";
    }
    
    // 책 검색 페이지
    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String keyword,
                             @RequestParam(required = false) String searchType,
                             Model model) {
        List<BookEntity> books;
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            switch (searchType) {
                case "title":
                    books = bookService.searchByTitle(keyword);
                    break;
                case "author":
                    books = bookService.searchByAuthor(keyword);
                    break;
                default:
                    books = bookService.searchBooks(keyword);
                    break;
            }
        } else {
            books = bookService.getAvailableBooks();
        }
        
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        return "books/search";
    }
    
    // 책 상세 정보
    @GetMapping("/{isbn}")
    public String bookDetail(@PathVariable Long isbn, Model model) {
        Optional<BookEntity> book = bookService.getBookByIsbn(isbn);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/detail";
        } else {
            return "redirect:/books?error=notfound";
        }
    }
    
    // 책 대출
    @PostMapping("/{isbn}/rent")
    public String rentBook(@PathVariable Long isbn, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByEmail(auth.getName());
            
            if (user == null) {
                return "redirect:/login";
            }
            
            BookRental rental = bookService.rentBook(user, isbn);
            return "redirect:/books/my-rentals?success=rented";
        } catch (Exception e) {
            return "redirect:/books/" + isbn + "?error=" + e.getMessage();
        }
    }
    
    // 책 반납
    @PostMapping("/{isbn}/return")
    public String returnBook(@PathVariable Long isbn, Model model) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByEmail(auth.getName());
            
            if (user == null) {
                return "redirect:/login";
            }
            
            BookRental rental = bookService.returnBook(user, isbn);
            return "redirect:/books/my-rentals?success=returned";
        } catch (Exception e) {
            return "redirect:/books/my-rentals?error=" + e.getMessage();
        }
    }
    
    // 내 대출 현황
    @GetMapping("/my-rentals")
    public String myRentals(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        
        if (user == null) {
            return "redirect:/login";
        }
        
        List<BookRental> rentals = bookService.getUserRentals(user);
        List<BookRental> activeRentals = bookService.getUserActiveRentals(user);
        
        model.addAttribute("rentals", rentals);
        model.addAttribute("activeRentals", activeRentals);
        model.addAttribute("user", user);
        return "books/my-rentals";
    }
    
    // 책 등록 폼
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "books/add";
    }
    
    // 책 등록 처리
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute BookDto bookDto, 
                         BindingResult result, 
                         Model model) {
        if (result.hasErrors()) {
            return "books/add";
        }
        
        try {
            bookService.registerBook(bookDto);
            return "redirect:/books?success=added";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "books/add";
        }
    }
}