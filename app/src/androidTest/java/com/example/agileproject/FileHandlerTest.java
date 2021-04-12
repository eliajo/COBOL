package com.example.agileproject;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class FileHandlerTest {


    @Test
    public void writeReadTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileHandler fileHandler = new FileHandler();

        fileHandler.write("hej", appContext, "test.txt");

        String s = fileHandler.read(appContext, "test.txt");


        File dir = appContext.getFilesDir();
        File file = new File(dir, "test.txt");
        file.delete();

        assertEquals("hej", s);


    }
}

