package com.vijay.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {

    @NotBlank(message = "courseName must not be null or empty")
    private String courseName;

    @NotBlank(message = "description must not be null or empty")
    private String description;
}
