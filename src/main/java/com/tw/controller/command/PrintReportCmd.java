package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.controller.exception.InputErrorException;
import com.tw.core.Service;
import com.tw.core.model.Report;
import com.tw.view.PrintReportPage;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.tw.controller.Status.HOME;

public class PrintReportCmd implements Command {
    private Service service = new Service();

    @Override
    public Response exec(Request request) {
        return printReport(request.getInput());
    }

    public Response printReport(String input) {
        Response response = new Response();

        try {
            List<String> sNumbers = parseSNumbers(input);
            Report report = service.getReport(sNumbers);

            response.setPage(printReport(report));
            response.setStatus(HOME.toString());
            response.setInputRequired(false);

        } catch (Exception ex) {
            response.setPage(PrintReportPage.failed);
            response.setStatus(Status.PRINT_REPORT.toString());
            response.setInputRequired(true);
        }
        return response;
    }

    // TODO: 2019-04-10 report template
    private String printReport(Report report) {
        return PrintReportPage.success + report;
    }


    private List<String> parseSNumbers(String sNumberInput) {
        List<String> sNumberArr = Arrays.stream(sNumberInput.split(", "))
                .filter(i -> Pattern.matches("\\d+", i))
                .collect(Collectors.toList());
        if (sNumberArr.size() == 0) {
            throw new InputErrorException("Student number input error");
        }
        return sNumberArr;

    }
}
