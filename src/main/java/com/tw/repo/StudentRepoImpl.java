package com.tw.repo;

import com.tw.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepoImpl implements StudentRepo {

    private List<Student> students = new ArrayList<>();


    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public Optional<Student> findByStudentNumber(String studentNumber) {
        return students.stream()
                .filter(s -> s.getStudentNumber().equals(studentNumber))
                .findFirst();
    }

}
