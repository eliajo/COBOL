package com.example.agileproject;

import com.example.agileproject.Model.Answer;
import com.example.agileproject.Utils.FileConverter;
import com.example.agileproject.Utils.FileFormatter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author William Hugo, Elias Johansson
 */
public class FileConverterTest {

    @Test
    public void checkIfStoredAnswersAreConvertedCorrectlyByDate() { //Also tests if boolean and string works
        Answer a = new Answer(3,20,"2021-04-19");
        Answer b = new Answer("Test if strings work",30,"2021-04-18");
        Answer c = new Answer(false,40,"2021-04-19");
        Answer d = new Answer(8,20,"2021-04-17");
        Answer e = new Answer("Hello!",30,"2021-04-17");
        Answer f = new Answer(true,40,"2021-04-18");

        List<Answer> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        answers.add(e);
        answers.add(f);

        FileFormatter ff = new FileFormatter();
        String res = ff.format(answers);
        FileConverter fc = new FileConverter();
        fc.convert(res);

        String expected = b.getData() + f.getData();
        StringBuilder result = new StringBuilder();
        for (Answer ans: fc.getAnswersByDate("2021-04-18")) {
            result.append(ans.getData());
        }

        assertEquals(expected, result.toString());

    }

    @Test
    public void checkIfStoredAnswersAreConvertedCorrectlyByQuestionID() { //Also tests if int works
        Answer a = new Answer(3,20,"2021-04-19");
        Answer b = new Answer("Test if strings work",30,"2021-04-18");
        Answer c = new Answer(false,40,"2021-04-19");
        Answer d = new Answer(8,20,"2021-04-17");
        Answer e = new Answer("Hello!",30,"2021-04-17");
        Answer f = new Answer(true,40,"2021-04-18");

        List<Answer> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        answers.add(e);
        answers.add(f);

        FileFormatter ff = new FileFormatter();
        String res = ff.format(answers);
        FileConverter fc = new FileConverter();
        fc.convert(res);

        String expected = a.getData() + d.getData();
        StringBuilder result = new StringBuilder();
        for (Answer ans: fc.getAnswersByQuestionID(20)) {
            result.append(ans.getData());
        }

        assertEquals(expected, result.toString());

    }

}
