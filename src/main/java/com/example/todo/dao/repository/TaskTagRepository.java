package com.example.todo.dao.repository;

import com.example.todo.dao.model.TaskTag;
import com.example.todo.dao.model.TaskTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTagId> {
}
