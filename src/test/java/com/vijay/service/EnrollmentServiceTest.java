package com.vijay.service;

import com.vijay.entity.Course;
import com.vijay.entity.Enrollment;
import com.vijay.entity.StudentEntity;
import com.vijay.repository.CourseRepository;
import com.vijay.repository.EnrollmentRepository;
import com.vijay.repository.StudentRepository;
import com.vijay.request.EnrollmentRequest;
import com.vijay.response.EnrollmentResponse;
import com.vijay.serviceImpl.EnrollmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    private Enrollment sampleEnrollment;
    private EnrollmentRequest sampleRequest;
    private EnrollmentResponse sampleResponse;

    @BeforeEach
    public void setup(){
        sampleEnrollment=Enrollment.builder()
                .id(1L)
                .enrollmentDate(LocalDateTime.now())
                .student(StudentEntity.builder()
                        .id(1L)
                        .email("vijayraj1130@gmail.com")
                        .firstName("vijay")
                        .lastName("kumar")
                        .gender("male")
                        .dateOfBirth("05-05-1995")
                        .createAt(LocalDateTime.now()).build())
                .course(Course.builder()
                        .id(1L)
                        .description("course Description")
                        .courseName("Java Basic")
                        .build())
                .build();

        sampleRequest= EnrollmentRequest.builder()
                .studentId(1L)
                .courseId(1L).build();

        sampleResponse=EnrollmentResponse.builder()
                .id(1L)
                .studentId(1L)
                .studentName("vijay")
                .courseId(1L)
                .courseName("Java Basics")
                .enrollmentDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void createEnrollment_test(){
        Mockito.when(enrollmentRepository.save(Mockito.any(Enrollment.class))).thenReturn(sampleEnrollment);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(StudentEntity.builder()
                .id(1L)
                .email("vijayraj1130@gmail.com")
                .firstName("vijay")
                .lastName("kumar")
                .gender("male")
                .dateOfBirth("05-05-1995")
                .createAt(LocalDateTime.now()).build()));
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(Course.builder()
                .id(1L).description("course description").courseName("Java Basics").build()));

        EnrollmentResponse enrollment= enrollmentService.createEnrollment(sampleRequest);
        Assertions.assertNotNull(enrollment);
        Assertions.assertEquals("vijay kumar", enrollment.getStudentName());
    }

}
