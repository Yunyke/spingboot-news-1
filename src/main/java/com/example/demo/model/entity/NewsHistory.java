package com.example.demo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news_history", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "news_id"}))

public class NewsHistory {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long userId;
	    private Long newsId;

	    private LocalDateTime viewedAt = LocalDateTime.now();
	    
	    public NewsHistory(Long userId, Long newsId) {
	        this.userId = userId;
	        this.newsId = newsId;
	        this.viewedAt = LocalDateTime.now();
	    }
	    
	
}
