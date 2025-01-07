package com.SoLAW.controller;

import com.SoLAW.model.ConsultationRequest;
import com.SoLAW.model.User;
import com.SoLAW.repository.UserRepository;
import com.SoLAW.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/consultations")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showConsultant() {
        return "consultant/consultation_detail";
    }

    @GetMapping("/history")
    public String showConsultantHistory() {
        return "consultant/consultation_history";
    }

    @GetMapping("/create")
    public String ConsultantCreate() {
        return "consultant/create_consultation";
    }

//    @PostMapping("/create")
//    public ConsultationRequest createRequest(@RequestBody ConsultationRequestDto requestDto) {
//        User user = fetchUserById(requestDto.getUserId());
//        return consultationService.createRequest(user, requestDto.getCaseDescription());
//    }

    @GetMapping("/user/{userId}")
    public List<ConsultationRequest> getUserRequests(@PathVariable Long userId) {
        User user = fetchUserById(userId);
        return consultationService.getUserRequests(user);
    }

    @PostMapping("/{requestId}/assign/{consultantId}")
    public void assignConsultant(@PathVariable Long requestId, @PathVariable Long consultantId) {
        consultationService.assignConsultant(requestId, consultantId);
    }

    private User fetchUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
