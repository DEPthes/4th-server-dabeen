package com.example.database.config;

import com.example.database.domain.BookEntity;
import com.example.database.domain.User;
import com.example.database.domain.User.UserType;
import com.example.database.domain.User.Role;
import com.example.database.repository.BookEntityRepository;
import com.example.database.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    private UserRepository userRepository;
    private BookEntityRepository bookRepository;
    private PasswordEncoder passwordEncoder;
    
    public DataLoader(UserRepository userRepository, BookEntityRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner loadData() {
        return args -> {
            // 사용자 데이터 로드
            if (userRepository.count() == 0) {
                User admin = User.builder()
                    .email("admin@university.edu")
                    .password(passwordEncoder.encode("admin123"))
                    .name("관리자")
                    .userType(UserType.STUDENT)
                    .role(Role.ADMIN)
                    .build();
                userRepository.save(admin);

                User student = User.builder()
                    .email("student@university.edu")
                    .password(passwordEncoder.encode("student123"))
                    .name("학생")
                    .userType(UserType.STUDENT)
                    .role(Role.USER)
                    .build();
                userRepository.save(student);

                User professor = User.builder()
                    .email("professor@university.edu")
                    .password(passwordEncoder.encode("professor123"))
                    .name("교수")
                    .userType(UserType.PROFESSOR)
                    .role(Role.USER)
                    .build();
                userRepository.save(professor);
            }
            
            // 책 데이터 로드
            if (bookRepository.count() == 0) {
                BookEntity book1 = BookEntity.builder()
                    .isbn(9780134685991L)
                    .title("Effective Java")
                    .author("Joshua Bloch")
                    .year(2018)
                    .price(45000)
                    .available(true)
                    .build();
                bookRepository.save(book1);

                BookEntity book2 = BookEntity.builder()
                    .isbn(9780201633610L)
                    .title("Design Patterns")
                    .author("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides")
                    .year(1994)
                    .price(35000)
                    .available(true)
                    .build();
                bookRepository.save(book2);

                BookEntity book3 = BookEntity.builder()
                    .isbn(9780132350884L)
                    .title("Clean Code")
                    .author("Robert C. Martin")
                    .year(2008)
                    .price(30000)
                    .available(true)
                    .build();
                bookRepository.save(book3);

                BookEntity book4 = BookEntity.builder()
                    .isbn(9780201485677L)
                    .title("Refactoring")
                    .author("Martin Fowler")
                    .year(1999)
                    .price(40000)
                    .available(true)
                    .build();
                bookRepository.save(book4);

                BookEntity book5 = BookEntity.builder()
                    .isbn(9780131103627L)
                    .title("The C Programming Language")
                    .author("Brian W. Kernighan, Dennis M. Ritchie")
                    .year(1988)
                    .price(25000)
                    .available(true)
                    .build();
                bookRepository.save(book5);

                BookEntity book6 = BookEntity.builder()
                    .isbn(9780262033848L)
                    .title("Introduction to Algorithms")
                    .author("Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein")
                    .year(2009)
                    .price(60000)
                    .available(true)
                    .build();
                bookRepository.save(book6);

                BookEntity book7 = BookEntity.builder()
                    .isbn(9780131101630L)
                    .title("The Art of Computer Programming")
                    .author("Donald E. Knuth")
                    .year(1997)
                    .price(55000)
                    .available(true)
                    .build();
                bookRepository.save(book7);

                BookEntity book8 = BookEntity.builder()
                    .isbn(9780201896831L)
                    .title("The Mythical Man-Month")
                    .author("Frederick P. Brooks Jr.")
                    .year(1995)
                    .price(28000)
                    .available(true)
                    .build();
                bookRepository.save(book8);
            }
        };
    }
} 