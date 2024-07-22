package com.example.todo.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Table(name = "tasks")
public class Task {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL)
    private List<Reminder> reminders;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskTag> taskTags;



}
