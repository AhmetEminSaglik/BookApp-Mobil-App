package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.SignUpResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll managerAll = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return managerAll;
    }

    public Call<List<User>> getAllUser() {
        return getUserRestApiClient().getAllUsers();
    }


    public Call<RestApiResponse<Object>> removeUserReadBookConection(long userId, long bookId) {
        return getUserRestApiClient().removeUserReadBookConnection(userId, bookId);
    }

    public Call<RestApiResponse<List<User>>> getUserRelationshipFollowedListRequest(long id) {
        return getUserRestApiClient().getFollowedList(id);
    }

    public Call<RestApiResponse<List<User>>> getUserRelationshipFollowerListRequest(long id) {
        return getUserRestApiClient().getFollowerList(id);
    }

    public Call<RestApiResponse<List<User>>> removeFollowedUserRelationShipRequest(long userId, long followedUserId) {
        return getUserRestApiClient().removeFollowedUserRelationship(userId, followedUserId);
    }
    public Call<RestApiResponse<List<User>>> removeFollowerUserRelationShipRequest(long userId, long followerUserId) {
        return getUserRestApiClient().removeFollowerUserRelationship(userId, followerUserId);
    }


    public Call<LoginResponse> getUserByLoginRequest(User user) {
        return getUserRestApiClient().loginUser(user);
    }

    public Call<SignUpResponse> signUpUser(User user) {
        return getUserRestApiClient().signUpUser(user);
    }

    public Call<RestApiResponse<List<Book>>> getReadBookList(Long id) {
        return getBookRestApiClient().getReadBooks(id);
    }

    public Call<RestApiResponse<List<Book>>> getRecommendedBookListByPoint() {
        return getBookRestApiClient().getRecommendedBookListByPoint();
    }

    public Call<RestApiResponse<List<Book>>> getRecommendedBookListByTotalRead() {
        return getBookRestApiClient().getRecommendedBookListByTotalRead();
    }

    public Call<RestApiResponse<List<Book>>> getRecommendedBookListByFriendRead(Long userId) {
        return getBookRestApiClient().getRecommendedBookListByFriendRead(userId);
    }
    public Call<RestApiResponse<List<User>>> getRecommendedUserByFriendRead(Long userId) {
        return getUserRestApiClient().getRecommendedUserList(userId);
    }

    public Call<RestApiResponse<List<Author>>> getRecommendAuthorListByPoint() {
        return getAuthorRestApiClient().getRecommendedAuthorListByPoint();
    }
}
