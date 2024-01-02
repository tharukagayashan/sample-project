package com.projects.sampleproject.service;

import com.projects.sampleproject.dao.StudentDao;
import com.projects.sampleproject.dto.StudentCreateReqDto;
import com.projects.sampleproject.dto.StudentResDto;
import com.projects.sampleproject.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student createStudent(StudentCreateReqDto studentCreateReqDto) {

        Student student = new Student();
        student.setStudentName(studentCreateReqDto.getStudentName());
        student.setAge(studentCreateReqDto.getAge());
        student.setGrade(studentCreateReqDto.getGrade());

        return studentDao.save(student);
    }

    public List<Student> gelAllStudent() {
        return studentDao.findAll();
    }

    public Student getStudentById(Long studentId) {
        Optional<Student> optStudent = studentDao.findById(studentId);
        if (optStudent.isPresent()) {
            return optStudent.get();
        } else {

            throw new RuntimeException("Student not found for given ID");
        }
    }

    public Student updateStudent(Long studentId, Student student) {
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

    public String deleteStudent(Long studentId) {
        Optional<Student> optStudent = studentDao.findById(studentId);
        if (optStudent.isPresent()) {
            studentDao.deleteById(studentId);
            return "Student deleted";
        } else {
            throw new RuntimeException("Student not found for given ID");
        }
    }

    public List<StudentResDto> getAllCustomStudents() {
        List<StudentResDto> studentResList = new ArrayList<>();
        List<Student> studentList = studentDao.findAll();
        for (Student s : studentList) {
            StudentResDto studentResDto = new StudentResDto();
            studentResDto.setStudentName(s.getStudentName());
            studentResDto.setGrade(s.getGrade());
            studentResList.add(studentResDto);
        }
        return studentResList;
    }
}
