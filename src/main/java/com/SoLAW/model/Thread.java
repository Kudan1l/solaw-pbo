package com.SoLAW.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String author;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor dan Getter/Setter sudah dihasilkan oleh Lombok
    // Tidak perlu menulis constructor manual karena @AllArgsConstructor sudah ada
}
