package com.example.agileproject.Utils;

import com.example.agileproject.Model.Answer;

import java.util.HashMap;

public class FileConverter {

    private HashMap<String, Answer> dateMap;
    private HashMap<Integer, Answer> questionMap;
    private int answerIndex = 0;
    private int idIndex = 1;
    private int dateIndex = 2;
    private int typeIndex = 3;

    public FileConverter(){
        dateMap = new HashMap<>();
        questionMap = new HashMap<>();
    }

    public void convert(String data) {

        String[] questionsStrings = data.split("@@@---@@@---@@@"); //Split by question

        for (String question:questionsStrings) {
            String[] answerParameters = question.split("###---###---###");
            Answer answer;
            if(answerParameters[typeIndex].equals("0")){
                //Answer is text
                answer = new Answer(answerParameters[answerIndex],Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                dateMap.put(answer.getDate(),answer);
                questionMap.put(answer.getQuestionId(),answer);
            }
            else if(answerParameters[typeIndex].equals("1")){
                //Answer is int
                answer = new Answer(Integer.parseInt(answerParameters[answerIndex]),Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                dateMap.put(answer.getDate(),answer);
                questionMap.put(answer.getQuestionId(),answer);
            }
            else if(answerParameters[typeIndex].equals("2")){
                //Answer is boolean
                answer = new Answer(Boolean.getBoolean(answerParameters[answerIndex]),Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                dateMap.put(answer.getDate(),answer);
                questionMap.put(answer.getQuestionId(),answer);
            }
        }
    }

    public HashMap<Integer, Answer> getQuestionMap() {
        return questionMap;
    }

    public HashMap<String, Answer> getDateMap() {
        return dateMap;
    }
}
