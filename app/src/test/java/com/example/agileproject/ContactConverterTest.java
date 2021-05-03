package com.example.agileproject;

import com.example.agileproject.ControlView.Contact;
import com.example.agileproject.Model.Storable;
import com.example.agileproject.Utils.ContactConverter;
import com.example.agileproject.Utils.FileFormatter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Elias Johansson
 */
public class ContactConverterTest {

    @Test
    public void checkIfContactIsConvertedCorrectly(){

        Contact c1 = new Contact("n1","0001");
        Contact c2 = new Contact("n2","0002");
        Contact c3 = new Contact("n3","0003","email@email.com");

        List<Storable> storableList = new ArrayList<>();

        storableList.add(c1);
        storableList.add(c2);
        storableList.add(c3);

        FileFormatter ff = new FileFormatter();
        String formattedData = ff.format(storableList);

        ContactConverter.getInstance().convert(formattedData);

        String expected = c1.getInfoToWrite() + c2.getInfoToWrite() + c3.getInfoToWrite();
        StringBuilder convertedString = new StringBuilder();
        for (Contact c:ContactConverter.getInstance().getContactList()) {
            convertedString.append(c.getInfoToWrite());
        }
        assertEquals(expected, convertedString.toString());

    }
}
