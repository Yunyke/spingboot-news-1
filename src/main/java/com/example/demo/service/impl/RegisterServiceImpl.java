package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RegisterService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        // 檢查是否已存在相同的 username
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists.");
        }

        // 將 DTO 轉換成 Entity
        User newUser = userMapper.toEntity(userDto);
        newUser.setName(userDto.getName());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBirthdate(userDto.getBirthdate());
        newUser.setGender(userDto.getGender());
        newUser.setEmail(userDto.getEmail());
        newUser.setActive(userDto.getActive() != null ? userDto.getActive() : false);

        // 儲存
        User savedUser = userRepository.save(newUser);

        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public void confirmUser(String email) {
        int updated = userRepository.activateUser(email.trim());
        if (updated == 0) {
            throw new RuntimeException("帳號不存在或已啟用：" + email);
        }
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userMapper.toDto(user);
    }
}
