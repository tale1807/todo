package com.example.todo.dao.service;

import com.example.todo.dao.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class TagService {
    private final TagRepository tagRepository;
}
