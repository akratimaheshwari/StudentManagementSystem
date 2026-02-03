package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentRequestDto;
import com.example.studentmanagementsystem.dto.StudentResponseDto;
import com.example.studentmanagementsystem.model.StudentModel;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }
    //create api

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto student){

        return service.addStudent(student);
    }

    //display
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(){
        return service.getAllStudents();
    }
    //update
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(
            @PathVariable String id,
            @RequestBody StudentRequestDto student){
        return service.updateStudent(id,student);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "student deleted succesfully";
    }
}
