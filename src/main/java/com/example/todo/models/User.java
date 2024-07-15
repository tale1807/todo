package com.example.todo.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public User() {
    }

    public User(String name, String email, String password,
                List<Task> tasks) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "UserModel{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               ", tasks=" + tasks +
               '}';
    }
}
