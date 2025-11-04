package com.vijay.serviceImpl;

import com.vijay.entity.Address;
import com.vijay.entity.StudentEntity;
import com.vijay.exception.ResourceNotFoundException;
import com.vijay.repository.StudentRepository;
import com.vijay.request.StudentRequest;
import com.vijay.response.StudentResponse;
import com.vijay.security.JwtTokenProvider;
import com.vijay.service.EmailService;
import com.vijay.service.StudentService;
import com.vijay.utils.RollNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;
    private final TemplateService templateService;

    public StudentServiceImpl(StudentRepository studentRepository, JwtTokenProvider jwtTokenProvider, EmailService emailService, TemplateService templateService) {
        this.studentRepository = studentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.emailService = emailService;
        this.templateService = templateService;
    }

    /**
     * studentRegistration api this api used to register new  student
     *
     * @param studentRequest this request body firstName ,lastName and studentDetails information
     * @return StudentResponse
     */
    @Override
    public StudentResponse studentRegistration(StudentRequest studentRequest) throws Exception {
        LOGGER.info("Inside studentRegistration API with request Object {}",studentRequest);
        Optional<StudentEntity> existStudent = studentRepository.findByEmail(studentRequest.getEmail());
        if (existStudent.isPresent()) {
            LOGGER.error("Student already Exist in DB: {} " , studentRequest.getEmail());
            throw new ResourceNotFoundException("Student already Exist in DB:- " + studentRequest.getEmail());
        }
        String token = jwtTokenProvider.generateToken(studentRequest.getEmail());
        if (token==null){
            LOGGER.error("token is null: {} " , token);
            throw new ResourceNotFoundException("token is null:- " + token);
        }
        StudentEntity student = new StudentEntity();
        student.setRollNo(RollNumberGenerator.generateRollNumber(studentRequest.getFirstName()));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setGender(studentRequest.getGender());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setCreateAt(LocalDateTime.now());
        Address address = new Address();
        address.setCity(studentRequest.getAddress().getCity());
        address.setCountry(studentRequest.getAddress().getCountry());
        address.setState(studentRequest.getAddress().getState());
        address.setStreet(studentRequest.getAddress().getStreet());
        address.setPostalCode(studentRequest.getAddress().getPostalCode());
        address.setCreateAt(LocalDateTime.now());
        student.setAddress(address);
        StudentEntity save = studentRepository.save(student);
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setAccessToken(token);
        BeanUtils.copyProperties(save, studentResponse);
        String generated= templateService.generateRegistrationHtml(save);
        // call email service to send mail to email
        emailService.sendHtmlEmail(studentRequest.getEmail(),"Student Registration",generated);
        return studentResponse;
    }

    /**
     * this api is get Student data by email
     *
     * @param username this request contains such as email
     * @return StudentEntity
     */
    @Override
    public StudentEntity findByEmail(String username) {
        Optional<StudentEntity> existingStudent = studentRepository.findByEmail(username);
        if (existingStudent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("user Not found by email %s", username));
        }
        return existingStudent.get();
    }

    /**
     * fetchStudentByEmail Api is fetch student data by email
     *
     * @param email this request contains such as email
     * @return StudentResponse
     */
    @Override
    public StudentResponse fetchStudentByEmail(String email) {
        StudentEntity student = studentRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(String.format("Student Not Found by email %s ", email)));
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setAccessToken(jwtTokenProvider.generateToken(student.getEmail()));
        BeanUtils.copyProperties(student, studentResponse);
        logic();
        return studentResponse;
    }

    public void logic() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        List<Integer> list = numbers.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        System.out.println(list);

    }

}
