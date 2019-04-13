package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.AddStudentPage;
import com.tw.view.MenuPage;
import com.tw.view.PrintReportPage;

import static com.tw.controller.Status.ADD_STUDENT;
import static com.tw.controller.Status.EXIT;
import static com.tw.controller.Status.MENU;
import static com.tw.controller.Status.PRINT_REPORT;

public class SelectMenuCmd implements Command {
    @Override
    public Response exec(Request request) {
        Response response;

        switch (request.getInput()) {
            case "1":
                response = new Response(ADD_STUDENT.toString(), AddStudentPage.PROMPT, true);
                break;
            case "2":
                response = new Response(PRINT_REPORT.toString(), PrintReportPage.PROMPT, true);
                break;
            case "3":
                response = new Response(EXIT.toString(), "Exit", false);
                break;
            default:
                response = new Response(MENU.toString(), MenuPage.MENU, true);
                break;
        }
        return response;
    }
}
