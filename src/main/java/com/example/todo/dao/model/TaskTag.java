package com.example.todo.dao.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_tags")
public class TaskTag {

    @Column(name = "task_tag_id")
    @EmbeddedId
    private TaskTagId id;

}
