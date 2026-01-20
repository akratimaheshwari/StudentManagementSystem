package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.model.StudentModel;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    //create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }
}
