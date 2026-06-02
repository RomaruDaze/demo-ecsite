package com.example.controller;

import com.example.domain.CartItem;
import com.example.domain.User;
import com.example.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<CartItem> cartItems = cartService.findByUserId(user.getId());
            // Sum all quantities in the cart
            int cartCount = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
            model.addAttribute("cartCount", cartCount);
        }
    }
}