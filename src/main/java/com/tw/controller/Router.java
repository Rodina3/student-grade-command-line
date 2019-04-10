package com.tw.controller;

import com.tw.controller.command.AddStudentCmd;
import com.tw.controller.command.Command;
import com.tw.controller.command.HomeCmd;
import com.tw.controller.command.PrintReportCmd;
import com.tw.controller.command.SelectMenuCmd;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<Status, Command> router = new HashMap<>();

    public Router() {
        router.put(Status.HOME, new HomeCmd());
        router.put(Status.MENU, new SelectMenuCmd());
        router.put(Status.ADD_STUDENT, new AddStudentCmd());
        router.put(Status.PRINT_REPORT, new PrintReportCmd());
    }

    public Response dispatch(Request request) {
        Command command = router.get(Status.valueOf(request.getCurrentStatus()));
        return command.exec(request);
    }

}
