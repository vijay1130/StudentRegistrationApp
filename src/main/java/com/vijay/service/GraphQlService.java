package com.vijay.service;

import com.vijay.entity.StudentEntity;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.Collection;

public interface GraphQlService {
     Collection<StudentEntity> getAllBooks();
     StudentEntity studentById(@Argument Long id);

}
