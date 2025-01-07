package com.SoLAW.service;

import com.SoLAW.model.Consultant;
import com.SoLAW.model.ConsultationRequest;
import com.SoLAW.model.User;
import com.SoLAW.repository.ConsultantRepository;
import com.SoLAW.repository.ConsultationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRequestRepository consultationRequestRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    public ConsultationRequest createRequest(User user, String caseDescription) {
        if (user == null || caseDescription == null || caseDescription.isEmpty()) {
            throw new IllegalArgumentException("User or case description cannot be null");
        }
        ConsultationRequest request = new ConsultationRequest();
        request.setUser(user);
        request.setCaseDescription(caseDescription);
        return consultationRequestRepository.save(request);
    }

    public List<ConsultationRequest> getUserRequests(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return consultationRequestRepository.findByUser(user);
    }

    public void assignConsultant(Long requestId, Long consultantId) {
        Optional<ConsultationRequest> requestOpt = consultationRequestRepository.findById(requestId);
        Optional<Consultant> consultantOpt = consultantRepository.findById(consultantId);

        if (requestOpt.isEmpty() || consultantOpt.isEmpty()) {
            throw new IllegalArgumentException("Request or consultant not found");
        }

        ConsultationRequest request = requestOpt.get();
        Consultant consultant = consultantOpt.get();

        request.setConsultant(consultant);
        request.setStatus("Assigned");
        consultationRequestRepository.save(request);
    }
}