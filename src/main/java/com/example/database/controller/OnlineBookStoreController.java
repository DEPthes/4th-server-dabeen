package com.example.database.controller;

import com.example.database.domain.OnlineBookStore.*;
import com.example.database.repository.OnlineBookStore.*;
import com.example.database.service.OnlineBookStore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 온라인 서점 시스템의 웹 요청을 처리하는 컨트롤러
 * 도서, 저자, 출판사, 재고, 고객 정보 등을 관리
 */
@Controller
@RequestMapping("/bookstore")
public class OnlineBookStoreController {
    @Autowired BookService bookService;
    @Autowired WrittenByService writtenByService;
    @Autowired PublisherService publisherService;
    @Autowired PublishedByService publishedByService;
    @Autowired CustomerService customerService;
    @Autowired StocksService stocksService;
    @Autowired private AuthorService authorService;

    /**
     * 2-(a) 특정 작가의 도서 정보 조회
     * 작가가 작성한 도서의 제목과 재고 수량을 확인
     */
    @GetMapping(value = "/find_book_by_author")
    public ModelAndView find_book_by_author() {
        ModelAndView modelAndView = new ModelAndView("find_book_by_author");
        List<AuthorEntity> authors = authorService.findAll();
        modelAndView.addObject("Authors", authors);
        return modelAndView;
    }

    @GetMapping(value = "/find_book_by_author/{name}")
    public ModelAndView find_book_by_author(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("find_book_by_author_result");
        List<WrittenByRepository.SearchBookInterface> find_book_by_author = writtenByService.searchBook(name);
        modelAndView.addObject("find_book_by_author", find_book_by_author);
        return modelAndView;
    }

    /**
     * 2-(b) 출판사 정보 및 도서 조회
     * 모든 출판사 정보 확인 및 특정 출판사의 도서 정보(제목, 가격, 재고량) 조회
     * 재고가 없는 경우 '재고없음' 표시
     */
    @GetMapping(value = "/find_book_by_publisher")
    public ModelAndView find_book_by_publisher() {
        ModelAndView modelAndView = new ModelAndView("find_book_by_publisher");
        List<PublisherEntity> publishers = publisherService.findAll();
        modelAndView.addObject("Publishers", publishers);
        return modelAndView;
    }

    @GetMapping(value = "/find_book_by_publisher/{name}")
    public ModelAndView find_book_by_publisher(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("find_book_by_publisher_result");
        List<PublishedByRepository.SearchPublisherInterface> find_book_by_publisher = publishedByService.searchPublisher(name);
        modelAndView.addObject("find_book_by_publisher", find_book_by_publisher);
        return modelAndView;
    }

    /**
     * 2-(c) 고객 정보 및 주문 도서 조회
     * 모든 고객 정보 확인 및 특정 고객의 주문 도서, 출판사, 저자 정보 조회
     */
    @GetMapping(value = "/find_book_by_customer")
    public ModelAndView find_book_by_customer() {
        ModelAndView modelAndView = new ModelAndView("find_book_by_customer");
        List<CustomerEntity> customers = customerService.findAll();
        modelAndView.addObject("Customers", customers);
        return modelAndView;
    }

    @GetMapping(value = "/find_book_by_customer/{email}")
    public ModelAndView find_book_by_customer(@PathVariable("email") String email) {
        ModelAndView modelAndView = new ModelAndView("find_book_by_customer_result");
        List<CustomerRepository.SearchCustomerInterface> find_book_by_customer = customerService.searchCustomer(email);
        modelAndView.addObject("find_book_by_customer", find_book_by_customer);
        return modelAndView;
    }

    /**
     * 2-(d) 새로운 도서 등록
     * 도서 정보와 함께 저자, 출판사, 창고별 재고량을 함께 등록
     */
    @GetMapping(value = "/insert_book/form")
    public ModelAndView insert_book_form() {
        ModelAndView modelAndView = new ModelAndView("book_registration_form");
        return modelAndView;
    }

    @PostMapping(value = "/insert_book")
    public ModelAndView insert_book(int isbn, String title, int year, int price,
                                    String authorName, String authorAddress, String authorUrl,
                                    String code, String wareAddress, String warePhone, int num) {
        ModelAndView modelAndView = new ModelAndView("book_registration");
        publishedByService.insertBook(isbn, title, year, price);
        publishedByService.insertAuthor(authorName, authorAddress, authorUrl);
        publishedByService.insertWrittenBy(authorName, authorAddress, isbn);
        publishedByService.insertWarehouse(code, wareAddress, warePhone);
        publishedByService.insertStocks(isbn, code, num);
        return modelAndView;
    }

    /**
     * 2-(e) 도서 가격 통계
     * 전체 도서의 평균 가격 및 연도별 평균 가격 계산
     */
    @GetMapping(value = "/avg_price_year")
    public ModelAndView avg_price_book() {
        ModelAndView modelAndView = new ModelAndView("avg_price_year");
        // 전체 도서의 평균 가격 출력
        double avg_price_year = publishedByService.avgPriceYear();
        modelAndView.addObject("avg_price_year", avg_price_year);
        // 도서의 연도별 평균 가격 출력
        List<PublishedByRepository.AvgPriceBookByYearInterface> avg_price_book_by_year = publishedByService.avgPriceBookByYear();
        modelAndView.addObject("avg_price_book_by_year", avg_price_book_by_year);
        return modelAndView;
    }

    /**
     * 2-(f) 작가별 도서 통계
     * 작가별 도서의 총 개수, 최고/최저/평균 가격 확인
     */
    @GetMapping(value = "/book_statistics_by_author")
    public ModelAndView book_price_by_name() {
        ModelAndView modelAndView = new ModelAndView("book_statistics_by_author");
        List<PublishedByRepository.BookPriceByNameInterface> book_statistics_by_author = publishedByService.bookPriceByName();
        modelAndView.addObject("book_statistics_by_author", book_statistics_by_author);
        return modelAndView;
    }

    /**
     * 2-(g) 재고 기반 도서 검색 및 가격 할인
     * 특정 재고량 이상인 도서 검색 및 가격 할인 적용
     */
    @GetMapping("/stock_above")
    public ModelAndView showStockForm() {
        return new ModelAndView("find_book_by_stock");
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
            return "redirect:/bookstore/stock_above/search?stock=" + minStock + "&success=true";
        } catch (Exception e) {
            return "redirect:/bookstore/stock_above/search?stock=" + minStock + "&error=true";
        }
    }
}
