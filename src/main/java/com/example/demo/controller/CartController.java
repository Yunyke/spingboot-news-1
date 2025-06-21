package com.example.demo.controller;

import com.example.demo.model.entity.Cart;
import com.example.demo.model.entity.News;
import com.example.demo.service.CartService;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.NewsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor    
public class CartController {

    
    private final NewsService newsService;
    private final CartService cartService;
    private final FavoriteService favoriteService; 
    
    @GetMapping
    public String cartPage() {
        return "cart";  
    }

    @PostMapping
    public Cart createCart() {
        return cartService.createCart();
    }

    @PostMapping("/{cartId}/news/{newsId}")
    public Cart addNewsToCart(@PathVariable Long cartId, @PathVariable Long newsId) {
        return cartService.addItem(cartId, newsId);
    }
    
    @PostMapping("/load")
    public String loadCart(@RequestBody List<Long> newsIds, Model model) {
    	
        System.out.println("🟡 收到的 ID 清單：" + newsIds);

        // 撈出符合 ID 的新聞
        List<News> newsList = newsService.getNewsByIds(newsIds);
        System.out.println("🟢 撈回來的新聞筆數：" + newsList.size());

        // 🔽 新增：過濾出實際有撈到的 ID，並印出沒找到的 ID
        List<Long> foundIds = newsList.stream().map(News::getId).toList();
        List<Long> notFoundIds = newsIds.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        for (News news : newsList) {
            System.out.println("📰 " + news.getId() + ": " + news.getTitle());
        }

        if (!notFoundIds.isEmpty()) {
            System.out.println("⚠️ 以下 ID 找不到對應新聞：" + notFoundIds);
        }
        Integer userId = 3;
        Set<Long> favoriteIds = favoriteService.getFavoriteNewsIds(userId);
        
        model.addAttribute("cartNews", newsList);
        model.addAttribute("favoriteNewsIds", favoriteIds); // ⭐ 必填
        model.addAttribute("foundIds", foundIds);

        
        return "cart :: cartFragment";
        
    }
}