package com.example.agileproject.ControlView;

/**
 *  Class for defining how to construct contacts in other classes.
 *
 *  @author Elin Berthag, Alva Leufstedt, Klara Jakobsson
 */
public class Contact {
    //A contact consist of a name and one or two ways of contact (phone number and/or email).
    String name;
    String contactWay;
    String optionalContactWay;

    //Constructor for new contacts with one way of contacting.
    public Contact(String name, String contactWay){
        this.name = name;
        this.contactWay = contactWay;
    }
    //Constructor for new contacts with two ways of contacting.
    public Contact(String name, String contactWay, String optionalContactWay){
        this.name = name;
        this.contactWay = contactWay;
        this.optionalContactWay = optionalContactWay;
    }
}
