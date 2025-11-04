package com.vijay.serviceImpl;

import com.vijay.entity.Course;
import com.vijay.exception.ResourceNotFoundException;
import com.vijay.repository.CourseRepository;
import com.vijay.request.CourseRequest;
import com.vijay.response.CourseResponse;
import com.vijay.response.DeleteResponse;
import com.vijay.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**
     * createCourse Api this api is used to create new course record
     *
     * @param courseRequest this request body contains such as courseName and courseDescription
     * @return CourseResponse
     */
    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        log.info("Inside createCourse Api with request payload {}", courseRequest);
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        Course saveCourse = courseRepository.save(course);
        return CourseResponse.builder().id(saveCourse.getId()).courseName(saveCourse.getCourseName()).description(saveCourse.getDescription()).build();
    }

    /**
     * fetchCourseByCourseId Api is used to fetch course details by courseId
     *
     * @param courseId this request contains such as courseId
     * @return courseResponse
     */
    @Override
    public CourseResponse fetchCourseByCourseId(Long courseId) {
        log.info("Inside fetchCourseByCourseId Api with request courseId {}", courseId);

        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            log.error("Course not found for courseId: {}", courseId);
            return new ResourceNotFoundException(String.format("Course not found by courseId %s", courseId));
        });
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(course, courseResponse);
        return courseResponse;
    }

    /**
     * updateCourse Api to used update course details
     *
     * @param courseRequest this request object contains such as courseName and description
     * @param courseId      this request contains such as courseId
     * @return courseResponse
     */
    @Override
    public CourseResponse updateCourse(CourseRequest courseRequest, Long courseId) {
        log.info("Inside updateCourse Api with requestObject courseRequest {} request {}", courseRequest,courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() ->{
            log.error("Course not found by courseId {}", courseId);
          return new ResourceNotFoundException(String.format("Course not found by courseId %s", courseId));
        });
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        Course updateCourse = courseRepository.save(course);
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(updateCourse, courseResponse);
        return courseResponse;
    }

    @Override
    public DeleteResponse deleteCourseById(Long id) {
        log.info("Inside deleteCourseById serviceImpl method with param {}",id);
        Course course= courseRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Course Not Found with id: "+id));
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setStatus(String.valueOf(HttpStatus.OK));
        deleteResponse.setMessage("Successfully deleted course with id: "+course.getId());
        return deleteResponse;
    }


}
