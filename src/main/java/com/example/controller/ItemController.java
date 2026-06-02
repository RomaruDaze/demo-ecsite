package com.example.controller;

import com.example.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpSession session;

    // Optional: Redirect root URL to home
    @GetMapping("")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String home() {
        session.setAttribute("items", itemService.findAll());
        return "home";
    }
}
