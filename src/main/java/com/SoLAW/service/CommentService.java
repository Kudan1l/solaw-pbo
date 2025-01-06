package com.SoLAW.service;

import com.SoLAW.model.Comment;
import com.SoLAW.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // Get all comments by thread id
    public List<Comment> getCommentsByThreadId(Long threadId) {
        return commentRepository.findByThreadId(threadId);
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Delete a comment
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
