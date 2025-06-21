package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "select user_id,name,username,password,birthdate,gender,email,active from users where username=:username",nativeQuery = true)
	
	User findByUsername(String username);
	User findByEmail(String email);
	@Modifying
	@Transactional
	@Query("update User u set u.active = true where u.email = :email and u.active = false")
	int activateUser(@Param("email") String email);
}