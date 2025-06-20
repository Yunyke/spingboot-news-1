package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.NewsHistory;

public interface NewsHistoryRepository  extends JpaRepository<NewsHistory, Long>{
	 Optional<NewsHistory> findByUserIdAndNewsId(Long userId, Long newsId);
	   List<NewsHistory> findByUserIdOrderByViewedAtDesc(Long userId);
	   
}
