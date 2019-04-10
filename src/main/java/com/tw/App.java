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

        Status status = HOME;
        boolean needInput = false;

        while (status != EXIT) {
            Request request = buildRequest(sc,status, needInput);
            Response response = getResponse(router, request);

            System.out.println(response.getPage());
            status = Status.valueOf(response.getStatus());
            needInput = response.isInputRequired();
        }
    }

    private static Response getResponse(Router router, Request request) {
        return router.dispatch(request);
    }

    private static Request buildRequest(Scanner sc, Status status, boolean needInput) {
        Request request = new Request();
        if (needInput) {
            String input = sc.nextLine();
            request.setInput(input);
        }
        request.setCurrentStatus(status.toString());
        return request;
    }

}
