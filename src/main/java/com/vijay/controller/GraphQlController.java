package com.vijay.controller;

import com.vijay.entity.StudentEntity;
import com.vijay.repository.StudentRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphQlController {

    private final StudentRepository studentRepository;

    public GraphQlController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @QueryMapping
    public List<StudentEntity> getAllBooks() {
        return studentRepository.findAll();
    }

}
