package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.NewsHistory;
import com.example.demo.repository.NewsHistoryRepository;

import jakarta.transaction.Transactional;

@Service
public class NewsHistoryService {

	  
	  private final NewsHistoryRepository repository;

	  public NewsHistoryService(NewsHistoryRepository repository) {
	      this.repository = repository;
	  }
	    @Transactional
	    public void saveView(Long userId, Long newsId) {
	        Optional<NewsHistory> existing = repository.findByUserIdAndNewsId(userId, newsId);
	        if (existing.isPresent()) {
	        	NewsHistory record = existing.get();
	            record.setViewedAt(LocalDateTime.now());
	            repository.save(record);
	        } else {
	            repository.save(new NewsHistory(userId, newsId));
	        }
	    }

	    public List<NewsHistory> getUserHistory(Long userId) {
	        return repository.findByUserIdOrderByViewedAtDesc(userId);
	    }
	}

