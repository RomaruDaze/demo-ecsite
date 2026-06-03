package com.example.controller;

import com.example.domain.Item;
import com.example.domain.User;
import com.example.domain.WishlistItem;
import com.example.service.ItemService;
import com.example.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/item/{id}")
    public String itemDetail(@PathVariable Integer id, Model model) {
        Item item = itemService.findById(id);
        if (item == null) {
            return "redirect:/home"; // Item not found
        }
        model.addAttribute("item", item);

        User user = (User) session.getAttribute("user");
        if (user != null) {
            WishlistItem wishlistItem = wishlistService.findByUserIdAndItemId(user.getId(), id);
            model.addAttribute("isWishlisted", wishlistItem != null);
        }

        return "detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String query, Model model) {
        // Fetch matching items and update the session
        session.setAttribute("items", itemService.search(query));

        // Re-calculate wishlist state for the results
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<WishlistItem> wishlist = wishlistService.findByUserId(user.getId());
            List<Integer> wishlistedItemIds = wishlist.stream().map(WishlistItem::getItemId).toList();
            model.addAttribute("wishlistedItemIds", wishlistedItemIds);
        }

        // Pass the query back to the view so it stays visible in the search bar
        model.addAttribute("searchQuery", query);

        // Reuse the home page layout to display the results!
        return "home";
    }
}