package com.example.todo.dao.repository;

import com.example.todo.dao.model.TaskTag;
import com.example.todo.dao.model.TaskTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTagId> {
}
