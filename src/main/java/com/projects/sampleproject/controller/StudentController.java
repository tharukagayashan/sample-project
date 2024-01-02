package com.projects.sampleproject.controller;

import com.projects.sampleproject.dao.StudentDao;
import com.projects.sampleproject.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentDao studentDao;

    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {

        return studentDao.save(student);
    }

    @GetMapping
    public List<Student> gelAllStudent() {

        return studentDao.findAll();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        Optional<Student> optStudent = studentDao.findById(studentId);
        if (optStudent.isPresent()) {
            return optStudent.get();
        } else {
            throw new RuntimeException("Student not found for given ID");
        }
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        if (studentId == null) {
            throw new RuntimeException("Student ID null");
        } else if (studentId != student.getStudentId()) {
            throw new RuntimeException("Student ID mismatch");
        } else {

            Optional<Student> optStudent = studentDao.findById(studentId);
            if (optStudent.isPresent()) {

                Student st = optStudent.get();
                st.setStudentName(student.getStudentName());
                st.setAge(student.getAge());
                st.setGrade(student.getGrade());

                return studentDao.save(st);

            } else {
                throw new RuntimeException("Student not found for given ID");
            }

        }
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        Optional<Student> optStudent = studentDao.findById(studentId);
        if (optStudent.isPresent()) {
            studentDao.deleteById(studentId);
            return "Student deleted";
        } else {
            throw new RuntimeException("Student not found for given ID");
        }
    }

}