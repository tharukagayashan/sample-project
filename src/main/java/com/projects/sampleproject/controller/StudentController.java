package com.projects.sampleproject.controller;

import com.projects.sampleproject.dto.StudentCreateReqDto;
import com.projects.sampleproject.dto.StudentResDto;
import com.projects.sampleproject.model.Student;
import com.projects.sampleproject.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentCreateReqDto studentCreateReqDto) {
        return studentService.createStudent(studentCreateReqDto);
    }

    @GetMapping
    public List<Student> gelAllStudent() {
        return studentService.gelAllStudent();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
       return studentService.updateStudent(studentId,student);
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/custom-students")
    public List<StudentResDto> getAllCustomStudents(){
        return studentService.getAllCustomStudents();
    }

}