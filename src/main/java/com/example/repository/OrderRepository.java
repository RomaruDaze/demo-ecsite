package com.example.repository;

import com.example.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Order> ROW_MAPPER = (rs, i) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUserId(rs.getInt("user_id"));
        order.setTotalPrice(rs.getInt("total_price"));
        order.setStatus(rs.getString("status"));
        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
        return order;
    };

    public Integer save(Order order) {
        String sql = "INSERT INTO orders (user_id, total_price, status, order_date) " +
                "VALUES (:userId, :totalPrice, :status, :orderDate)";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", order.getUserId())
                .addValue("totalPrice", order.getTotalPrice())
                .addValue("status", order.getStatus())
                .addValue("orderDate", order.getOrderDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder, new String[]{"id"});
        return keyHolder.getKey().intValue();
    }

    public List<Order> findByUserId(Integer userId) {
        String sql = "SELECT * FROM orders WHERE user_id = :userId ORDER BY order_date DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        return template.query(sql, param, ROW_MAPPER);
    }
}