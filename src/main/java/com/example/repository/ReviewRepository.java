package com.example.repository;

import com.example.domain.Review;
import com.example.domain.ReviewComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ReviewRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Review> REVIEW_MAPPER = (rs, i) -> {
        Review r = new Review();
        r.setId(rs.getInt("id"));
        r.setItemId(rs.getInt("item_id"));
        r.setUserId(rs.getInt("user_id"));
        r.setRating(rs.getInt("rating"));
        r.setComment(rs.getString("comment"));
        r.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        r.setUserName(rs.getString("user_name"));
        r.setLikeCount(rs.getInt("like_count"));
        r.setLikedByCurrentUser(rs.getInt("liked_by_me") > 0);
        return r;
    };

    private static final RowMapper<ReviewComment> COMMENT_MAPPER = (rs, i) -> {
        ReviewComment c = new ReviewComment();
        c.setId(rs.getInt("id"));
        c.setReviewId(rs.getInt("review_id"));
        c.setUserId(rs.getInt("user_id"));
        c.setComment(rs.getString("comment"));
        c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        c.setUserName(rs.getString("user_name"));
        c.setLikeCount(rs.getInt("like_count"));
        c.setLikedByCurrentUser(rs.getInt("liked_by_me") > 0);
        return c;
    };

    public List<Review> findReviewsByItemId(Integer itemId, Integer currentUserId) {
        String sql = "SELECT r.*, u.name as user_name, " +
                "(SELECT COUNT(*) FROM review_likes rl WHERE rl.review_id = r.id) as like_count, " +
                "(SELECT COUNT(*) FROM review_likes rl WHERE rl.review_id = r.id AND rl.user_id = :currentUserId) as liked_by_me " +
                "FROM reviews r JOIN users u ON r.user_id = u.id WHERE r.item_id = :itemId ORDER BY r.created_at DESC";
        return template.query(sql, new MapSqlParameterSource().addValue("itemId", itemId).addValue("currentUserId", currentUserId == null ? 0 : currentUserId), REVIEW_MAPPER);
    }

    public List<ReviewComment> findCommentsByReviewId(Integer reviewId, Integer currentUserId) {
        String sql = "SELECT c.*, u.name as user_name, " +
                "(SELECT COUNT(*) FROM review_likes rl WHERE rl.comment_id = c.id) as like_count, " +
                "(SELECT COUNT(*) FROM review_likes rl WHERE rl.comment_id = c.id AND rl.user_id = :currentUserId) as liked_by_me " +
                "FROM review_comments c JOIN users u ON c.user_id = u.id WHERE c.review_id = :reviewId ORDER BY c.created_at ASC";
        return template.query(sql, new MapSqlParameterSource().addValue("reviewId", reviewId).addValue("currentUserId", currentUserId == null ? 0 : currentUserId), COMMENT_MAPPER);
    }

    public void addReview(Review review) {
        String sql = "INSERT INTO reviews (item_id, user_id, rating, comment) VALUES (:itemId, :userId, :rating, :comment)";
        template.update(sql, new MapSqlParameterSource().addValue("itemId", review.getItemId()).addValue("userId", review.getUserId()).addValue("rating", review.getRating()).addValue("comment", review.getComment()));
    }

    public void updateReview(Integer id, Integer rating, String comment) {
        String sql = "UPDATE reviews SET rating = :rating, comment = :comment WHERE id = :id";
        template.update(sql, new MapSqlParameterSource().addValue("rating", rating).addValue("comment", comment).addValue("id", id));
    }

    public void deleteReview(Integer id) {
        template.update("DELETE FROM review_likes WHERE review_id = :id", new MapSqlParameterSource().addValue("id", id));
        template.update("DELETE FROM review_comments WHERE review_id = :id", new MapSqlParameterSource().addValue("id", id));
        template.update("DELETE FROM reviews WHERE id = :id", new MapSqlParameterSource().addValue("id", id));
    }

    public void addComment(ReviewComment comment) {
        String sql = "INSERT INTO review_comments (review_id, user_id, comment) VALUES (:reviewId, :userId, :comment)";
        template.update(sql, new MapSqlParameterSource().addValue("reviewId", comment.getReviewId()).addValue("userId", comment.getUserId()).addValue("comment", comment.getComment()));
    }

    public void deleteComment(Integer id) {
        template.update("DELETE FROM review_likes WHERE comment_id = :id", new MapSqlParameterSource().addValue("id", id));
        template.update("DELETE FROM review_comments WHERE id = :id", new MapSqlParameterSource().addValue("id", id));
    }

    public void toggleLike(Integer userId, Integer reviewId, Integer commentId) {
        String checkSql = "SELECT COUNT(*) FROM review_likes WHERE user_id = :userId AND " + (reviewId != null ? "review_id = :targetId" : "comment_id = :targetId");
        Integer count = template.queryForObject(checkSql, new MapSqlParameterSource().addValue("userId", userId).addValue("targetId", reviewId != null ? reviewId : commentId), Integer.class);

        if (count != null && count > 0) {
            String deleteSql = "DELETE FROM review_likes WHERE user_id = :userId AND " + (reviewId != null ? "review_id = :targetId" : "comment_id = :targetId");
            template.update(deleteSql, new MapSqlParameterSource().addValue("userId", userId).addValue("targetId", reviewId != null ? reviewId : commentId));
        } else {
            String insertSql = "INSERT INTO review_likes (user_id, review_id, comment_id) VALUES (:userId, :reviewId, :commentId)";
            template.update(insertSql, new MapSqlParameterSource().addValue("userId", userId).addValue("reviewId", reviewId).addValue("commentId", commentId));
        }
    }
}