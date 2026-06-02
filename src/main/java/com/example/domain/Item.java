package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    private boolean deleted;
}
