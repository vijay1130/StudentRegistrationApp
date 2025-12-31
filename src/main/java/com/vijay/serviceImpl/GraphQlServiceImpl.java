package com.vijay.serviceImpl;

import com.vijay.entity.StudentEntity;
import com.vijay.repository.StudentRepository;
import com.vijay.service.GraphQlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GraphQlServiceImpl implements GraphQlService {
    Logger logger = LoggerFactory.getLogger(GraphQlServiceImpl.class);

    private final StudentRepository studentRepository;

    public GraphQlServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<StudentEntity> getAllBooks() {
        logger.info("Inside getAllBooks with no Param");
        List<StudentEntity> all= studentRepository.findAll();
        return all;
    }

    @Override
    public StudentEntity studentById(Long id) {
        logger.info("Inside studentById with parameter id: {}",id);
        Optional<StudentEntity> student= studentRepository.findById(id);
        return student.get();
    }
}
