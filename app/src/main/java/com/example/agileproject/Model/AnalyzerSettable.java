package com.example.agileproject.Model;

/**
 * Interface for the settings for analyzing data
 * @author William Hugo
 */
public interface AnalyzerSettable {

    /**
     * Getter for the id which should be the same as the question that will be analyzed
     * @return int
     */
    int getId();

    /**
     * Getter for an int representing the type of the question, will either be 1 (int) or 2 (boolean)
     * @return int representing the data type
     */
    int getType();

    /**
     * Getter for the time frame that needs to be checked
     * @return int
     */
    int getTimeFrame();

    /**
     * Method to get the info needed to write and load from file
     * @return String that holds information about object
     */
    String getInfoToWrite();


}
