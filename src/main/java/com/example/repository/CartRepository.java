package com.example.repository;

import com.example.domain.CartItem;
import com.example.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<CartItem> ROW_MAPPER = (rs, i) -> {
        CartItem c = new CartItem();
        c.setId(rs.getInt("id"));
        c.setUserId(rs.getInt("user_id"));
        c.setItemId(rs.getInt("item_id"));
        c.setQuantity(rs.getInt("quantity"));

        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setPrice(rs.getInt("item_price"));
        item.setImageUrl(rs.getString("item_image"));
        c.setItem(item);

        return c;
    };

    public List<CartItem> findByUserId(Integer userId) {
        String sql = "SELECT c.id, c.user_id, c.item_id, c.quantity, i.name as item_name, i.price as item_price, i.image_url as item_image " +
                "FROM cart c JOIN items i ON c.item_id = i.id WHERE c.user_id = :userId ORDER BY c.id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        return template.query(sql, param, ROW_MAPPER);
    }

    public void add(Integer userId, Integer itemId) {
        // Simple insert (you could upgrade this later to check if the item exists and just update the quantity)
        String sql = "INSERT INTO cart (user_id, item_id, quantity) VALUES (:userId, :itemId, 1)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
        template.update(sql, param);
    }

    public void remove(Integer id) {
        String sql = "DELETE FROM cart WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

    public CartItem findByUserIdAndItemId(Integer userId, Integer itemId) {
        String sql = "SELECT c.id, c.user_id, c.item_id, c.quantity, i.name as item_name, i.price as item_price, i.image_url as item_image " +
                "FROM cart c JOIN items i ON c.item_id = i.id WHERE c.user_id = :userId AND c.item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("itemId", itemId);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateQuantity(Integer id, Integer quantity) {
        String sql = "UPDATE cart SET quantity = :quantity WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", quantity).addValue("id", id);
        template.update(sql, param);
    }
}