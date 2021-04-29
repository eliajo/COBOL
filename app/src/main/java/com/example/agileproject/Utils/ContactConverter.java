package com.example.agileproject.Utils;

import com.example.agileproject.ControlView.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactConverter {

    private static ContactConverter contactConverter;
    private List<Contact> contactList;

    private ContactConverter() {
        contactList = new ArrayList();
    }

    public static ContactConverter getInstance() {
        if (contactConverter == null) {
            contactConverter = new ContactConverter();
        }
        return contactConverter;
    }

    public void convert(String data) {
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

    public List<Contact> getContactList() {
        List<Contact> safeCopy = new ArrayList<>();
        safeCopy.addAll(contactList);
        return safeCopy;
    }
}
