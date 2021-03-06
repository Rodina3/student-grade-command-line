package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.controller.exception.InputErrorException;
import com.tw.core.Service;
import com.tw.core.model.Score;
import com.tw.core.model.Student;
import com.tw.view.AddStudentPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.tw.controller.Status.ADD_STUDENT;
import static com.tw.controller.Status.HOME;
import static java.lang.String.format;

public class AddStudentCmd implements Command {

    private Service service = Service.getInstance();

    @Override
    public Response exec(Request request) {
        return addStudent(request.getInput());
    }


    public Response addStudent(String input) {
        Response response;
        try {
            Student student = parseStudent(input);
            service.addStudent(student);
            response = new Response(HOME.toString(), format(AddStudentPage.SUCCESS_TEMPLATE, student.getName()), false);

        } catch (InputErrorException ex) {
            response = new Response(ADD_STUDENT.toString(), AddStudentPage.ERROR_PROMPT, true);
        }

        return response;
    }

    private Student parseStudent(String input) {
        try {
            List<String> studentInfo = new ArrayList<>(Arrays.asList(input.split(", ")));
            Student student = new Student();
            student.setName(studentInfo.get(0));
            student.setStudentNumber(studentInfo.get(1));
            studentInfo.remove(0);
            studentInfo.remove(0);

            List<Score> scores = studentInfo.stream()
                    .map(this::getScore)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            student.setScores(scores);
            return student;
        } catch (Exception ex) {
            throw new InputErrorException("Student info input error");
        }
    }

    private Score getScore(String str) {
        Score score;
        String[] split = str.split(": ");
        String subject = Score.convertSubjectToEnglish(split[0]);
        if (subject.isEmpty()) {
            score = null;
        } else {
            score = new Score(subject, Integer.parseInt(split[1]));
        }
        return score;
    }

}
