package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.UserCert;
import com.example.demo.service.CertService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CertService certService;

    @GetMapping
    public String loginPage(@RequestParam(defaultValue = "/news") String redirect,
            Model model) {
model.addAttribute("redirect", redirect);
return "login";
}
    

      @PostMapping
      public String checkLogin(
              @RequestParam String username,
              @RequestParam String password,
              @RequestParam(defaultValue = "/news") String redirect, 
              HttpSession session,
              HttpServletRequest req,
              Model model) {
  
          try {
              // ✅ 從 certService 取得使用者憑證（包含 name 和 username）
              UserCert userCert = certService.getCert(username, password);
  
              // ✅ 將資訊放入 session
              session.setAttribute("userCert", userCert);
              session.setAttribute("name", userCert.getName());
              session.setAttribute("locale", req.getLocale());
  
              return "redirect:" + redirect;
  
          } catch (Exception e) {
              model.addAttribute("message", e.getMessage());
              return "error";
          }
      }
}