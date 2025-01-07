package com.SoLAW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String expertise;

    @OneToMany(mappedBy = "consultant")
    @JsonIgnore
    private List<ConsultationRequest> consultationRequests;

    // Getters and Setters
}
