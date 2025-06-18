package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select user_id,name,username,password,birthdate,gender,email,active from users where username=:username",nativeQuery = true)
	Optional<User> findByUsername(String username);
	
	
}

