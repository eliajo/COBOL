package com.example.agileproject.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.Answer;

import java.time.LocalDate;

import java.util.List;

/**
 * A class that takes a list of answers and puts them all into one single String.
 * Used to format the String so that the list of answers can be saved in a text file.
 * @author William Hugo, Elias Johansson
 */
public class FileFormatter {

    public FileFormatter(){

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String format(List<Answer> answers){
        StringBuilder sb = new StringBuilder();
        for (Answer a:answers) {
            sb.append(a.getData());
            sb.append("@@@---@@@---@@@"); //End of question
        }
        return sb.toString();
    }
}