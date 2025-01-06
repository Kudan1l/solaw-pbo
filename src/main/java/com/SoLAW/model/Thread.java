package com.SoLAW.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    // One-to-many relationship with Comment, with cascading delete
    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // Constructor and Getter/Setter are generated by Lombok
    // No need to manually write constructors as @AllArgsConstructor is already present
}
