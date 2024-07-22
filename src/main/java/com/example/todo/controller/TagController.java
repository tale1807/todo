package com.example.todo.controller;

import com.example.todo.dao.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class TagController {

    private final TagService tagService;
}
