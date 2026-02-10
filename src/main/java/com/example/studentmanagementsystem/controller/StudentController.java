package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentRequestDto;
import com.example.studentmanagementsystem.dto.StudentResponseDto;
import com.example.studentmanagementsystem.model.StudentModel;
import com.example.studentmanagementsystem.service.StudentService;
import com.example.studentmanagementsystem.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {

        this.service = service;
        this.jwtUtil = jwtUtil;
    }
    //create api
    public void checkToken(String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    @PostMapping("/students")
    public StudentResponseDto addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);

        return service.addStudent(student);
    }

    //display
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(
            @RequestHeader(value = "Authorization",required = false ) String authHeader){
        checkToken(authHeader);
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
    @PatchMapping("/update/{id}")
    public StudentResponseDto patchStudent(

            @PathVariable String id,
            @RequestBody StudentRequestDto dto) {

        return service.patchStudent(id, dto);
    }

}
