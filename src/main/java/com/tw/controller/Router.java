package com.tw.controller;

import com.tw.controller.command.Command;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<Status, Command> router = new HashMap<>();

    public Response dispatch(Request request) {
        Command command = router.get(Status.valueOf(request.getCurrentStatus()));
        return command.exec(request);
    }

    public void register(Status status, Command command) {
        router.put(status, command);
    }

    public void remove(Status status) {
        router.remove(status);
    }

}
