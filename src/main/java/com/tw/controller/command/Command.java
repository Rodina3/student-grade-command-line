package com.tw.controller.command;

import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;

public interface Command {
    Response exec(Request request);
}
