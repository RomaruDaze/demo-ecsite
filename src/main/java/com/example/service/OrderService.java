package com.example.service;

import com.example.domain.CartItem;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private EmailService emailService;

    @Transactional
    public void checkout(User user) {
        List<CartItem> cartItems = cartService.findByUserId(user.getId());
        List<CartItem> checkedItems = cartItems.stream().filter(CartItem::isChecked).toList();

        if (checkedItems.isEmpty()) return;

        int total = checkedItems.stream().mapToInt(c -> c.getItem().getPrice() * c.getQuantity()).sum();

        // 1. Create the Order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotalPrice(total);
        order.setStatus("PROCESSING");
        order.setOrderDate(LocalDateTime.now());
        Integer orderId = orderRepository.save(order);
        order.setId(orderId);

        // 2. Create Order Items and empty the cart
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem c : checkedItems) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(orderId);
            oi.setItemId(c.getItemId());
            oi.setQuantity(c.getQuantity());
            oi.setPriceAtPurchase(c.getItem().getPrice());

            // Set the item so the email service can print the name
            oi.setItem(c.getItem());

            orderItemRepository.save(oi);
            orderItems.add(oi);

            // Remove from cart
            cartService.remove(c.getId());
        }

        // 3. Send mock email
        emailService.sendOrderReceipt(user, order, orderItems);
    }

    public List<Order> getUserOrders(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        for (Order order : orders) {
            order.setOrderItems(orderItemRepository.findByOrderId(order.getId()));
        }
        return orders;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.setOrderItems(orderItemRepository.findByOrderId(order.getId()));
        }
        return orders;
    }

    public void updateOrderStatus(Integer orderId, String status) {
        orderRepository.updateStatus(orderId, status);
    }
}