package com.vijay.controller;

import com.vijay.request.CourseRequest;
import com.vijay.response.CourseResponse;
import com.vijay.response.DeleteResponse;
import com.vijay.service.CourseService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/api")
public class CourseController {
    private final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * this api to crate course createCourse APi
     *
     * @param courseRequest the request object containing course details such as courseName, and user information
     * @return courseResponse
     */
    @PostMapping("/create")
    // @PreAuthorize("hasRole('ROLE_RHC_ADMIN')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        log.info("Inside createCourse Api with request payload {}", courseRequest);
        CourseResponse courseResponse = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    /**
     * fetchCourseByCourseId method use to fetch course details by course_id
     *
     * @param courseId the request to send courseId
     * @return courseResponse
     */
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchCourseByCourseId(@RequestParam("courseId") Long courseId) {
        log.info("Inside fetchCourseByCourseId Api with requestParam {}", courseId);
        CourseResponse courseResponse = courseService.fetchCourseByCourseId(courseId);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }


    /**
     * Processes the course request and returns the result.
     *
     * @param courseRequest the request object containing course details such as course ID, name, and user information
     * @return the result of the course processing
     */
    @PutMapping("/update")
    // @PreAuthorize("hasRole('ROLE_RHC_ADMIN')")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequest courseRequest, @RequestParam("courseId") Long courseId) {
        log.info("Inside updateCourse Api with request payload {}", courseRequest);
        CourseResponse courseResponse = courseService.updateCourse(courseRequest, courseId);
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCourseById(@RequestParam("id") Long id) {
        log.info("Inside deleteCourseById method with param {}", id);
        DeleteResponse deleteResponse = courseService.deleteCourseById(id);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }
}
