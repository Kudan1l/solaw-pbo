package com.SoLAW.controller;

import com.SoLAW.model.Thread;
import com.SoLAW.repository.ThreadRepository;
import com.SoLAW.service.ThreadService;
import com.SoLAW.repository.CommentRepository;
import com.SoLAW.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    private ThreadRepository threadRepository;
    @Autowired
    private CommentService commentService;  // Pastikan ini ada
    @Autowired
    private ThreadService threadService;

    // Get all threads to display on the page
    @GetMapping
    public String getAllThreads(Model model) {
        List<Thread> threads = threadRepository.findAll();

        // Convert LocalDateTime to Date before passing to Thymeleaf
        for (Thread thread : threads) {
            if (thread.getCreatedAt() != null) {
                Date createdAtDate = Date.from(thread.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
                thread.setCreatedAt(convertToLocalDateTime(createdAtDate));
            }
        }

        model.addAttribute("threads", threads);
        return "Thread/threads"; // Returns threads.html
    }

    // Get thread by id
    @GetMapping("/{id}")
    public String getThreadById(@PathVariable Long id, Model model) {
        Optional<Thread> thread = threadRepository.findById(id);
        if (thread.isPresent()) {
            Thread threadData = thread.get();
            if (threadData.getCreatedAt() != null) {
                Date createdAtDate = Date.from(threadData.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
                threadData.setCreatedAt(convertToLocalDateTime(createdAtDate));
            }
            model.addAttribute("thread", threadData);
            model.addAttribute("comments", commentService.getCommentsByThreadId(id));  // Menggunakan instance commentService
            return "Thread/thread-details"; // Returns thread-details.html
        }
        return "redirect:/threads";
    }



    // Create a new thread (for displaying the form)
    @GetMapping("/create")
    public String showCreateThreadForm(Model model) {
        model.addAttribute("thread", new Thread());
        return "Thread/create-thread"; // Returns create-thread.html
    }

    // Post the new thread (after the form submission)
    @PostMapping
    public String createThread(@ModelAttribute Thread thread) {
        thread.setCreatedAt(LocalDateTime.now());  // Set the current LocalDateTime
        threadRepository.save(thread);
        return "redirect:/threads"; // Redirect to threads page after creating
    }

    // Utility method to convert Date to LocalDateTime
    private LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    @GetMapping("/search")
    public String searchThreads(@RequestParam("query") String query, Model model) {
        // Check if query is empty or null, you can also handle this case if needed
        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("error", "Query cannot be empty");
            return "Thread/threads"; // Or redirect to a different page with an error message
        }

        List<Thread> threads = threadService.searchThreads(query); // Call the search service method
        model.addAttribute("threads", threads);
        model.addAttribute("query", query); // Add query to the model to display in the view

        return "Thread/threads"; // Show the search results
    }


}
