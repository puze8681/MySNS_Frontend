package com.example.parktaejun.mysns.Data;

/**
 * Created by parktaejun on 2017. 2. 19..
 */

public class TimeLine {
    private String name;
    private String title;
    private String time;
    private String context;

    public TimeLine(String name, String title, String time, String context){
        this.name = name;
        this.title = title;
        this.time = time;
        this.context = context;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }





}
