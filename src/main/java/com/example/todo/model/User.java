package com.example.todo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NonNull
    private String email;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    @Column(name = "created_at")
    @NonNull
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @NonNull
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @NonNull
    private List<Task> tasks;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
