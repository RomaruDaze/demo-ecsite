package com.example.controller;

import com.example.domain.Review;
import com.example.domain.ReviewComment;
import com.example.domain.User;
import com.example.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private HttpSession session;

    private User getLoggedInUser() { return (User) session.getAttribute("user"); }
    private String redirectBack(HttpServletRequest req) { return "redirect:" + req.getHeader("Referer"); }

    @PostMapping("/add")
    public String addReview(Review review, HttpServletRequest req) {
        User user = getLoggedInUser();
        if (user != null && reviewService.canReview(user.getId(), review.getItemId())) {
            review.setUserId(user.getId());
            reviewService.addReview(review);
        }
        return redirectBack(req);
    }

    @PostMapping("/edit")
    public String editReview(@RequestParam Integer id, @RequestParam Integer rating, @RequestParam String comment, HttpServletRequest req) {
        if (getLoggedInUser() != null) reviewService.updateReview(id, rating, comment);
        return redirectBack(req);
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam Integer id, HttpServletRequest req) {
        if (getLoggedInUser() != null) reviewService.deleteReview(id);
        return redirectBack(req);
    }

    @PostMapping("/comment/add")
    public String addComment(ReviewComment comment, HttpServletRequest req) {
        User user = getLoggedInUser();
        if (user != null) {
            comment.setUserId(user.getId());
            reviewService.addComment(comment);
        }
        return redirectBack(req);
    }

    @PostMapping("/comment/delete")
    public String deleteComment(@RequestParam Integer id, HttpServletRequest req) {
        if (getLoggedInUser() != null) reviewService.deleteComment(id);
        return redirectBack(req);
    }

    @PostMapping("/like")
    public String toggleLike(@RequestParam(required = false) Integer reviewId, @RequestParam(required = false) Integer commentId, HttpServletRequest req) {
        User user = getLoggedInUser();
        if (user != null) reviewService.toggleLike(user.getId(), reviewId, commentId);
        return redirectBack(req);
    }
}