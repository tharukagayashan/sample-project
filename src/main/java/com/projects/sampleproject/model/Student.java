package com.projects.sampleproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "STUDENT_TBL")
public class Student {

    @Id
    @GeneratedValue(generator = "STUDENT_TBL", strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "STUDENT_NAME", length = 50)
    private String studentName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "GRADE", length = 10)
    private String grade;

}