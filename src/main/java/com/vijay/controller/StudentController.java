package com.vijay.controller;

import com.vijay.request.StudentRequest;
import com.vijay.response.StudentResponse;
import com.vijay.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * studentRegistration API to create Student with address
     *
     * @param studentRequest this request body contains such as firstName,lastName and dateOfBirth studentInformation
     * @return StudentResponse
     */
    @PostMapping("/create")
    public ResponseEntity<?> studentRegistration(@RequestBody StudentRequest studentRequest) throws Exception {
        log.info("Inside studentRegistration Api with request payload is {}", studentRequest);
        StudentResponse createStudent = studentService.studentRegistration(studentRequest);
        return new ResponseEntity<>(createStudent, HttpStatus.OK);
    }

    /**
     * fetchStudentByEmail Api get Student by email
     *
     * @param email this request contains such as email parameter
     * @return StudentResponse
     */
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchStudentByEmail(@Valid @RequestParam(value = "email") String email) {
        log.info("Inside fetchStudentByEmail Api with requestParam {}", email);
        StudentResponse studentResponse = studentService.fetchStudentByEmail(email);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

}
