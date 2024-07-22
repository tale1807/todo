package com.example.todo.dao.repository;

import com.example.todo.dao.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
}
