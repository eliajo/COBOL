package com.example.agileproject.Utils;


import android.content.Context;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Elias Johansson
 * Class that handles saving data to a file and reading data from a file.
 */
public class FileHandler {

    /**
     * @param data     The data that is to be written, String
     * @param context  The application context. (Different ways to get access to this depending on where this method is used)
     *                 Context.
     * @param filename the desired name of the file. String
     *                 <p>
     *                 This method writes data to a desired file.
     */
    public void write(String data, Context context, String filename) {
        File pathToFile = context.getFilesDir();


        File file = new File(pathToFile, filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @param context  The application context. (Different ways to get access to this depending on where this method is used)
     *                 Context.
     * @param filename the desired name of the file. String
     * @return String with the data from the file in it.
     * <p>
     * This method reads all the data from a desired file.
     */

    public String read(Context context, String filename) {

        File pathToFile = context.getFilesDir();

        File file = new File(pathToFile, filename);

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
