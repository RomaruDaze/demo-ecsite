package com.example.domain;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Integer totalPrice;
    private String status;
    private LocalDateTime orderDate;

    // To hold the items when displaying the order on the frontend
    private List<OrderItem> orderItems;
}