package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Cart;
import com.example.demo.model.entity.News;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.NewsRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final NewsRepository newsRepository;

    /** 建新購物車並回傳 */
    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    /** 把商品加入指定購物車 */
    public Cart addItem(Long cartId, Long newsId) {
        Cart cart = cartRepository.findById(cartId)
                            .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        News news = newsRepository.findById(newsId)
                            .orElseThrow(() -> new IllegalArgumentException("News not found"));
        
        //  檢查是否已加入過
        boolean exists = cart.getItems().stream()
                .anyMatch(item -> item.getNews().getId().equals(newsId));
        if (exists) {
            throw new IllegalStateException("News already exists in cart");
        }

        // 加入新聞
        cart.addNews(news);

        // ✅ 儲存後回傳
        return cartRepository.save(cart);
        
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id)
                       .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }
}
