package com.example.demo.service;

import java.time.LocalDate;
import java.util.Set;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.News;

public interface UserService {
	public UserDto getUser(String username);
	public void addUser(UserDto userDto);
	
}