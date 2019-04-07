package com.tw.repo;

import com.tw.model.Score;
import com.tw.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StudentRepoImplTest {

    private StudentRepo studentRepo;

    @Before
    public void setUp() {
        studentRepo = new StudentRepoImpl();
    }

    @Test
    public void shouldAddStudentAndGetStudentByStudentNumber() {
        // given
        studentRepo.save(buildOneStudent());

        // when
        Optional<Student> result = studentRepo.findByStudentNumber("001");

        // then
        assertTrue(result.isPresent());
        assertThat(result.get().getStudentNumber(), is("001"));
        assertTrue(result.get().getAverageScore() - 82.5 < 0.1);
        assertThat(result.get().getTotalScore(), is(330));
    }


    @Test
    public void shouldGetNullWhenStudentNumberNotExist() {
        // when
        Optional<Student> result = studentRepo.findByStudentNumber("002");

        // then
        assertFalse(result.isPresent());
    }

    private Student buildOneStudent() {
        Score math = new Score("数学", 75);
        Score chinese = new Score("语文", 95);
        Score english = new Score("英语", 80);
        Score programming = new Score("编程", 80);
        return new Student("001", "Bob", Arrays.asList(math, chinese, english, programming));
    }
}