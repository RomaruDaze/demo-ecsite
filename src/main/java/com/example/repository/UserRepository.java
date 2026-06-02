package com.example.repository;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<User> ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPrefecture(rs.getString("prefecture"));
        user.setZipcode(rs.getString("zipcode"));
        user.setMunicipalities(rs.getString("municipalities"));
        user.setAddress(rs.getString("address"));
        user.setTelephone(rs.getString("telephone"));
        return user;
    };

    public User findAuth(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = :email AND password = :password";
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            // No user found with those credentials
            return null;
        }
    }

    public void save(User user) {
        System.out.println("RepositorySave");
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        if (user.getId() == null) {
            String insertSql = "INSERT INTO users (name,email,password,zipcode,prefecture,municipalities,address,telephone) " +
                    "VALUES (:name,:email,:password,:zipcode,:prefecture,:municipalities,:address,:telephone)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            String[] keyColumnsNames = {"id"};
            template.update(insertSql, param, keyHolder, keyColumnsNames);
            user.setId(keyHolder.getKey().intValue());
            System.out.println(keyHolder.getKey() + "が割り当てられました。");
        } else {
            String updateSql = "UPDATE  " +
                    "SET name=:name" +
                    ",email=:email" +
                    ",password=:password" +
                    ",zipcode=:zipcode" +
                    ",prefecture=:prefecture" +
                    ",municipalities=:municipalities" +
                    ",address=:address" +
                    ",telephone=:telephone " +
                    "WHERE id = :id";

            template.update(updateSql, param);
        }
    }
}
