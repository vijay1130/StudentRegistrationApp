package com.vijay.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnrollmentRequest {

    @NotNull(message = "studentId not be null ")
    private Long studentId;

    @NotNull(message = "courseId  not be null ")
    private Long courseId;
}

