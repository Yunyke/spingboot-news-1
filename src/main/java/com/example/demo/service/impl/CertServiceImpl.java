package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CertException;
import com.example.demo.model.dto.UserCert;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertService;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public com.example.demo.model.dto.UserCert getCert(String username, String password) throws CertException {
		
		 User user = userRepository.findByUsername(username)        
                 .orElseThrow(() -> new CertException("查無此人"));

		if (!user.getPassword().equals(password)) {
			throw new CertException("密碼錯誤");

		}

		UserCert userCert = new UserCert(user.getId(), user.getName(), user.getUsername(), null, 
				user.getBirthdate(), user.getGender(), user.getEmail(), user.getActive());
		return userCert;
	} 

}