package com.example.agileproject.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.agileproject.Model.Answerable;
import com.example.agileproject.Model.Storable;

import java.util.List;

/**
 * A class that takes a list of storables and puts them all into one single String.
 * Used to format the String so that the list of storables can be saved in a text file.
 * @author William Hugo, Elias Johansson
 */
public class FileFormatter {

    public FileFormatter(){

    }

    public String format(List<Storable> storables){
        StringBuilder sb = new StringBuilder();

        for (Storable s:storables) {
            sb.append(s.getInfoToWrite());

            sb.append("@@@---@@@---@@@"); //End of question or contact
        }
        return sb.toString();
    }
}