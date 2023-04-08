package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.SignUpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiUserService {
    @GET("")
    Call<List<User>> getAllUsers();

    @DELETE("readbooks")
    Call<RestApiResponse<Object>> removeUserReadBookConnection(@Query("userId") long userId, @Query("bookId") long bookId);

    @POST("login")
    Call<LoginResponse> loginUser(@Body User user);

    @POST("signup")
    Call<SignUpResponse> signUpUser(@Body User user);

    @GET("followed/{userId}")
    Call<RestApiResponse<List<User>>> getFollowedList(@Path("userId") long userId);

    @GET("follower/{userId}")
    Call<RestApiResponse<List<User>>> getFollowerList(@Path("userId") long userId);

    @DELETE("/users/{userId}/followed/{followedUserId}")
    Call<RestApiResponse<List<User>>> removeFollowedUserRelationship(@Path("userId") long userId, @Path("followedUserId") long followedUserId);

    @DELETE("/users/{userId}/follower/{followerUserId}")
    Call<RestApiResponse<List<User>>> removeFollowerUserRelationship(@Path("userId") long userId, @Path("followerUserId") long followerUserId);
    /*
    This returns response.body() null
    @FormUrlEncodedw

    @POST("/users/login")
    Call<User> getUserByUsernameAndPassword(/*@Body User user@Field("username") String username,@Field("password") String password);*/
}
