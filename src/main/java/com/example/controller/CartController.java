package com.example.controller;

import com.example.domain.CartItem;
import com.example.domain.User;
import com.example.service.CartService;
import com.example.service.OrderService;
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

        // NEW: Calculate total price ONLY for checked items
        int total = cartItems.stream()
                .filter(CartItem::isChecked)
                .mapToInt(c -> c.getItem().getPrice() * c.getQuantity())
                .sum();

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
            cartService.add(user.getId(), itemId, false); // Default unchecked
        }

        redirectAttributes.addFlashAttribute("toastMessage", "Added to Cart!");
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    // NEW: Buy Now endpoint
    @PostMapping("/buyNow")
    public String buyNow(@RequestParam Integer itemId) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        CartItem existing = cartService.findByUserIdAndItemId(user.getId(), itemId);
        if (existing != null) {
            cartService.updateQuantity(existing.getId(), existing.getQuantity() + 1);
            cartService.updateChecked(existing.getId(), true); // Force check it
        } else {
            cartService.add(user.getId(), itemId, true); // Insert as checked
        }

        return "redirect:/cart"; // Send straight to cart
    }

    // NEW: Update checked status endpoint
    @PostMapping("/updateChecked")
    public String updateChecked(@RequestParam Integer id, @RequestParam boolean checked) {
        cartService.updateChecked(id, checked);
        return "redirect:/cart";
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


    @Autowired
    private OrderService orderService;

    // Add this new endpoint
    @PostMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        orderService.checkout(user);

        redirectAttributes.addFlashAttribute("toastMessage", "Checkout Successful! A receipt has been sent to your email.");
        return "redirect:/profile"; // Redirect to profile to see the order
    }
}