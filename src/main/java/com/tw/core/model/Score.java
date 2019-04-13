package com.tw.core.model;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private String subject;
    private int grade;

    public static String convertSubjectToEnglish(String subject) {
        Map<String, String> subjectMap = new HashMap<>();
        subjectMap.put("语文", "chinese");
        subjectMap.put("数学", "math");
        subjectMap.put("英语", "english");
        subjectMap.put("编程", "programming");
        return subjectMap.get(subject);
    }

    public Score(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getSubject() {
        return subject;
    }
}
