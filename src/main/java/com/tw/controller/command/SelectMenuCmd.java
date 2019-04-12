package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.AddStudentPage;
import com.tw.view.PrintReportPage;
import com.tw.view.MenuPage;

import static com.tw.controller.Status.ADD_STUDENT;
import static com.tw.controller.Status.EXIT;
import static com.tw.controller.Status.MENU;
import static com.tw.controller.Status.PRINT_REPORT;

public class SelectMenuCmd implements Command {
    @Override
    public Response exec(Request request) {
        Response response = new Response();

        switch (request.getInput()) {
            case "1":
                response.setStatus(ADD_STUDENT.toString());
                response.setPage(AddStudentPage.PROMPT);
                response.setInputRequired(true);
                break;
            case "2":
                response.setStatus(PRINT_REPORT.toString());
                response.setPage(PrintReportPage.PROMPT);
                response.setInputRequired(true);
                break;
            case "3":
                response.setStatus(EXIT.toString());
                response.setPage("Exit");
                response.setInputRequired(false);
                break;
            default:
                response.setStatus(MENU.toString());
                response.setPage(MenuPage.MENU);
                response.setInputRequired(true);
                break;
        }
        return response;
    }
}
