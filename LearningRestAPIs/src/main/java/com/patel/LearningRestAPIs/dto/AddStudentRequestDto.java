package com.patel.LearningRestAPIs.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data

public class AddStudentRequestDto {
    @NotBlank(message = "name is required")
    private String name;
    @Email
    @NotBlank(message = "Email is Required")
    private String email;
}
