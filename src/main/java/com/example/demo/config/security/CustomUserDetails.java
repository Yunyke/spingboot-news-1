//package com.example.demo.config.security;
//
//import com.example.demo.model.entity.Role;
//import com.example.demo.model.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//import com.example.demo.model.entity.User;
//
////把User 實體包裝成 UserDetails 物件，讓Spring Security 可以認得正確進行認證與授權。
//public class CustomUserDetails implements UserDetails {
//
//    private final User user;
//
//    public CustomUserDetails(User user) {
//        this.user = user;
//    }
//    
//    //將 User 物件的 roles 轉換成 Spring Security 所認得的 GrantedAuthority
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority(role.getName()))
//            .collect(Collectors.toSet());
//    }
//    
//    //登入驗證使用者的帳號密碼
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//    
//    //帳號是否過期。這裡預設不會過期
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    //帳號是否被鎖定
//    @Override
//    public boolean isAccountNonLocked() {
//        return user.getActive(); 
//    }
//    //密碼是否過期
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    //帳號是否啟用
//    @Override
//    public boolean isEnabled() {
//        return user.getActive();
//    }
//}