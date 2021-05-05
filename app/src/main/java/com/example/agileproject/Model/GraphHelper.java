package com.example.agileproject.Model;

import com.example.agileproject.Utils.FileConverter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class that computes the datapoints for a certain interval of time.
 * @author Elias Johansson
 */
public class GraphHelper {

    /**
     *
     * @param startingDate String for starting date (formatted yyyy-MM-dd)
     * @param endDate      String for end date (formatted yyyy-MM-dd)
     * @param questionId   The id of the question
     * @return List<Answerable> The answers that have the questionId during that interval of time.
     *
     * This method returns a list that represents the answers for a given question during a period of time.
     * The list will be ordered by date.
     */
    public List<AnswerEntry> getDataFromDateToDate(String startingDate, String endDate, int questionId) {

        List<Answerable> answerableList = new ArrayList<>();
        List<Answerable> byIdList;
        List<Answerable> byDateList;
        List<AnswerEntry> answerEntries = new ArrayList<>();
        FileConverter fileConverter = FileConverter.getInstance();


        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //Used to convert strings to dates
            Date date1 = simpleDateFormat.parse(startingDate); //converting
            Date date2 = simpleDateFormat.parse(endDate);
            long difference_In_Time = date2.getTime() - date1.getTime(); //The difference in days
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;


            //For every day, check if there is an answer for that date with the given questionId
            for(int i=0;i<=difference_In_Days;i++){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                calendar.add(Calendar.DAY_OF_YEAR, i);
               byDateList= fileConverter.getAnswersByDate(simpleDateFormat.format(calendar.getTime()));
               byIdList = fileConverter.getAnswersByQuestionID(questionId);

               byDateList.retainAll(byIdList); //Basically a union of the two lists.
               answerableList.addAll(byDateList);
               byDateList.clear();
               byIdList.clear();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        int index = 0;
        for (Answerable answerable:answerableList
             ) {
            answerEntries.add(new AnswerEntry(index, (Float) answerable.getData(), questionId));
        }
return answerEntries;
    }
}
