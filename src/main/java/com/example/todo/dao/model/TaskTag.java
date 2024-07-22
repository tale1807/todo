package com.example.todo.dao.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Table(name = "task_tags")
public class TaskTag implements Serializable {

    @EmbeddedId
    private TaskTagId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
