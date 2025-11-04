package com.vijay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {

    /**
     * student_id field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    /**
     * roll_number filed
     */
    @Column(name = "roll_number")
    private String rollNo;
    /**
     * first_name field
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * last_name filed
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * date_of_birth field
     */
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    /**
     * gender filed
     */
    @Column(name = "gender")
    private String gender;
    /**
     * email field
     */
    @Column(name = "email")
    @Email( regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Invalid email format")
    private String email;
    /**
     * phone_number
     */
    @Column(name = "phone_number")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;
    /**
     * address field
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    /**
     * enrollments field
     */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();
    /**
     * create_at field
     */
    @Column(name = "create_at")
    private LocalDateTime createAt;
    /**
     * updated_at filed
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    /**
     * deleted_at
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
