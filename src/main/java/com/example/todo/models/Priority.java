package com.example.todo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Priority {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;
}
