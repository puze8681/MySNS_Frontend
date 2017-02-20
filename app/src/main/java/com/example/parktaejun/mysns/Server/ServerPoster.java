package com.example.parktaejun.mysns.Server;

/**
 * Created by parktaejun on 2017. 2. 19..
 */

public class ServerPoster {
    public String id;
    public String post_name;
    public String post_title;
    public String post_time;
    public String post_context;

    public ServerPoster(String id, String user_name, String post_title, String post_time, String post_context){
        this.id = id;
        this.post_name = user_name;
        this.post_title = post_title;
        this.post_time = post_time;
        this.post_context = post_context;
    }
}
