package com.example.domain;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Review {
    private Integer id;
    private Integer itemId;
    private Integer userId;
    private String userName; // Fetched via JOIN
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    private int likeCount;
    private boolean likedByCurrentUser;
    private List<ReviewComment> comments;
}