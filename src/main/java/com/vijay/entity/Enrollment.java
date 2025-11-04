package com.vijay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {

    /**
     * enrollment_id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    /**
     * student_id field
     */
    @ManyToOne @JoinColumn(name = "student_id")
    private StudentEntity student;
    /**
     * course_id field
     */
    @ManyToOne @JoinColumn(name = "course_id")
    private Course course;
    /**
     * enrollment_date field
     */
    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;
}
