package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    /*
    * Home Redirect
    * */
    @GetMapping("home")
    public String home() {
        return "home";
    }

    /*
     * Login
     * */
    @GetMapping("login")
    public String login() {
        System.out.println("yes");
        return "login";
    }

    @PostMapping("login")
    public String login(String email,String password) {
        User user = userService.findAuth(email, password);
        if (user == null) {
            return "redirect:/login";
        }
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    /*
     * Sign-In
     * */
    @GetMapping("signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("signin")
    public String signin(
            String name
            , String email
            , String password
            , String zipcode
            , String prefecture
            , String municipalities
            , String address
            , String telephone) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setZipcode(zipcode);
        user.setPrefecture(prefecture);
        user.setMunicipalities(municipalities);
        user.setAddress(address);
        user.setTelephone(telephone);
        userService.save(user);
        return "login";
    }
}
