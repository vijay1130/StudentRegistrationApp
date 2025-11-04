package com.vijay.service;

import com.vijay.request.CourseRequest;
import com.vijay.response.CourseResponse;
import com.vijay.response.DeleteResponse;
import jakarta.validation.Valid;

public interface CourseService {

    CourseResponse createCourse(CourseRequest courseRequest);

    CourseResponse fetchCourseByCourseId(Long courseId);

    CourseResponse updateCourse(@Valid CourseRequest courseRequest, Long courseId);

    DeleteResponse deleteCourseById(Long id);
}
