package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

public class BaseManager {
    protected RestApi getRestApiClient() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.USER_URL);
        return restApiClient.getRestAPI();
    }
}
