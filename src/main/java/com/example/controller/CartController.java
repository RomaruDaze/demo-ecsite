package com.example.controller;

import com.example.domain.CartItem;
import com.example.domain.User;
import com.example.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String viewCart(Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<CartItem> cartItems = cartService.findByUserId(user.getId());

        // Calculate total price
        int total = cartItems.stream().mapToInt(c -> c.getItem().getPrice() * c.getQuantity()).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", total);
        return "cart";
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer itemId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        CartItem existing = cartService.findByUserIdAndItemId(user.getId(), itemId);
        if (existing != null) {
            cartService.updateQuantity(existing.getId(), existing.getQuantity() + 1);
        } else {
            cartService.add(user.getId(), itemId);
        }

        redirectAttributes.addFlashAttribute("toastMessage", "Added to Cart!");
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    // Add this new endpoint
    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam Integer id, @RequestParam Integer quantity, RedirectAttributes redirectAttributes) {
        if (quantity <= 0) {
            cartService.remove(id);
            redirectAttributes.addFlashAttribute("toastMessage", "Item removed from cart");
        } else {
            cartService.updateQuantity(id, quantity);
            redirectAttributes.addFlashAttribute("toastMessage", "Cart updated");
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Integer id) {
        cartService.remove(id);
        return "redirect:/cart";
    }
}