package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

public class BaseManager {
    protected RestApiUserService getUserRestApiClient() {
        return RestApiClient.getUserRestApi(BaseUrl.USER_URL);
    }
    protected RestApiBookService getBookRestApiClient() {
        return RestApiClient.getBookRestApi(BaseUrl.BOOK_URL);

    }
}
