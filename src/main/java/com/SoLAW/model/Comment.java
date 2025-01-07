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
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = false)
    private Thread thread;

    private String author;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor dan Getter/Setter sudah dihasilkan oleh Lombok
}
