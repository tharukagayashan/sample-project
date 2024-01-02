package com.projects.sampleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCreateReqDto {
    @NotNull(message = "student name cannot be null")
    @NotEmpty(message = "student name cannot be empty")
    @NotBlank(message = "student name cannot be blank")
    private String studentName;
    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 25, message = "Maximum age is 25")
    @NotNull(message = "Age cannot be null")
    private Integer age;
    private String grade;
}
