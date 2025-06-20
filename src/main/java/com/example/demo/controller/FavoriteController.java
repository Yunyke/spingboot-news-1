package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.FavoriteToggleResponse;
import com.example.demo.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService service;

    // 
    @PostMapping("/{userId}/{newsId}")
    public ResponseEntity<FavoriteToggleResponse> toggle(@PathVariable Integer userId,
            @PathVariable Long newsId) {
boolean nowFav = service.toggleFavorite(userId, newsId);
return ResponseEntity.ok(new FavoriteToggleResponse(newsId, nowFav));

    }
}
