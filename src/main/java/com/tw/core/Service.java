package com.tw.core;

import com.tw.core.model.Report;
import com.tw.core.model.Student;
import com.tw.repo.StudentRepo;
import com.tw.repo.StudentRepoImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {

    private StudentRepo studentRepo = new StudentRepoImpl();

    public void addStudent(Student student) {
        studentRepo.save(student);
    }

    public Report getReport(List<String> studentNumbers) {
        List<Student> students = studentNumbers.stream()
                .map(this::getByStudentNumber)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new Report(students);
    }

    private Student getByStudentNumber(String sNumber) {
        Optional<Student> studentOpt = studentRepo.findByStudentNumber(sNumber);
        return studentOpt.orElse(null);
    }
}
