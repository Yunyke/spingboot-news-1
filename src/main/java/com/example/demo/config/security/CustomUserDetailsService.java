//package com.example.demo.config.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.model.entity.User;
//import com.example.demo.repository.UserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//            .orElseThrow(() ->
//                new UsernameNotFoundException("使用者不存在"));
//
//        return new CustomUserDetails(user);
//    }
//
//}
