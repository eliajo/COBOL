package com.example.agileproject.ControlView;

import com.example.agileproject.Model.Storable;

/**
 *  Class for defining how to construct contacts in other classes.
 *
 *  @author Elin Berthag, Alva Leufstedt, Klara Jakobsson
 */
public class Contact implements Storable {
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
    @Override
    public String getInfoToWrite(){

        if (optionalContactWay!=null){
            return name + "###---###---###" + contactWay + "###---###---###" + optionalContactWay;
        }
        else {
            return name + "###---###---###" + contactWay;
        }
    }
}
