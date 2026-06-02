package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String zipcode;
    private String prefecture;
    private String municipalities;
    private String address;
    private String telephone;
}
