package com.example.controller;

import com.example.domain.User;
import com.example.domain.WishlistItem;
import com.example.service.ItemService;
import com.example.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("home")
    public String home(Model model) {
        session.setAttribute("items", itemService.findAll());
        User user = (User) session.getAttribute("user");

        // If logged in, fetch the IDs of items in the user's wishlist
        if (user != null) {
            List<WishlistItem> wishlist = wishlistService.findByUserId(user.getId());
            List<Integer> wishlistedItemIds = wishlist.stream().map(WishlistItem::getItemId).toList();
            model.addAttribute("wishlistedItemIds", wishlistedItemIds);
        }

        return "home";
    }
}