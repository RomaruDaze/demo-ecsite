package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findAuth(String email, String password) {
        return userRepository.findAuth(email, password);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void updateAddress(User user) {
        userRepository.save(user);
    }

    public void updateProfile(User user) { userRepository.updateProfile(user); }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
