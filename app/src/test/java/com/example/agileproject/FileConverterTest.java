package com.example.agileproject;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.Storable;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.Utils.AnswerConverter;
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
        NumberAnswer a = new NumberAnswer(3,20,"2021-04-19");
        TextAnswer b = new TextAnswer("Test if strings work",30,"2021-04-18");
        BooleanAnswer c = new BooleanAnswer(false,40,"2021-04-19");
        NumberAnswer d = new NumberAnswer(8,20,"2021-04-17");
        TextAnswer e = new TextAnswer("Hello!",30,"2021-04-17");
        BooleanAnswer f = new BooleanAnswer(true,40,"2021-04-18");

        List<Storable> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        answers.add(e);
        answers.add(f);

        FileFormatter ff = new FileFormatter();
        String res = ff.format(answers);
        AnswerConverter fc = AnswerConverter.getInstance();
        fc.convert(res);

        String expected = b.getInfoToWrite() + f.getInfoToWrite();
        StringBuilder result = new StringBuilder();
        for (Answerable ans: fc.getAnswersByDate("2021-04-18")) {
            result.append(ans.getData()+"###---###---###"+ans.getQuestionId() + "###---###---###" + ans.getDate() + "###---###---###" + ans.getType());
        }

        assertEquals(expected, result.toString());

    }

    @Test
    public void checkIfStoredAnswersAreConvertedCorrectlyByQuestionID() { //Also tests if int works
        NumberAnswer a = new NumberAnswer(3,20,"2021-04-19");
        TextAnswer b = new TextAnswer("Test if strings work",30,"2021-04-18");
        BooleanAnswer c = new BooleanAnswer(false,40,"2021-04-19");
        NumberAnswer d = new NumberAnswer(8,20,"2021-04-17");
        TextAnswer e = new TextAnswer("Hello!",30,"2021-04-17");
        BooleanAnswer f = new BooleanAnswer(true,40,"2021-04-18");

        List<Storable> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        answers.add(e);
        answers.add(f);

        FileFormatter ff = new FileFormatter();
        String res = ff.format(answers);
        AnswerConverter fc = AnswerConverter.getInstance();
        fc.convert(res);

        String expected = a.getInfoToWrite() + d.getInfoToWrite();
        StringBuilder result = new StringBuilder();
        for (Answerable ans: fc.getAnswersByQuestionID(20)) {
            result.append(ans.getData()+"###---###---###"+ans.getQuestionId() + "###---###---###" + ans.getDate() + "###---###---###" + ans.getType());
        }

        assertEquals(expected, result.toString());

    }

}
