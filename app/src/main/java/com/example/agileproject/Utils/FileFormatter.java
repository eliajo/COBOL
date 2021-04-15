package com.example.agileproject.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.Answer;

import java.time.LocalDate;

import java.util.List;

public class FileFormatter {

    public FileFormatter(){

    }

    private String endFile(String data){
        StringBuilder sb = new StringBuilder();

        sb.append(data);
        sb.append("###---###---###");

        return sb.toString();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String format(List<Answer> answers){
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now());
        for (Answer a:answers) {
            sb.append(a.getData());
            sb.append("@@@---@@@---@@@");
        }
        return endFile(sb.toString());

    }
}