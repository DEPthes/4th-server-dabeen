package com.example.database.service;

import com.example.database.domain.User;
import com.example.database.dto.LoginDto;
import com.example.database.dto.UserDto;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;
    
    @Autowired
    public UserService(UserRepository userRepository, ApplicationContext applicationContext) {
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
    }
    
    private PasswordEncoder getPasswordEncoder() {
        return applicationContext.getBean(PasswordEncoder.class);
    }
    
    public User registerUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(getPasswordEncoder().encode(userDto.getPassword()))
                .name(userDto.getName())
                .userType(User.UserType.valueOf(userDto.getUserType()))
                .department(userDto.getDepartment())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .role(User.Role.USER)
                .build();
        
        return userRepository.save(user);
    }
    
    public User authenticateUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElse(null);
        
        if (user != null && getPasswordEncoder().matches(loginDto.getPassword(), user.getPassword())) {
            return user;
        }
        
        return null;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return user;
    }
    
    public User registerKakaoUser(String kakaoId, String email, String name) {
        if (userRepository.existsByKakaoId(kakaoId)) {
            return userRepository.findByKakaoId(kakaoId).orElse(null);
        }
        User user = User.builder()
                .email(email)
                .password(getPasswordEncoder().encode("kakao_" + kakaoId))
                .name(name)
                .userType(User.UserType.STUDENT)
                .role(User.Role.USER)
                .kakaoId(kakaoId)
                .build();
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
} 