package com.example.database.controller;

import com.example.database.domain.User;
import com.example.database.dto.LoginDto;
import com.example.database.dto.UserDto;
import com.example.database.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, 
                       BindingResult result, 
                       Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "로그인에 실패했습니다.");
            return "login";
        }
    }
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserDto userDto, 
                          BindingResult result, 
                          Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser(userDto);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("user", user);
        return "dashboard";
    }
} 