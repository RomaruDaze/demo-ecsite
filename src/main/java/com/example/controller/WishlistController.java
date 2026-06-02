package com.example.controller;

import com.example.domain.User;
import com.example.domain.WishlistItem;
import com.example.service.WishlistService;
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

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private HttpSession session;

    @GetMapping("")
    public String viewWishlist(Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        model.addAttribute("wishlistItems", wishlistService.findByUserId(user.getId()));
        return "wishlist";
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer itemId, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        // Toggle logic
        WishlistItem existing = wishlistService.findByUserIdAndItemId(user.getId(), itemId);
        if (existing != null) {
            wishlistService.remove(existing.getId());
            redirectAttributes.addFlashAttribute("toastMessage", "Removed from Wishlist");
        } else {
            wishlistService.add(user.getId(), itemId);
            redirectAttributes.addFlashAttribute("toastMessage", "Added to Wishlist!");
        }

        // Redirect back to the page the user clicked the button from
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Integer id) {
        wishlistService.remove(id);
        return "redirect:/wishlist";
    }
}