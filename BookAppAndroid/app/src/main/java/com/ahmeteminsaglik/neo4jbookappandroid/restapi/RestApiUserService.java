package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.SignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApiUserService {
    @GET("")
    Call<List<User>> getAllUsers();

    @POST("login")
    Call<LoginResponse> loginUser(@Body User user);

    @POST("signup")
    Call<SignUpResponse> signUpUser(@Body User user);
    /*
    This returns response.body() null
    @FormUrlEncoded

    @POST("/users/login")
    Call<User> getUserByUsernameAndPassword(/*@Body User user@Field("username") String username,@Field("password") String password);*/
}
