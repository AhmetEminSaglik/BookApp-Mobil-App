package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RestApiBookService {
    @GET("readby/{userId}")
    Call<RestApiResponse<List<Book>>> getReadBooks(@Path("userId") Long id);

    @GET("recommend/point")
    Call<RestApiResponse<List<Book>>> getRecommendedBookListByPoint();

    @GET("recommend/totalread")
    Call<RestApiResponse<List<Book>>> getRecommendedBookListByTotalRead();
}
