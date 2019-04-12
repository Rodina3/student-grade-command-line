package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.core.Service;
import com.tw.core.model.Report;
import com.tw.core.model.Score;
import com.tw.core.model.Student;
import com.tw.view.PrintReportPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PrintReportCmdTest {
    @InjectMocks
    private PrintReportCmd printReportCmd;
    @Mock
    private Service service;

    private Request request;

    @Before
    public void setUp() {
        initMocks(this);
        request = new Request();
        request.setCurrentStatus(Status.PRINT_REPORT.toString());
    }

    @Test
    public void shouldSuccessWhenInputValidSNumber() {
        Report report = new Report(singletonList(buildOneStudent()));
        when(service.getReport(singletonList("001"))).thenReturn(report);
        request.setInput("001");

        Response response = printReportCmd.exec(request);

        String expectedPage = "\n成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n"
                + "========================\n"
                + "Bob|75|95|80|80|82.50|330\n"
                + "========================\n"
                + "全班总分平均分: 330.00\n"
                + "全班总分中位数: 330.00\n";
        assertThat(response.getPage(), is(expectedPage));
        assertThat(response.getStatus(), is(Status.HOME.toString()));
        assertFalse(response.isInputRequired());
    }

    @Test
    public void shouldFailedWhenInputInvalidSNumber() {
        request.setInput("aa");

        Response response = printReportCmd.exec(request);

        assertThat(response.getPage(), is(PrintReportPage.ERROR_PROMPT));
        assertThat(response.getStatus(), is(Status.PRINT_REPORT.toString()));
        assertTrue(response.isInputRequired());
    }

    // TODO: 2019-04-11 fix it
//    @Test
//    public void shouldIgnoreWhenInputNotExistSNumber() {
//        Report report = new Report(singletonList(buildOneStudent()));
//        when(service.getReport(singletonList("001"))).thenReturn(report);
//        request.setInput("999");
//
//        Response response = printReportCmd.exec(request);
//
//        String expectedPage = "\n成绩单\n"
//                + "姓名|数学|语文|英语|编程|平均分|总分\n"
//                + "========================\n"
//                + "Bob|75|95|80|80|82.50|330\n"
//                + "========================\n"
//                + "全班总分平均分: 330.00\n"
//                + "全班总分中位数: 330.00\n";
//        assertThat(response.getPage(), is(expectedPage));
//        assertThat(response.getStatus(), is(Status.HOME.toString()));
//        assertFalse(response.isInputRequired());
//    }
//
    private Student buildOneStudent() {
        Score math = new Score("数学", 75);
        Score chinese = new Score("语文", 95);
        Score english = new Score("英语", 80);
        Score programming = new Score("编程", 80);
        return new Student("001", "Bob", Arrays.asList(math, chinese, english, programming));
    }

}