package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {

    // Define validation groups as empty interfaces
    public interface LoginGroup {}
    public interface SignInGroup {}

    @NotBlank(groups = {SignInGroup.class}, message = "Name is required")
    private String name;

    @NotBlank(groups = {LoginGroup.class, SignInGroup.class}, message = "Email is required")
    @Email(groups = {LoginGroup.class, SignInGroup.class}, message = "Invalid email format")
    private String email;

    @NotBlank(groups = {LoginGroup.class, SignInGroup.class}, message = "Password is required")
    private String password;

    @NotBlank(groups = {SignInGroup.class}, message = "Zipcode is required")
    private String zipcode;

    @NotBlank(groups = {SignInGroup.class}, message = "Prefecture is required")
    private String prefecture;

    @NotBlank(groups = {SignInGroup.class}, message = "Municipalities is required")
    private String municipalities;

    @NotBlank(groups = {SignInGroup.class}, message = "Address is required")
    private String address;

    @NotBlank(groups = {SignInGroup.class}, message = "Telephone is required")
    private String telephone;
}