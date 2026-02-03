package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentRequestDto;
import com.example.studentmanagementsystem.dto.StudentResponseDto;
import com.example.studentmanagementsystem.exception.StudentNotFoundException;
import com.example.studentmanagementsystem.model.StudentModel;
import com.example.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {

        this.repository = repository;
    }
    //create
//    public StudentModel addStudent(StudentModel student){
//
//        return repository.save(student);
//    }

    //add through dto
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel(); //obj for database(obj)
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student); //obj for response(client)

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }

//    public List<StudentModel> getAllStudents(){
//        return repository.findAll();
//    }
    public List<StudentResponseDto> getAllStudents(){
        List<StudentModel> students = repository.findAll();
        return students.stream()
                .map(student -> new StudentResponseDto(
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getEmail()
                ))
                .toList() ;
    }
//    public StudentModel updateStudent(String id,StudentModel student){
//        StudentModel  existingStudent = repository.findById(id).
//                orElseThrow(() -> new RuntimeException("No student found"));
//
//        existingStudent.setName(student.getName());
//        existingStudent.setAge(student.getAge());
//        existingStudent.setEmail(student.getEmail());
//
//        return repository.save(existingStudent);
//
//
//    }
    public StudentResponseDto updateStudent(String id, StudentRequestDto dto){
        StudentModel existingStudent = repository.findById(id).
                orElseThrow(()-> new StudentNotFoundException("Student not found"));
        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());

        StudentModel saved = repository.save(existingStudent);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );

    }
//    public void deleteStudent(String id){
//        StudentModel student  = repository.findById(id).
//                orElseThrow(() -> new RuntimeException("No student found"));
//        repository.delete(student);
//    }

    public void deleteStudent(String id){
        StudentModel student = repository.findById(id).
                orElseThrow(()-> new StudentNotFoundException("Student not found"));
        repository.delete(student);


    }

}
