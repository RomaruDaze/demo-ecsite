package com.example.service;

import com.example.domain.CartItem;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> findByUserId(Integer userId) { return cartRepository.findByUserId(userId); }
    public void add(Integer userId, Integer itemId) { cartRepository.add(userId, itemId); }
    public void remove(Integer id) { cartRepository.remove(id); }
    public CartItem findByUserIdAndItemId(Integer userId, Integer itemId) { return cartRepository.findByUserIdAndItemId(userId, itemId); }
    public void updateQuantity(Integer id, Integer quantity) { cartRepository.updateQuantity(id, quantity); }
}