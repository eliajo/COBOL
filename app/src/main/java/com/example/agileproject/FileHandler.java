package com.example.agileproject;

import android.content.Context;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {

    /**
     *
     * @param data the data we want to write to the file
     * @param context the application context
     *
     *
     */
    public void write(String data, Context context, String filename){

        File pathToFile = context.getFilesDir();


        File file = new File (pathToFile,filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(file,true);
            outputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String read(Context context, String filename){

        File pathToFile = context.getFilesDir();

        File file = new File(pathToFile,filename);

        int fileSize = (int) file.length();

        byte[] data = new byte[fileSize];

        try {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String readString = new String(data);
        return readString;


    }
}
