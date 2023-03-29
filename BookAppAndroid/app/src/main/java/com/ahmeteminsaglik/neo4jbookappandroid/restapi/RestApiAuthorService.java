package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiAuthorService {
    @GET("recommend/point")
    Call<RestApiResponse<List<Author>>> getRecommendedAuthorListByPoint();
}
