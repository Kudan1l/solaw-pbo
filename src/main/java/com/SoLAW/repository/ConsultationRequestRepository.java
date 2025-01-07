package com.SoLAW.repository;

import com.SoLAW.model.ConsultationRequest;
import com.SoLAW.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRequestRepository extends JpaRepository<ConsultationRequest, Long> {
    List<ConsultationRequest> findByUser(User user);
}