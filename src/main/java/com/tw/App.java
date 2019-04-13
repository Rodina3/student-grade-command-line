package com.tw;

import com.tw.controller.Router;
import com.tw.controller.Status;
import com.tw.controller.command.AddStudentCmd;
import com.tw.controller.command.HomeCmd;
import com.tw.controller.command.PrintReportCmd;
import com.tw.controller.command.SelectMenuCmd;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;

import java.util.Scanner;

import static com.tw.controller.Status.EXIT;
import static com.tw.controller.Status.HOME;

public class App {
    private static Router router;
    private static Status currentStatus;
    private static boolean isInputRequired;
    private static Scanner scanner;

    public static void main(String[] args) {
        init();
        process();
    }

    private static void init() {
        router = new Router();
        router.register(Status.HOME, new HomeCmd());
        router.register(Status.MENU, new SelectMenuCmd());
        router.register(Status.ADD_STUDENT, new AddStudentCmd());
        router.register(Status.PRINT_REPORT, new PrintReportCmd());

        currentStatus = HOME;
        isInputRequired = false;
        scanner = new Scanner(System.in);
    }

    private static void process() {
        while (currentStatus != EXIT) {
            Request request = buildRequest();
            Response response = getResponse(request);

            System.out.println(response.getPage());
            currentStatus = Status.valueOf(response.getStatus());
            isInputRequired = response.isInputRequired();
        }
    }

    private static Response getResponse(Request request) {
        return router.dispatch(request);
    }

    private static Request buildRequest() {
        Request request = new Request();
        if (isInputRequired) {
            request.setInput(scanner.nextLine());
        }
        request.setCurrentStatus(currentStatus.toString());
        return request;
    }

}
