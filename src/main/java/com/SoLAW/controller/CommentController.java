package com.SoLAW.controller;

import com.SoLAW.model.Comment;
import com.SoLAW.model.Thread;
import com.SoLAW.service.CommentService;
import com.SoLAW.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThreadService threadService;

    // Display comments for a specific thread
    @GetMapping("/thread/{threadId}")
    public String getCommentsByThreadId(@PathVariable Long threadId, Model model) {
        Thread thread = threadService.getThreadById(threadId)
                .orElseThrow(() -> new RuntimeException("Thread not found"));
        model.addAttribute("thread", thread);
        model.addAttribute("comments", commentService.getCommentsByThreadId(threadId));
        return "Thread/thread-details";  // Returning to thread-details page with comments
    }

    // Create a new comment
    @PostMapping("/create")
    public String createComment(@ModelAttribute Comment comment, @RequestParam Long threadId) {
        Thread thread = threadService.getThreadById(threadId)
                .orElseThrow(() -> new RuntimeException("Thread not found"));
        comment.setThread(thread);
        comment.setCreatedAt(LocalDateTime.now());
        commentService.createComment(comment);
        return "redirect:/threads/" + threadId;  // Redirecting to thread details page
    }

    // Delete a comment
    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id, @RequestParam Long threadId) {
        commentService.deleteComment(id);
        return "redirect:/threads/" + threadId;
    }
}
