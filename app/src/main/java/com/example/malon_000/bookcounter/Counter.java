/*
 * CMPUT 301 LEC A1
 *
 * Version 3
 * Sept 30. 2017
 *
 */
package com.example.malon_000.bookcounter;


import java.sql.Timestamp;
import java.util.Date;


/**
 * Counter class to store the information for each counter
 * @author long6
 * @version 3
 *
 */

public class Counter {
    private int count; // the current count of the counter
    private String name; // the counter name
    private int initialValue; // the initial value or value the counter resets to
    private String comment; // the comment of the counter
    private Date time; // the date and time the coutner was last edited

    /**
     * Initializes the counter
     * @param name the string entered to be the name of the counter
     * @param initialValue the value entered for the coutner to start at, also set as the value the
     *                     counter will reset to until changed
     * @param comment The string entered to be the comment of the coutner
     * @return void
     */
    public Counter(String name, int initialValue, String comment){
        count = initialValue;
        this.comment=comment;
        this.initialValue=initialValue;
        this.name=name;
        time = new Date();
    }
    /**
     * Changes the values of the counter and updates the last used time
     * @param a the value the counter will be changed to
     * @return void
     */
    public void setValue(int a){
        count = a;
        time = new Date();
    }
    /**
     * Resets the current count of the counter to the initial nubmer
     * @return void
     */
    public void reset(){
        count=initialValue;
        time = new Date();
    }
    /**
     * returns the counter as a string
     * @return string the formatted output
     */
    public String toString(){
        time = new Date();
        return (name+"\n Comment:"+comment+"\n Count"+String.valueOf(count)+"\n Last Edited:"+time.toString());
    }
    /**
     * returns the name of the counter
     * @return string the name of the counter
     */
    public String get_name(){
        return name;
    }

    /**
     * returns the current count as an int
     * @return int
     */
    public int get_value(){
        return count;
    }

    /**
     * returns the comment as a string
     * @return string
     */
    public String get_comment(){
        return comment;
    }

    /**
     * returns the last time the counter was edited
     * @return time, a Date object to indicate the time
     */
    public Date get_time(){return time;}

    /**
     * returns the value the counter resets to when the reset button is clicked
     * @return int
     */
    public int get_origin(){return initialValue;}

    /**
     * Changes the name of the counter
     * @param name a string that will be the new name of the counter
     */
    public void set_name(String name){
        this.name=name;
        time = new Date();
    }

    /**
     * Changes the comment of the counter
     * @param comment a string that will replace the current comment
     */
    public void set_comment(String comment){
        this.comment=comment;
        time = new Date();
    }

    /**
     * changes the initial value or the value the coutner will reset to
     * @param  newOrig a int that wil lbe the new reset value
     */
    public void set_original(int newOrig){
        this.initialValue=newOrig;
        time = new Date();
    }

}
