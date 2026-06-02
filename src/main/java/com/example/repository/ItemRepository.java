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
        return item;
    };

    public List<Item> findAll() {
        System.out.println("RepositoryFindAll");
        String sql = "SELECT * FROM items ORDER by id";
        return template.query(sql, ROW_MAPPER);
    }

    public Item findById(Integer id) {
        String sql = "SELECT * FROM items WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
