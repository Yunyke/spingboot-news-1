package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Favorite;
import com.example.demo.model.entity.FavoriteId;
import com.example.demo.repository.FavoriteRepository;

@Service
public class FavoriteService {

 private final FavoriteRepository favoriteRepository;

 public FavoriteService(FavoriteRepository favoriteRepository) {
     this.favoriteRepository = favoriteRepository;
 }

 public boolean toggleFavorite(Integer userId, Long newsId) {
     FavoriteId id = new FavoriteId();
     id.setUserId(userId);
     id.setNewsId(newsId);

     if (favoriteRepository.existsById(id)) {
    	 favoriteRepository.deleteByUserIdAndNewsId(userId, newsId);
         return false;              // 取消收藏
     } else {
    	 Favorite favorite = new Favorite();
         favorite.setUserId(userId);
         favorite.setNewsId(newsId);
         favorite.setCreatedAt(LocalDateTime.now());
         favoriteRepository.save(favorite);
         return true;                // 成功收藏
     }
 }

 	public Set<Long> getFavoriteNewsIds(Integer userId){
     return favoriteRepository.findByUserId(userId)
                .stream()
                .map(Favorite::getNewsId)
                .collect(Collectors.toSet());
 }
}
