package com.vijay.serviceImpl;

import com.vijay.entity.Course;
import com.vijay.entity.Enrollment;
import com.vijay.entity.StudentEntity;
import com.vijay.exception.ResourceNotFoundException;
import com.vijay.repository.CourseRepository;
import com.vijay.repository.EnrollmentRepository;
import com.vijay.repository.StudentRepository;
import com.vijay.request.EnrollmentRequest;
import com.vijay.response.EnrollmentResponse;
import com.vijay.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final Logger log = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * createEnrollment Api is used to create new record of enrollment
     *
     * @param enrollmentRequest this request body contains such as studentId and courseId
     * @return EnrollmentResponse
     */
    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        log.info("Inside createEnrollment with request payload {}", enrollmentRequest);
        LocalDateTime currentDateTime = LocalDateTime.now();
        StudentEntity existStudent = studentRepository.findById(enrollmentRequest.getStudentId()).orElseThrow(() -> new ResourceNotFoundException("Student Not Found with studentId " + enrollmentRequest.getStudentId()));
        Course existCourse = courseRepository.findById(enrollmentRequest.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course Not Found with courseId " + enrollmentRequest.getCourseId()));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(existStudent);
        enrollment.setCourse(existCourse);
        enrollment.setEnrollmentDate(currentDateTime);
        Enrollment saveEnroll = enrollmentRepository.save(enrollment);

        return EnrollmentResponse.builder()
                .id(saveEnroll.getId())
                .courseName(existCourse.getCourseName())
                .studentName(existStudent.getFirstName() + " " + existStudent.getLastName())
                .courseId(existCourse.getId())
                .studentId(existStudent.getId())
                .enrollmentDate(saveEnroll.getEnrollmentDate())
                .build();
    }

    /**
     * getEnrollmentById api for fetch enrollment by id
     *
     * @param id this request contains such as id
     * @return EnrollmentResponse
     */
    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {
        log.info("Inside getEnrollmentById with parameter id {}", id);
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> {
            log.error("Enrollment not found with enrollmentId {}", id);
          return new ResourceNotFoundException(String.format("Enrollment not found with enrollmentId %s", id));
        });
        StudentEntity existStudent = studentRepository.findById(enrollment.getStudent().getId()).orElseThrow(() -> {
            log.error("Student Not Found with studentId {}" ,enrollment.getStudent().getId());
           return new ResourceNotFoundException("Student Not Found with studentId " + enrollment.getStudent().getId());
        });
        Course existCourse = courseRepository.findById(enrollment.getCourse().getId()).orElseThrow(() -> {
            log.error("Course Not Found with courseId {} ", enrollment.getCourse().getId());
            return new ResourceNotFoundException("Course Not Found with courseId " + enrollment.getCourse().getId());
        });
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .courseName(existCourse.getCourseName())
                .courseId(existCourse.getId())
                .studentName(existStudent.getFirstName())
                .studentId(existStudent.getId())
                .build();
    }


}
