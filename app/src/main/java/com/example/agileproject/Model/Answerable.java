package com.example.agileproject.Model;

/**
 * Interface for all objects that are some form of answer.
 * @author Elias Johansson William Hugo
 */
public interface Answerable {


    /**
     *Returns the primary data for the object, for example Boolean. Note all are subclasses of Object so no primitive types
     * @return Object. Weird solution but might actually be needed to avoid code repetition.
     */
    Object getData();

    /**
     * Returns the id of the Question that the answer belongs to.
     * @return int.
     */
    int getQuestionId();

    /**
     *
     * @return String that represents the date when the answer was created.
     */
    String getDate();

    /**
     * Getter for the type of the answerable
     * @return an int corresponding to which type it is.
     */
    int getType();
}
