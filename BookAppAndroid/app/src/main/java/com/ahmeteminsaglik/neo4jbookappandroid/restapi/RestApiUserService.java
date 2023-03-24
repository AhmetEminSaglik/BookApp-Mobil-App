package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiUserService {
    @GET("/users")
    Call<List<User>> getAllUsers();

    @POST("/users/login")
    Call<LoginResponse> loginUser(@Body User user/*@Field("username") String username,@Field("password") String password*/);
    /*
    This returns response.body() null
    @FormUrlEncoded

    @POST("/users/login")
    Call<User> getUserByUsernameAndPassword(/*@Body User user@Field("username") String username,@Field("password") String password);*/
}
