package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WishlistItem {
    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Item item; // Holds the joined item details
}