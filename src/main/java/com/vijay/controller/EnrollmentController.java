package com.vijay.controller;

import com.vijay.request.EnrollmentRequest;
import com.vijay.response.EnrollmentResponse;
import com.vijay.service.EnrollmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    private final Logger log = LoggerFactory.getLogger(EnrollmentController.class);

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * createEnrollment Api
     * create enrollment data for student and course
     *
     * @param enrollmentRequest this request object contains enrollment details such as studentId and courseId
     * @return EnrollmentResponse
     */
    @PostMapping("/create")
    public ResponseEntity<?> createEnrollment(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        log.info("Inside createEnrollment Api with payload {}", enrollmentRequest);
        EnrollmentResponse enrollment = enrollmentService.createEnrollment(enrollmentRequest);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    /**
     * getEnrollmentById APi for fetch enrollment Data by id
     *
     * @param id this request contains id
     * @return EnrollmentResponse
     */
    @GetMapping("/fetch")
    public ResponseEntity<?> getEnrollmentById(@RequestParam("id") Long id) {
        log.info("Inside getEnrollmentById Api with parameter {}", id);
        EnrollmentResponse enrollment = enrollmentService.getEnrollmentById(id);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }
}
