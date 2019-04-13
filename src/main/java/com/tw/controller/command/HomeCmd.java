package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.MenuPage;

public class HomeCmd implements Command {
    @Override
    public Response exec(Request request) {
        return new Response(Status.MENU.toString(), MenuPage.MENU, true);
    }
}
