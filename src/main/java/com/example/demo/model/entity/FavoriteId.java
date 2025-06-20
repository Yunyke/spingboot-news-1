package com.example.demo.model.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteId implements Serializable {
    private Integer userId;
    private Long newsId;
}