package com.example.agileproject.Utils;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.BooleanAnswer;
import com.example.agileproject.Model.NumberAnswer;
import com.example.agileproject.Model.TextAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Converts string from file into question objects stored in HashMaps.
 * There's two functions to get the answers, either by date or by question.
 * @author William Hugo, Elias Johansson
 */
public class FileConverter {

    private HashMap<String, List<Answerable>> dateMap;
    private HashMap<Integer, List<Answerable>> questionMap;
    private int answerIndex = 0;
    private int idIndex = 1;
    private int dateIndex = 2;
    private int typeIndex = 3;

    private static FileConverter fileConverter;

    private FileConverter(){
        dateMap = new HashMap<>();
        questionMap = new HashMap<>();
    }

    public static FileConverter getInstance(){
        if(fileConverter == null){
            fileConverter = new FileConverter();
        }
        return fileConverter;
    }

    public void convert(String data) {

        String[] questionsStrings = data.split("@@@---@@@---@@@"); //Split by question

        for (String question:questionsStrings) {
            String[] answerParameters = question.split("###---###---###");
            Answerable answer;
            if(answerParameters[typeIndex].equals("0")){
                //Answer is text
                answer = new TextAnswer(answerParameters[answerIndex],Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                if( dateMap.containsKey(answer.getDate()) ) { //if date already exists
                    dateMap.get(answer.getDate()).add(answer);
                }
                else { //if date is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    dateMap.put(answer.getDate(), answerList);
                }
                if( questionMap.containsKey(answer.getQuestionId()) ) { //if question already exists
                    questionMap.get(answer.getQuestionId()).add(answer);
                }
                else { //if questions is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    questionMap.put(answer.getQuestionId(), answerList);
                }
            }
            else if(answerParameters[typeIndex].equals("1")){
                //Answer is int
                answer = new NumberAnswer(Integer.parseInt(answerParameters[answerIndex]),Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                if( dateMap.containsKey(answer.getDate()) ) { //if date already exists
                    dateMap.get(answer.getDate()).add(answer);
                }
                else { //if date is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    dateMap.put(answer.getDate(), answerList);
                }
                if( questionMap.containsKey(answer.getQuestionId()) ) { //if question already exists
                    questionMap.get(answer.getQuestionId()).add(answer);
                }
                else { //if questions is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    questionMap.put(answer.getQuestionId(), answerList);
                }
            }
            else if(answerParameters[typeIndex].equals("2")){
                //Answer is boolean
                answer = new BooleanAnswer(Boolean.valueOf(answerParameters[answerIndex]),Integer.parseInt(answerParameters[idIndex]),answerParameters[dateIndex]);
                if( dateMap.containsKey(answer.getDate()) ) { //if date already exists
                    dateMap.get(answer.getDate()).add(answer);
                }
                else { //if date is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    dateMap.put(answer.getDate(), answerList);
                }
                if( questionMap.containsKey(answer.getQuestionId()) ) { //if question already exists
                    questionMap.get(answer.getQuestionId()).add(answer);
                }
                else { //if questions is new
                    List<Answerable> answerList = new ArrayList<>();
                    answerList.add(answer);
                    questionMap.put(answer.getQuestionId(), answerList);
                }
            }
        }
    }

    public List<Answerable> getAnswersByDate(String date) {
        return dateMap.get(date);
    }

    public List<Answerable> getAnswersByQuestionID(int questionID) {
        return questionMap.get(questionID);
    }
}
