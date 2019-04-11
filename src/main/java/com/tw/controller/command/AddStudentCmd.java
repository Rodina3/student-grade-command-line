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
import java.util.stream.Collectors;

import static com.tw.controller.Status.ADD_STUDENT;
import static com.tw.controller.Status.HOME;
import static java.lang.String.format;

public class AddStudentCmd implements Command {

    private Service service = new Service();

    @Override
    public Response exec(Request request) {
        return addStudent(request.getInput());
    }


    public Response addStudent(String input) {
        Response response = new Response();
        try {
            Student student = parseStudent(input);
            service.addStudent(student);
            response.setPage(format(AddStudentPage.SUCCESS_TEMPLATE, student.getName()));
            response.setStatus(HOME.toString());
            response.setInputRequired(false);

        } catch (InputErrorException ex) {
            response.setPage(AddStudentPage.ERROR_PROMPT);
            response.setStatus(ADD_STUDENT.toString());
            response.setInputRequired(true);
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
                    .map(s -> {
                        String[] split = s.split(": ");
                        return new Score(split[0], Integer.parseInt(split[1]));
                    }).collect(Collectors.toList());

            student.setScores(scores);
            return student;
        } catch (Exception ex) {
            throw new InputErrorException("Student info input error");
        }
    }

}
