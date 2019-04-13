package com.tw.controller.command;

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
import static com.tw.controller.Status.PRINT_REPORT;
import static java.lang.String.format;

public class PrintReportCmd implements Command {
    private Service service = Service.getInstance();

    @Override
    public Response exec(Request request) {
        return printReport(request.getInput());
    }

    public Response printReport(String input) {
        Response response;

        try {
            List<String> sNumbers = parseSNumbers(input);
            Report report = service.getReport(sNumbers);
            response = new Response(HOME.toString(), printReport(report), false);

        } catch (Exception ex) {
            response = new Response(PRINT_REPORT.toString(), PrintReportPage.ERROR_PROMPT, true);
        }
        return response;
    }

    private String printReport(Report report) {
        String studentInfo = report.getStudents()
                .stream()
                .map(s -> format(PrintReportPage.RECORD_TEMPLATE, s.getName(),
                        s.getScores().get(0).getGrade(), s.getScores().get(1).getGrade(),
                        s.getScores().get(2).getGrade(), s.getScores().get(3).getGrade(),
                        s.getAverageScore(), s.getTotalScore()))
                .collect(Collectors.joining());

        return format(PrintReportPage.REPORT_TEMPLATE, studentInfo, report.getAverage(), report.getMedian());
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
