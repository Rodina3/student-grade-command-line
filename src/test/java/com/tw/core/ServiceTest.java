package com.tw.core;

import com.tw.core.model.Report;
import com.tw.core.model.Score;
import com.tw.core.model.Student;
import com.tw.repo.StudentRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceTest {

    @InjectMocks
    private Service service;
    @Mock
    private StudentRepo studentRepo;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldAddStudent() {
        Student student = buildOneStudent("001", "Bob", 75, 95, 80, 80);

        service.addStudent(student);

        verify(studentRepo).save(student);
    }

    @Test
    public void shouldGetReport() {
        Student Bob = buildOneStudent("001", "Bob", 75, 95, 80, 80);
        Student Peter = buildOneStudent("002", "Peter", 85, 80, 70, 90);

        when(studentRepo.findByStudentNumber("001")).thenReturn(Optional.of(Bob));
        when(studentRepo.findByStudentNumber("002")).thenReturn(Optional.of(Peter));

        Report report = service.getReport(Arrays.asList("001", "002"));

        assertThat(report.getStudents().size(), is(2));
        assertTrue(report.getAverage() - 327.5 < 0.1);
        assertTrue(report.getMedian() - 327.5 < 0.1);
    }

    private Student buildOneStudent(String sNumber, String name, int mathGrade, int chineseGrade, int englishGrade, int programmingGrade) {
        Score math = new Score("数学", mathGrade);
        Score chinese = new Score("语文", chineseGrade);
        Score english = new Score("英语", englishGrade);
        Score programming = new Score("编程", programmingGrade);
        return new Student(sNumber, name, Arrays.asList(math, chinese, english, programming));
    }
}