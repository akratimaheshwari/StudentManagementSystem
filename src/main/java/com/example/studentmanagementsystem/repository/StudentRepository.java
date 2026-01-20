package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository <StudentModel, String> {

}
