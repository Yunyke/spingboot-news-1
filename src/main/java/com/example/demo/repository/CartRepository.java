package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
