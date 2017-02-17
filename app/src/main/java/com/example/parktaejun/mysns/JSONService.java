package com.example.parktaejun.mysns;

import com.example.parktaejun.mysns.Data.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by parktaejun on 2017. 2. 16..
 */

public interface JSONService {

    @FormUrlEncoded
    @POST("/auth/login")
    Call<User> login(@Field("user_id") String user_id, @Field("user_password") String user_pw);

    @FormUrlEncoded
    @POST("/auth/register")
    Call<User> register(@Field("user_id") String user_id, @Field("user_password") String user_pw, @Field("user_name") String user_name);
}
