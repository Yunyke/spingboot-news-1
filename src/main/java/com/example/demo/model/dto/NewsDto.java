package com.example.demo.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NewsDto {
		private Long id;
	    private String title;
	    private String description;
	    private String url;
	    private String imageUrl;
	    private String source;
	    private String author;
	    private String content;
}