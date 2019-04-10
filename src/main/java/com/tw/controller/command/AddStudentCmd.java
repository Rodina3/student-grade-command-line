package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.core.Service;
import com.tw.core.model.Student;
import com.tw.controller.exception.InputErrorException;
import com.tw.view.AddStudentPage;

import java.util.Collections;

import static com.tw.controller.Status.ADD_STUDENT;
import static com.tw.controller.Status.HOME;

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
            response.setPage(AddStudentPage.SUCCESS_TEMPLATE);
            response.setStatus(HOME.toString());
            response.setInputRequired(false);

        } catch (InputErrorException ex) {
            response.setPage(AddStudentPage.ERROR_PROMPT);
            response.setStatus(ADD_STUDENT.toString());
            response.setInputRequired(true);
        }

        return response;
    }

    // TODO: 2019-04-10 ParseInput
    private Student parseStudent(String input) {
        if (input.equals("valid")) {
            return new Student("001", "Amie", Collections.emptyList());
        } else {
            throw new InputErrorException("Student info input error");
        }
    }

}
