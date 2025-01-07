package com.SoLAW.repository;

import com.SoLAW.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
    // JpaRepository sudah menyediakan banyak method default seperti findAll(), findById(), save(), deleteById(), dll
    List<Thread> findByTitleContainingOrContentContaining(String title, String content);
}
