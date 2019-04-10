package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.view.AddStudentPage;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AddStudentCmdTest {
    private AddStudentCmd addStudentCmd;
    private Request request;

    @Before
    public void setUp() {
        addStudentCmd = new AddStudentCmd();
        request = new Request();
        request.setCurrentStatus(Status.ADD_STUDENT.toString());
    }

    @Test
    public void shouldSuccessWhenInputValidStudentInfo() {
        request.setInput("valid");

        Response response = addStudentCmd.exec(request);

        assertThat(response.getStatus(), is(Status.HOME.toString()));
        assertThat(response.getPage(),is(AddStudentPage.SUCCESS_TEMPLATE));
        assertFalse(response.isInputRequired());
    }


    @Test
    public void shouldFailedWhenInputInvalidStudentInfo() {
        request.setInput("invalid student info");

        Response response = addStudentCmd.exec(request);

        assertThat(response.getStatus(), is(Status.ADD_STUDENT.toString()));
        assertThat(response.getPage(),is(AddStudentPage.ERROR_PROMPT));
        assertTrue(response.isInputRequired());
    }
}