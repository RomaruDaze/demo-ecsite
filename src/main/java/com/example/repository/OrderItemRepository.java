package com.example.repository;

import com.example.domain.Item;
import com.example.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<OrderItem> ROW_MAPPER = (rs, i) -> {
        OrderItem oi = new OrderItem();
        oi.setId(rs.getInt("id"));
        oi.setOrderId(rs.getInt("order_id"));
        oi.setItemId(rs.getInt("item_id"));
        oi.setQuantity(rs.getInt("quantity"));
        oi.setPriceAtPurchase(rs.getInt("price_at_purchase"));

        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("item_name"));
        item.setImageUrl(rs.getString("item_image"));
        oi.setItem(item);

        return oi;
    };

    public void save(OrderItem oi) {
        String sql = "INSERT INTO order_items (order_id, item_id, quantity, price_at_purchase) " +
                "VALUES (:orderId, :itemId, :quantity, :priceAtPurchase)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("orderId", oi.getOrderId())
                .addValue("itemId", oi.getItemId())
                .addValue("quantity", oi.getQuantity())
                .addValue("priceAtPurchase", oi.getPriceAtPurchase());
        template.update(sql, param);
    }

    public List<OrderItem> findByOrderId(Integer orderId) {
        String sql = "SELECT oi.*, i.name as item_name, i.image_url as item_image " +
                "FROM order_items oi JOIN items i ON oi.item_id = i.id " +
                "WHERE oi.order_id = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
        return template.query(sql, param, ROW_MAPPER);
    }
}