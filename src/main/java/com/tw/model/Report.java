package com.tw.model;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Student> students = new ArrayList<>();
    private float average;
    private float median;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getMedian() {
        return median;
    }

    public void setMedian(float median) {
        this.median = median;
    }
}
