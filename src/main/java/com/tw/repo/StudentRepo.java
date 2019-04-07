package com.tw.repo;

import com.tw.core.model.Student;

import java.util.Optional;

public interface StudentRepo {

    void save(Student student);

    Optional<Student> findByStudentNumber(String studentNumber);

}
