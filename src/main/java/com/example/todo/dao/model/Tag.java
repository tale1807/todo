package com.example.todo.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Table(name = "tags")
public class Tag {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<TaskTag> taskTags;

}
