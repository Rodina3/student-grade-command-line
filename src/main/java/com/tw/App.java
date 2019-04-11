package com.tw;

import com.tw.controller.Router;
import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;

import java.util.Scanner;

import static com.tw.controller.Status.EXIT;
import static com.tw.controller.Status.HOME;

public class App {

    public static void main(String[] args) {
        Router router = new Router();
        Scanner sc = new Scanner(System.in);

        Status currentStatus = HOME;
        boolean isInputRequired = false;

        while (currentStatus != EXIT) {
            Request request = buildRequest(sc, currentStatus, isInputRequired);
            Response response = getResponse(router, request);

            System.out.println(response.getPage());
            currentStatus = Status.valueOf(response.getStatus());
            isInputRequired = response.isInputRequired();
        }
    }

    private static Response getResponse(Router router, Request request) {
        return router.dispatch(request);
    }

    private static Request buildRequest(Scanner sc, Status currentStatus, boolean isInputRequired) {
        Request request = new Request();
        if (isInputRequired) {
            String input = sc.nextLine();
            request.setInput(input);
        }
        request.setCurrentStatus(currentStatus.toString());
        return request;
    }

}
