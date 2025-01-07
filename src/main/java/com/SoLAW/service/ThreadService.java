package com.SoLAW.service;

import com.SoLAW.model.Thread;
import com.SoLAW.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    @Autowired
    private ThreadRepository threadRepository;

    // Get all threads
    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }

    // Get thread by id
    public Optional<Thread> getThreadById(Long id) {
        return threadRepository.findById(id);
    }

    // Create new thread
    public Thread createThread(Thread thread) {
        thread.setCreatedAt(LocalDateTime.now());
        return threadRepository.save(thread);
    }

    // Update existing thread
    public Optional<Thread> updateThread(Long id, Thread thread) {
        if (threadRepository.existsById(id)) {
            thread.setId(id);
            return Optional.of(threadRepository.save(thread));
        }
        return Optional.empty();
    }

    // Delete thread
    public boolean deleteThread(Long id) {
        if (threadRepository.existsById(id)) {
            threadRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Tidak menggunakan static, mengakses instance threadRepository
    public List<Thread> searchThreads(String query) {
        return threadRepository.findByTitleContainingOrContentContaining(query, query);
    }
}

