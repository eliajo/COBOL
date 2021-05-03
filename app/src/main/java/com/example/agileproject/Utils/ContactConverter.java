package com.example.agileproject.Utils;

import com.example.agileproject.ControlView.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that converts a given String into a Contact. Stores the Contacts in a list.
 * Singleton.
 * @author Elias Johansson
 */
public class ContactConverter {


    private static ContactConverter contactConverter;
    private List<Contact> contactList;

    private ContactConverter() {
        contactList = new ArrayList();
    }

    /**
     * returns the instance of the class, creates it if it does not exist.
     * @return The instance of the class
     */
    public static ContactConverter getInstance() {
        if (contactConverter == null) {
            contactConverter = new ContactConverter();
        }
        return contactConverter;
    }

    /**
     * Converts a string into Contact objects. Throws exception if data can not be formatted.
     * @param data The string containing the data from the contact file.
     */
    public void convert(String data) {
        contactList.clear();
        if (data.length()==0){
            return;
        }
        String[] contactStrings = data.split("@@@---@@@---@@@");

        for (String contact : contactStrings) {
            String[] contactParameters = contact.split("###---###---###");

            if (contactParameters.length == 3) {
                contactList.add(new Contact(contactParameters[0], contactParameters[1], contactParameters[2]));
            } else if (contactParameters.length == 2) {
                contactList.add(new Contact(contactParameters[0], contactParameters[1]));
            } else {
                throw new IllegalArgumentException("Failed to convert into contact");
            }

        }


    }

    /**
     * Getter for the list that stores the Contacts
     * @return A copy of the list that stores the Contacts
     */
    public List<Contact> getContactList() {
        List<Contact> safeCopy = new ArrayList<>();
        safeCopy.addAll(contactList);
        return safeCopy;
    }
}
