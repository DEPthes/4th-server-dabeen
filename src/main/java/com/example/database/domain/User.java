package com.example.database.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String department;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String kakaoId;

    public User() {}
    public User(Long id, String email, String password, String name, UserType userType, String department, String phone, String address, Role role, String kakaoId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.department = department;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.kakaoId = kakaoId;
    }
    public static UserBuilder builder() { return new UserBuilder(); }
    public static class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private UserType userType;
        private String department;
        private String phone;
        private String address;
        private Role role;
        private String kakaoId;
        public UserBuilder id(Long id) { this.id = id; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder name(String name) { this.name = name; return this; }
        public UserBuilder userType(UserType userType) { this.userType = userType; return this; }
        public UserBuilder department(String department) { this.department = department; return this; }
        public UserBuilder phone(String phone) { this.phone = phone; return this; }
        public UserBuilder address(String address) { this.address = address; return this; }
        public UserBuilder role(Role role) { this.role = role; return this; }
        public UserBuilder kakaoId(String kakaoId) { this.kakaoId = kakaoId; return this; }
        public User build() {
            return new User(id, email, password, name, userType, department, phone, address, role, kakaoId);
        }
    }
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UserType getUserType() { return userType; }
    public void setUserType(UserType userType) { this.userType = userType; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getKakaoId() { return kakaoId; }
    public void setKakaoId(String kakaoId) { this.kakaoId = kakaoId; }
    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }
    @Override
    public int hashCode() { return Objects.hash(id, email); }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", kakaoId='" + kakaoId + '\'' +
                '}';
    }
    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
    @Override
    public String getUsername() { return email; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
    public enum UserType { STUDENT, PROFESSOR }
    public enum Role { USER, ADMIN }
} 