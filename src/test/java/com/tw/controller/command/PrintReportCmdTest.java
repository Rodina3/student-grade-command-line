package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.PrintReportPage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class PrintReportCmdTest {
    private PrintReportCmd printReportCmd;
    private Request request;

    @Before
    public void setUp() {
        printReportCmd = new PrintReportCmd();
        request = new Request();
        request.setCurrentStatus(Status.PRINT_REPORT.toString());
    }

    @Test
    public void shouldSuccessWhenInputValidSNumber() {


    }

    @Test
    public void shouldFailedWhenInputInvalidSNumber() {
        request.setInput("aa");

        Response response = printReportCmd.exec(request);

        assertThat(response.getPage(), is(PrintReportPage.failed));
        assertThat(response.getStatus(), is(Status.PRINT_REPORT.toString()));
        assertTrue(response.isInputRequired());
    }

    @Test
    public void shouldIgnoreWhenInputNotExistSNumber() {

    }
}