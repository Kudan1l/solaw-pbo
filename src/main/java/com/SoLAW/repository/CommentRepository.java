package com.SoLAW.repository;

import com.SoLAW.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Menyediakan method default seperti findAll(), findById(), save(), deleteById()
    List<Comment> findByThreadId(Long threadId);
}
