package com.example.agileproject;

import com.example.agileproject.Model.Answer;
import com.example.agileproject.Utils.FileFormatter;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileFormatterTest {
    @Test
    public void checkStringIsFormattedCorrectly(){

        Answer a = new Answer(3,20);
        Answer b = new Answer("Test if strings work",30);
        Answer c = new Answer(false,40);

        List<Answer> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);

        FileFormatter f = new FileFormatter();
        String res = f.format(answers);

        assertEquals(LocalDate.now() + a.getData() + "@@@---@@@---@@@"
                + b.getData() + "@@@---@@@---@@@"
                + c.getData() + "@@@---@@@---@@@"
                +"###---###---###",res );
    }
}