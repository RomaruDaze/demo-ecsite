package com.example.repository;

import com.example.domain.Item;
import com.example.domain.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<WishlistItem> ROW_MAPPER = (rs, i) -> {
        WishlistItem w = new WishlistItem();
        w.setId(rs.getInt("id"));
        w.setUserId(rs.getInt("user_id"));
        w.setItemId(rs.getInt("item_id"));

        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setPrice(rs.getInt("item_price"));
        item.setImageUrl(rs.getString("item_image"));
        w.setItem(item);

        return w;
    };

    public List<WishlistItem> findByUserId(Integer userId) {
        String sql = "SELECT w.id, w.user_id, w.item_id, i.name as item_name, i.price as item_price, i.image_url as item_image " +
                "FROM wishlist w JOIN items i ON w.item_id = i.id WHERE w.user_id = :userId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        return template.query(sql, param, ROW_MAPPER);
    }

    public void add(Integer userId, Integer itemId) {
        String sql = "INSERT INTO wishlist (user_id, item_id) VALUES (:userId, :itemId)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
        template.update(sql, param);
    }

    public void remove(Integer id) {
        String sql = "DELETE FROM wishlist WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public WishlistItem findByUserIdAndItemId(Integer userId, Integer itemId) {
        String sql = "SELECT w.id, w.user_id, w.item_id, i.name as item_name, i.price as item_price, i.image_url as item_image " +
                "FROM wishlist w JOIN items i ON w.item_id = i.id WHERE w.user_id = :userId AND w.item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("itemId", itemId);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null; // Item is not in the wishlist
        }
    }
}