package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        // 檢查是否已存在相同的 username
    	Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername());
    	if (userOpt.isPresent()) {
    	    throw new RuntimeException("Username already exists.");
    	
        }

        // 將 DTO 轉換成 Entity
        User newUser = userMapper.toEntity(userDto);
        newUser.setName(userDto.getName());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setBirthdate(userDto.getBirthdate());
        newUser.setGender(userDto.getGender());
        newUser.setEmail(userDto.getEmail());
        newUser.setActive(userDto.getActive() != null ? userDto.getActive() : false);

        // *****設定預設角色
        Role defaultRole = roleRepository.findByName("ROLE_USER");
        newUser.getRoles().add(defaultRole);

        // 儲存
        User savedUser = userRepository.save(newUser);

        // 回傳 DTO
        return userMapper.toDto(savedUser);
    }

	@Override
	public UserDto findByUsername(String username) {
		return userRepository.findByUsername(username)
	            .map(userMapper::toDto)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	}
}