package com.example.demo.controller;

import com.example.demo.model.entity.News;
import com.example.demo.model.entity.NewsHistory;
import com.example.demo.service.NewsHistoryService;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RequiredArgsConstructor 
@Controller
public class HistoryController {

 
    private final  NewsHistoryService historyService;

    private final UserService userService;
    private final NewsService newsService; 

    @GetMapping("/history")
    public String viewHistory(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            
            return "redirect:/news";
        }

        List<NewsHistory> historyList = historyService.getUserHistory(userId);
        List<Long> newsIds = historyList.stream()
                .map(NewsHistory::getNewsId)
                .collect(Collectors.toList());
        List<News> newsList = newsService.findByIds(newsIds);
        Map<Long, News> newsMap = newsService.findByIds(
                historyList.stream().map(NewsHistory::getNewsId).toList()
            ).stream().collect(Collectors.toMap(News::getId, news -> news));
        model.addAttribute("historyList", historyList);
        model.addAttribute("newsMap", newsMap);
        return "history";
    }

}
