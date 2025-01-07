package com.SoLAW.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class ConsultationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 500)
    private String caseDescription;

    private String status = "Pending";

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    private Consultant consultant;

    // Getters and Setters
}
