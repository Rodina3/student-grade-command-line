package com.tw.core.model;

import java.util.List;

public class Student {
    private String studentNumber;
    private String name;
    private List<Score> scores;
    private int totalScore;
    private double averageScore;

    public Student(String studentNumber, String name, List<Score> scores) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.scores = scores;
        this.totalScore = scores.stream().mapToInt(Score::getGrade).sum();
        this.averageScore = (double) totalScore / scores.size();
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public double getAverageScore() {
        return averageScore;
    }
}
