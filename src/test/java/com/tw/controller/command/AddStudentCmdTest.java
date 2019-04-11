package com.tw.controller.command;

import com.tw.controller.Status;
import com.tw.controller.dto.Request;
import com.tw.controller.dto.Response;
import com.tw.core.Service;
import com.tw.view.AddStudentPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddStudentCmdTest {
    @InjectMocks
    private AddStudentCmd addStudentCmd;
    @Mock
    private Service service;

    private Request request;

    @Before
    public void setUp() {
        initMocks(this);
        request = new Request();
        request.setCurrentStatus(Status.ADD_STUDENT.toString());
    }

    @Test
    public void shouldSuccessWhenInputValidStudentInfo() {
        request.setInput("Linda, 003, 数学: 89, 英语: 90, 语文: 100, 编程: 80");

        Response response = addStudentCmd.exec(request);

        verify(service).addStudent(argThat(argument -> argument.getStudentNumber().equals("003") &&
                argument.getName().equals("Linda") && argument.getScores().size() == 4));
        assertThat(response.getStatus(), is(Status.HOME.toString()));
        assertThat(response.getPage(), is("学生Linda的成绩被添加\n"));
        assertFalse(response.isInputRequired());
    }


    @Test
    public void shouldFailedWhenInputInvalidStudentInfo() {
        request.setInput("Linda, 003, 数学");

        Response response = addStudentCmd.exec(request);

        assertThat(response.getStatus(), is(Status.ADD_STUDENT.toString()));
        assertThat(response.getPage(), is(AddStudentPage.ERROR_PROMPT));
        assertTrue(response.isInputRequired());
    }
}