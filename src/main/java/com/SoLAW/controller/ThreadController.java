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

import java.security.Principal;
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
    private CommentService commentService;
    @Autowired
    private ThreadService threadService;

    // Get all threads to display on the page
    @GetMapping
    public String getAllThreads(Model model) {
        List<Thread> threads = threadRepository.findAll();
        model.addAttribute("threads", threads);
        return "Thread/threads"; // Returns threads.html
    }

    // Get thread by id
    @GetMapping("/{id}")
    public String getThreadById(@PathVariable Long id, Model model) {
        Optional<Thread> thread = threadRepository.findById(id);
        if (thread.isPresent()) {
            Thread threadData = thread.get();
            model.addAttribute("thread", threadData);
            model.addAttribute("comments", commentService.getCommentsByThreadId(id));
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
    public String createThread(@ModelAttribute Thread thread, Principal principal) {
        thread.setAuthor(principal.getName()); // Mengatur nama pengguna yang login
        thread.setCreatedAt(LocalDateTime.now()); // Set waktu pembuatan thread
        threadRepository.save(thread);
        return "redirect:/threads"; // Redirect to threads page after creating
    }

    // Search threads based on query
    @GetMapping("/search")
    public String searchThreads(@RequestParam("query") String query, Model model) {
        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("error", "Query cannot be empty");
            return "Thread/threads";
        }

        List<Thread> threads = threadService.searchThreads(query);
        model.addAttribute("threads", threads);
        model.addAttribute("query", query);
        return "Thread/threads";
    }

    // Edit Thread - Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Thread> thread = threadRepository.findById(id);
        if (thread.isPresent()) {
            model.addAttribute("thread", thread.get());
            return "Thread/edit"; // Returns edit-thread.html
        }
        return "redirect:/threads";
    }

    // Edit Thread - Handle Edit Form Submission
    @PostMapping("/edit/{id}")
    public String handleEditThread(@PathVariable Long id, @ModelAttribute Thread thread) {
        thread.setId(id);  // Ensure the correct thread is updated
        thread.setCreatedAt(threadRepository.findById(id).get().getCreatedAt()); // Retain original creation date
        threadRepository.save(thread);
        return "redirect:/threads"; // Redirect to threads page after editing
    }

    // Delete Thread
    @GetMapping("/delete/{id}")
    public String deleteThread(@PathVariable Long id) {
        threadRepository.deleteById(id);
        return "redirect:/threads"; // Redirect to threads page after deleting
    }
}
