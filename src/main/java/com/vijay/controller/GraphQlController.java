package com.vijay.controller;

import com.vijay.entity.StudentEntity;
import com.vijay.repository.StudentRepository;
import com.vijay.service.GraphQlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class GraphQlController {
    Logger log = LoggerFactory.getLogger(GraphQlController.class);

    private final GraphQlService graphQlService;

    public GraphQlController(GraphQlService graphQlService) {
        this.graphQlService = graphQlService;
    }

    @QueryMapping(name = "allStudent")
    public Collection<StudentEntity> getAllBooks() {
        log.info("Inside getAllBooks with No Argument: ");
        return  graphQlService.getAllBooks();

    }

    @QueryMapping(name = "studentById")
    public StudentEntity studentById(@Argument Long id) {
        log.info("Inside studentById with parameter id:{}",id);
       return graphQlService.studentById(id);
    }

}
