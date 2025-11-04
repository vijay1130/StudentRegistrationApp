package com.vijay.service;

import com.vijay.entity.Course;
import com.vijay.repository.CourseRepository;
import com.vijay.request.CourseRequest;
import com.vijay.response.CourseResponse;
import com.vijay.serviceImpl.CourseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseServiceImpl courseService;

    private Course sampleCourse;
    private CourseRequest sampleCourseRequest;
    private CourseResponse sampleResponse;

    @BeforeEach
    public void setup(){
        sampleCourse = Course.builder()
                .id(1L)
                .courseName("Java Basics")
                .description("Introduction to Java")
                .build();
        sampleCourseRequest = CourseRequest.builder()
                .courseName("Java Basics")
                .description("Introduction to Java")
                .build();
        sampleResponse= CourseResponse.builder()
                .courseName("Java Basics")
                .description("Introduction to Java")
                .id(1L).build();

    }

    @Test
    public void testSaveCourse_Positive(){
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(sampleCourse);
        CourseResponse course= courseService.createCourse(sampleCourseRequest);
        Assertions.assertNotNull(course);
        Assertions.assertEquals("Java Basics", course.getCourseName());
    }

    @Test
    public void testSaveCourse_Negative_SaveThrowsException() {
        Mockito.when(courseRepository.save(Mockito.any(Course.class)))
                .thenThrow(new RuntimeException("Database error"));

        Assertions.assertThrows(RuntimeException.class, () -> {
            courseService.createCourse(sampleCourseRequest);
        }, "Expected createCourse to throw exception when save fails");
    }

    @Test
    public void fetchCourseByCourseId_test(){
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(sampleCourse));
        CourseResponse courseResponse= courseService.fetchCourseByCourseId(1L);
        Assertions.assertNotNull(courseResponse);
        Assertions.assertEquals("Java Basics",courseResponse.getCourseName());
    }

    @Test
    public void fetchCourseByCourseId_Negative_FetchThrowsException(){
        Mockito.when(courseRepository.findById(1L)).thenThrow(new RuntimeException("DataBase Error"));
        Assertions.assertThrows(RuntimeException.class,()->{
            courseService.fetchCourseByCourseId(5L);
        },"Expected fetchCourseByCourseId to throw exception when fetch fails");
    }






}
