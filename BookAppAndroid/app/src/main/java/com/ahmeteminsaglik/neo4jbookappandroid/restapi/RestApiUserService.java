package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserWebService {
    @GET("/users")
    Call<List<User>> getAllUsers();

    @FormUrlEncoded
    @POST("/users/login")
    Call<User> getUserByUsernameAndPassword(@Field("username") String username,@Field("password") String password);
}
