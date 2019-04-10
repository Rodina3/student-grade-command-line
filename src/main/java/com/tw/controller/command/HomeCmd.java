package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.MenuPage;

public class HomeCmd implements Command {
    @Override
    public Response exec(Request request) {
        Response response = new Response();
        response.setStatus(Status.MENU.toString());
        response.setPage(MenuPage.MENU);
        response.setInputRequired(true);
        return response;
    }
}
