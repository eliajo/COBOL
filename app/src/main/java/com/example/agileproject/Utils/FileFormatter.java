package com.example.agileproject.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.Answer;

import java.time.LocalDate;

import java.util.List;

/**
 * @author William Hugo, Elias Johansson
 */
public class FileFormatter {

    public FileFormatter(){

    }

    private String endFile(String data){
        StringBuilder sb = new StringBuilder();

        sb.append(data);
        sb.append("###---###---###"); //End of date

        return sb.toString();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String format(List<Answer> answers){
        StringBuilder sb = new StringBuilder();
        for (Answer a:answers) {
            //sb.append(LocalDate.now());
            //sb.append("|"); //Makes it easy to split a question into date and answer
            sb.append(a.getData());
            sb.append("@@@---@@@---@@@"); //End of question
        }
        return sb.toString();
    }
}