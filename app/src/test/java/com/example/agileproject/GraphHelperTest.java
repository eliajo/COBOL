package com.example.agileproject;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.GraphHelper;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.TextAnswer;
import com.example.agileproject.Utils.FileConverter;
import com.example.agileproject.Utils.FileFormatter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphHelperTest {
    @Test
    public void checkIfGraphHelperReturnsCorrectElements() {
        NumberAnswer a = new NumberAnswer(3,20,"2021-04-19");
        TextAnswer b = new TextAnswer("Test if strings work",30,"2021-04-18");
        BooleanAnswer c = new BooleanAnswer(false,40,"2021-04-19");
        NumberAnswer d = new NumberAnswer(8,20,"2021-04-17");
        TextAnswer e = new TextAnswer("Hello!",30,"2021-04-17");
        BooleanAnswer f = new BooleanAnswer(true,40,"2021-04-18");

        List<Answerable> answers = new ArrayList<>();

        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        answers.add(e);
        answers.add(f);

        FileFormatter ff = new FileFormatter();
        String res = ff.format(answers);
        FileConverter fc = FileConverter.getInstance();
        fc.convert(res);
        GraphHelper g = new GraphHelper();
        List<Answerable> answerableList =g.getDataFromDateToDate("2021-04-17","2021-04-19",20);
        StringBuilder result = new StringBuilder();
        for (Answerable answerable:answerableList) {
            result.append(answerable.getInfoToWrite());
        }
        String expected = d.getInfoToWrite() + a.getInfoToWrite();
        assertEquals(expected, result.toString());
        }


}
