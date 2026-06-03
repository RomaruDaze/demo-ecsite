package com.example.domain;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id;
    private Integer orderId;
    private Integer itemId;
    private Integer quantity;
    private Integer priceAtPurchase;

    // To hold item details (name, image) on the frontend
    private Item item;
}