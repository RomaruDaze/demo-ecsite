package com.example.service;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    public void sendOrderReceipt(User user, Order order, List<OrderItem> items) {
        // Mocking the email by printing to the terminal
        System.out.println("\n=======================================================");
        System.out.println("MOCK EMAIL SENT TO: " + user.getEmail());
        System.out.println("SUBJECT: Your RogerMart Order Receipt (Order #" + order.getId() + ")");
        System.out.println("-------------------------------------------------------");
        System.out.println("Hello " + user.getName() + ",");
        System.out.println("Thank you for your purchase! Your order is now " + order.getStatus() + ".");
        System.out.println("\nOrder Details:");

        for (OrderItem item : items) {
            System.out.println("- " + item.getItem().getName() + " x" + item.getQuantity() +
                    " (¥" + item.getPriceAtPurchase() + " each)");
        }

        System.out.println("\nTotal Price: ¥" + order.getTotalPrice());
        System.out.println("Shipping to: " + user.getAddress() + ", " + user.getMunicipalities() + ", " + user.getPrefecture());
        System.out.println("=======================================================\n");
    }
}