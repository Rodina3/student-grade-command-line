package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.AddStudentPage;
import com.tw.view.PrintReportPage;
import com.tw.view.HomePage;

import static com.tw.controller.Status.*;
import static com.tw.controller.Status.EXIT;

public class SelectMenuCmd implements Command {
    @Override
    public Response exec(Request request) {
        Response response = new Response();

        switch (request.getInput()) {
            case "1":
                response.setStatus(ADD_STUDENT.toString());
                response.setPage(AddStudentPage.view);
                response.setInputRequired(true);
                break;
            case "2":
                response.setStatus(PRINT_REPORT.toString());
                response.setPage(PrintReportPage.view);
                response.setInputRequired(true);
                break;
            case "3":
                response.setStatus(EXIT.toString());
                response.setPage(null);
                response.setInputRequired(false);
                break;
            default:
                response.setStatus(HOME.toString());
                response.setPage(HomePage.view);
                response.setInputRequired(true);
                break;
        }
        return response;
    }
}
