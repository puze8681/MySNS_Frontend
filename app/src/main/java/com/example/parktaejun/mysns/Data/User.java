package com.example.parktaejun.mysns.Data;

/**
 * Created by parktaejun on 2017. 2. 16..
 */

public class User {
    private String name;

    public User(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
