package com.example.agileproject.Model;

/**
 * Interface that all objects that are to be stored inherits from.
 * @author Elias Johansson
 */
public interface Storable {
    /**
     * Method to get the info needed to write and load from file
     * @return String that holds information about object
     */
    String getInfoToWrite();
}
