package com.example.repository;

import com.example.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Item> ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(rs.getInt("price"));
        item.setImageUrl(rs.getString("image_url"));
        item.setDeleted(rs.getBoolean("deleted"));
        try {
            item.setAverageRating(rs.getDouble("average_rating"));
            item.setReviewCount(rs.getInt("review_count"));
        } catch (Exception e) {}
        return item;
    };

    public List<Item> findAll() {
        String sql = "SELECT i.*, COALESCE(AVG(r.rating), 0) as average_rating, COUNT(r.id) as review_count " +
                "FROM items i LEFT JOIN reviews r ON i.id = r.item_id GROUP BY i.id ORDER BY i.id";
        return template.query(sql, ROW_MAPPER);
    }

    public Item findById(Integer id) {
        String sql = "SELECT i.*, COALESCE(AVG(r.rating), 0) as average_rating, COUNT(r.id) as review_count " +
                "FROM items i LEFT JOIN reviews r ON i.id = r.item_id WHERE i.id = :id GROUP BY i.id";SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Item> search(String query) {
        // If the search is empty, just return everything
        if (query == null || query.trim().isEmpty()) {
            return findAll();
        }

        // Split the natural language query into individual words by spaces
        String[] words = query.trim().split("\\s+");

        StringBuilder sql = new StringBuilder(
                "SELECT i.*, COALESCE(AVG(r.rating), 0) as average_rating, COUNT(r.id) as review_count " +
                        "FROM items i LEFT JOIN reviews r ON i.id = r.item_id WHERE 1=1");MapSqlParameterSource param = new MapSqlParameterSource();

        for (int i = 0; i < words.length; i++) {
            // Enforce that EVERY word must be found in either the name OR description
            sql.append(" AND (name ILIKE :word").append(i)
                    .append(" OR description ILIKE :word").append(i).append(")");

            // Wrap the word in '%' for the SQL wildcard match
            param.addValue("word" + i, "%" + words[i] + "%");
        }

        sql.append(" GROUP BY i.id ORDER BY i.id");
        return template.query(sql.toString(), param, ROW_MAPPER);
    }
}
