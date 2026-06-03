package com.example.domain;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewComment {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private String userName; // Fetched via JOIN
    private String comment;
    private LocalDateTime createdAt;

    private int likeCount;
    private boolean likedByCurrentUser;
}