package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemId implements Serializable {

    private Long cartId;
    private Long newsId;

    public CartItemId() {}

    public CartItemId(Long cartId, Long newsId) {
        this.cartId = cartId;
        this.newsId = newsId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(cartId, that.cartId) &&
               Objects.equals(newsId, that.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, newsId);
    }
}
