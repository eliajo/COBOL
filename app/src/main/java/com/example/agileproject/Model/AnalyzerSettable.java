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
     * Getter for an inte representing the type of the question, will either be 1 (int) or 2 (boolean)
     * @return int representing the data type
     */
    int getType();


}
