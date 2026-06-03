package com.example.controller;

import com.example.domain.User;
import com.example.service.ItemService;
import com.example.service.OrderService;
import com.example.service.UserService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String adminDashboard(Model model) {
        User user = (User) session.getAttribute("user");

        // Guard clause: Must be logged in AND have the ADMIN role
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/home";
        }

        // Pass all data to the admin dashboard
        model.addAttribute("users", userService.findAll());
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("orders", orderService.getAllOrders());

        return "admin";
    }

    @PostMapping("/order/update-status")
    public String updateOrderStatus(@RequestParam Integer orderId, @RequestParam String status, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");

        // Security check
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/home";
        }

        orderService.updateOrderStatus(orderId, status);

        // Add a toast message and a flag to remember which tab we were on
        redirectAttributes.addFlashAttribute("toastMessage", "Order #" + orderId + " updated to " + status);
        redirectAttributes.addFlashAttribute("activeTab", "Orders");

        return "redirect:/admin";
    }
}