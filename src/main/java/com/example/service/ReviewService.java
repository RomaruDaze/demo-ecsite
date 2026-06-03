package com.example.service;

import com.example.domain.Review;
import com.example.domain.ReviewComment;
import com.example.repository.OrderRepository;
import com.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Review> getFullReviewsForItem(Integer itemId, Integer currentUserId) {
        List<Review> reviews = reviewRepository.findReviewsByItemId(itemId, currentUserId);
        for (Review r : reviews) {
            r.setComments(reviewRepository.findCommentsByReviewId(r.getId(), currentUserId));
        }
        return reviews;
    }

    public void addReview(Review review) {
        reviewRepository.addReview(review);
        // Requirement 1: Make order status "Reviewed"
        orderRepository.markOrderAsReviewedByItem(review.getUserId(), review.getItemId());
    }

    public boolean canReview(Integer userId, Integer itemId) {
        return orderRepository.canUserReviewItem(userId, itemId);
    }

    // Pass-throughs
    public void updateReview(Integer id, Integer rating, String comment) { reviewRepository.updateReview(id, rating, comment); }
    public void deleteReview(Integer id) { reviewRepository.deleteReview(id); }
    public void addComment(ReviewComment comment) { reviewRepository.addComment(comment); }
    public void deleteComment(Integer id) { reviewRepository.deleteComment(id); }
    public void toggleLike(Integer userId, Integer reviewId, Integer commentId) { reviewRepository.toggleLike(userId, reviewId, commentId); }
}