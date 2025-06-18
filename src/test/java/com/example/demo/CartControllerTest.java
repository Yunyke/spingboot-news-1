package com.example.demo;

import com.example.demo.controller.CartController;
import com.example.demo.model.entity.News;
import com.example.demo.service.NewsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @Test
    @DisplayName("GET /cart → 回傳 cart 頁面")
    void cartPage_shouldRenderCartView() throws Exception {
        mockMvc.perform(get("/cart"))
               .andExpect(status().isOk())
               .andExpect(view().name("cart"));
    }

    @Test
    @DisplayName("POST /cart/load → 回傳 cartFragment，並帶入正確 Model")
    void loadCart_shouldReturnFragmentWithNews() throws Exception {
        // 準備 mock 資料
        List<Long> requestIds = List.of(1L, 2L, 999L);

        News news1 = new News();
        news1.setId(1L);
        news1.setTitle("Title A");

        News news2 = new News();
        news2.setId(2L);
        news2.setTitle("Title B");

        List<News> foundNews = List.of(news1, news2);

        when(newsService.getNewsByIds(requestIds)).thenReturn(foundNews);

        mockMvc.perform(post("/cart/load")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1,2,999]"))
               .andExpect(status().isOk())
               .andExpect(view().name("cart :: cartFragment"))
               .andExpect(model().attribute("cartNews", foundNews))
               .andExpect(model().attribute("foundIds", List.of(1L, 2L)));

        verify(newsService, times(1)).getNewsByIds(requestIds);
    }
}
