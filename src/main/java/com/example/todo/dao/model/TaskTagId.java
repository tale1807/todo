package com.example.todo.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagId implements Serializable {

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "tag_id")
    private UUID tagId;
}
