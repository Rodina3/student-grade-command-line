package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.AddStudentPage;
import com.tw.view.PrintReportPage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SelectMenuCmdTest {
    private SelectMenuCmd selectMenuCmd;
    private Request request;

    @Before
    public void setUp() {
        selectMenuCmd = new SelectMenuCmd();
        request = new Request();
        request.setCurrentStatus(Status.HOME.toString());
    }

    @Test
    public void shouldGotoAddStudentWhenInput1() {
        request.setInput("1");

        Response response = selectMenuCmd.exec(request);

        assertThat(response.getPage(), is(AddStudentPage.PROMPT));
        assertThat(response.getStatus(), is(Status.ADD_STUDENT.toString()));
        assertTrue(response.isInputRequired());
    }

    @Test
    public void shouldGotoPrintReportWhenInput2() {
        request.setInput("2");

        Response response = selectMenuCmd.exec(request);

        assertThat(response.getPage(), is(PrintReportPage.PROMPT));
        assertThat(response.getStatus(), is(Status.PRINT_REPORT.toString()));
        assertTrue(response.isInputRequired());
    }

    @Test
    public void shouldExitWhenInput3() {
        request.setInput("3");

        Response response = selectMenuCmd.exec(request);

        assertThat(response.getPage(),is("Exit"));
        assertThat(response.getStatus(), is(Status.EXIT.toString()));
        assertFalse(response.isInputRequired());
    }
}