package com.example.studentmanagementsystem.dto;

public record StudentResponseDto(
        String id,
        String name,
        Integer age,
        String email
) {}
