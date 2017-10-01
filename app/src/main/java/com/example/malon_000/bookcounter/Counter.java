package com.example.malon_000.bookcounter;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by malon_000 on 2017-09-24.
 */

public class Counter {
    private int count;
    private String name;
    private int initialValue;
    private String comment;
    private Date time;

    public Counter(String name, int initialValue, String comment){
        count = initialValue;
        this.comment=comment;
        this.initialValue=initialValue;
        this.name=name;
        time = new Date();
    }

    public void setValue(int a){
        count = a;
        time = new Date();
    }

    public void reset(){
        count=initialValue;
        time = new Date();
    }

    public String toString(){
        time = new Date();
        return (name+"\n Comment:"+comment+"\n Count"+String.valueOf(count)+"\n Last Edited:"+time.toString());
    }
    public String get_name(){
        return name;
    }
    public int get_value(){
        return count;
    }
    public String get_comment(){
        return comment;
    }
    public Date get_time(){return time;}
    public int get_origin(){return initialValue;}
    public void set_name(String name){
        this.name=name;
        time = new Date();
    }
    public void set_comment(String comment){
        this.comment=comment;
        time = new Date();
    }
    public void set_original(int newOrig){
        this.initialValue=newOrig;
        time = new Date();
    }

}
