package com.tw.core.model;

import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private List<Student> students;
    private double average;
    private double median;

    public Report(List<Student> students) {
        this.students = students;
        this.average = calculateAverage(students);
        this.median = calculateMedian();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public double getAverage() {
        return average;
    }

    public double getMedian() {
        return median;
    }

    private double calculateAverage(List<Student> students) {
        return students.stream().mapToDouble(Student::getTotalScore).sum() / students.size();
    }

    private double calculateMedian() {
        List<Integer> totalScoresSorted = students.stream().map(Student::getTotalScore).sorted().collect(Collectors.toList());
        int size = totalScoresSorted.size();

        if (size % 2 == 0) {
            return (totalScoresSorted.get(size / 2 - 1) + totalScoresSorted.get(size / 2)) / 2;
        } else {
            return totalScoresSorted.get((size - 1) / 2);
        }

    }
}
