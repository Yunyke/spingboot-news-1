package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.News;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUser(String username) {
        return userRepository.findByUsername(username)
            .map(userMapper::toDto)
            .orElse(null); 
    }

    @Override
    public void addUser(UserDto userDto) {
    	 if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
             throw new RuntimeException("Username already exists.");
         }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
        
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(defaultRole);

        userRepository.save(user);
    }

//    // ⭐ 新增：收藏新聞
//    @Override
//    public void addFavorite(Integer userId, Long newsId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("找不到使用者"));
//
//        News news = newsRepository.findById(newsId)
//                .orElseThrow(() -> new RuntimeException("找不到新聞"));
//
//        if (user.getFavoriteNews() == null) {
//            user.setFavoriteNews(new HashSet<>());
//        }
//
//        user.getFavoriteNews().add(news);
//        userRepository.save(user);
//    }
//
//    // ⭐ 新增：移除收藏
//    @Override
//    public void removeFavorite(Integer userId, Long newsId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("找不到使用者"));
//
//        News news = newsRepository.findById(newsId)
//                .orElseThrow(() -> new RuntimeException("找不到新聞"));
//
//        if (user.getFavoriteNews() != null) {
//            user.getFavoriteNews().remove(news);
//        }
//
//        userRepository.save(user);
//    }
//
//    // ⭐ 新增：取得收藏
//    @Override
//    public Set<News> getFavorites(Integer userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("找不到使用者"));
//        return user.getFavoriteNews();
//    }
}