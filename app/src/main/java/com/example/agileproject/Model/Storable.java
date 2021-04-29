package com.example.agileproject.Model;

public interface Storable {
    /**
     * Method to get the info needed to write and load from file
     * @return String that holds information about object
     */
    String getInfoToWrite();
}
