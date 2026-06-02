package com.example.service;

import com.example.domain.WishlistItem;
import com.example.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public List<WishlistItem> findByUserId(Integer userId) { return wishlistRepository.findByUserId(userId); }
    public void add(Integer userId, Integer itemId) { wishlistRepository.add(userId, itemId); }
    public void remove(Integer id) { wishlistRepository.remove(id); }
    public WishlistItem findByUserIdAndItemId(Integer userId, Integer itemId) {
        return wishlistRepository.findByUserIdAndItemId(userId, itemId);
    }
}