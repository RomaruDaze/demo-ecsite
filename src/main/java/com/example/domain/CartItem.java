package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItem {
    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Integer quantity;
    private boolean checked;
    private Item item;
}