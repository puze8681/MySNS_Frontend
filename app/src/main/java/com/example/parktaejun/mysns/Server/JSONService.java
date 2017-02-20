package com.example.parktaejun.mysns.Server;

import com.example.parktaejun.mysns.Data.TimeLine;
import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.Server.ServerUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by parktaejun on 2017. 2. 16..
 */

public interface JSONService {

    @FormUrlEncoded
    @POST("/login")
    Call<ServerUser> login(@Field("user_id") String user_id, @Field("user_password") String user_pw);

    @FormUrlEncoded
    @POST("/register")
    Call<ServerUser> register(@Field("user_id") String user_id, @Field("user_password") String user_pw, @Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("/savePost")
    Call<ServerPoster> post(@Field("post_name") String post_name, @Field("post_title") String post_title, @Field("post_time") String post_time, @Field("post_context") String post_context);

    @FormUrlEncoded
    @POST("/loadPost")
    Call<List<ServerPoster>> loadPost();
}
