package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.NewsHistoryService;

import jakarta.servlet.http.HttpSession;

@RestController                     
@RequestMapping("/api/history")
public class HistoryApiController {

    @Autowired
    private NewsHistoryService historyService;

    /**  前端 JS 會 POST: { "newsId": 123 } */
    @PostMapping
    public ResponseEntity<?> addHistory(@RequestBody Map<String, Long> payload,
                                        HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Long newsId = payload.get("newsId");
        if (newsId == null) return ResponseEntity.badRequest().body("newsId missing");

        historyService.saveView(userId, newsId);
        return ResponseEntity.ok().build();
    }
}
