package com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts;

public class RestApiErrorResponse extends RestApiBaseResponse{
    private  final  String httpStatus;

    public RestApiErrorResponse(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
