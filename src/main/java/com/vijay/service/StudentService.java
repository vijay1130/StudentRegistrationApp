package com.vijay.service;

import com.vijay.entity.StudentEntity;
import com.vijay.request.StudentRequest;
import com.vijay.response.StudentResponse;

public interface StudentService {

    StudentResponse studentRegistration(StudentRequest studentRequest) throws Exception;

    StudentEntity findByEmail(String username);

    StudentResponse fetchStudentByEmail(String email);
}
