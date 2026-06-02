package com.example.controller;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("home")
    public String home() {
        return "home";
    }

    /*
     * Login
     */
    @GetMapping("login")
    public String login(Model model) {
        // Provide an empty form object for Thymeleaf to bind to
        model.addAttribute("userForm", new UserForm());
        return "login";
    }

    @PostMapping("login")
    public String login(@Validated(UserForm.LoginGroup.class) @ModelAttribute("userForm") UserForm form,
                        BindingResult bindingResult, Model model) {
        // Check for validation errors using the LoginGroup rules
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.findAuth(form.getEmail(), form.getPassword());
        if (user == null) {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    /*
     * Sign-In
     */
    @GetMapping("signin")
    public String signin(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "signin";
    }

    @PostMapping("signin")
    public String signin(@Validated(UserForm.SignInGroup.class) @ModelAttribute("userForm") UserForm form,
                         BindingResult bindingResult) {
        // Check for validation errors using the SignInGroup rules
        if (bindingResult.hasErrors()) {
            return "signin";
        }

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setZipcode(form.getZipcode());
        user.setPrefecture(form.getPrefecture());
        user.setMunicipalities(form.getMunicipalities());
        user.setAddress(form.getAddress());
        user.setTelephone(form.getTelephone());

        userService.save(user);
        return "redirect:/login"; // Typically redirect to avoid duplicate submissions
    }
}