package com.vijay.service;

import com.vijay.request.EnrollmentRequest;
import com.vijay.response.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest);

    EnrollmentResponse getEnrollmentById(Long id);
}
