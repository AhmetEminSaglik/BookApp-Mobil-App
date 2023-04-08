package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRelationship;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentProfilProcess {
    static private Context context;

    public FragmentProfilProcess(Context context) {
        this.context = context;
    }

    public List<RelationshipUser> getFollowedList() {
        return sendUserFollowedRelationshipListRequest();
    }

    public List<RelationshipUser> getFollowerList() {
        return sendUserFollowerRelationshipListRequest();
    }

    public static void removeFollowedUserRelationship(long followedUserId) {
        sendRemoveUserFollowedRelationshipRequest(followedUserId);
    }

    public static void removeFollowerUserRelationship(long followerUserId) {
        sendRemoveUserFollowerRelationshipRequest(followerUserId);
    }

    private static void sendRemoveUserFollowedRelationshipRequest(long followedUserId) {
        Call<RestApiResponse<List<User>>> call = ManagerAll.getInstance().removeFollowedUserRelationShipRequest(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID),
                followedUserId
        );
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void sendRemoveUserFollowerRelationshipRequest(long followerUserId) {
        Call<RestApiResponse<List<User>>> call = ManagerAll.getInstance().removeFollowerUserRelationShipRequest(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID),
                followerUserId
        );
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<RelationshipUser> sendUserFollowedRelationshipListRequest() {
        Call<RestApiResponse<List<User>>> call = ManagerAll.getInstance().getUserRelationshipFollowedListRequest(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID)
        );
        return parseRequestOfRelationshipList(call, EnumRelationship.FOLLOWED);
    }

    private List<RelationshipUser> sendUserFollowerRelationshipListRequest() {
        Call<RestApiResponse<List<User>>> call = ManagerAll.getInstance().getUserRelationshipFollowerListRequest(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID)
        );
        return parseRequestOfRelationshipList(call, EnumRelationship.FOLLOWER);
    }

    private List<RelationshipUser> parseRequestOfRelationshipList(Call<RestApiResponse<List<User>>> call, EnumRelationship enumRelationship) {
        List<RelationshipUser> relationshipList = new ArrayList();
        List<User> userList;

        try {
            Response<RestApiResponse<List<User>>> response = call.execute();
            if (response.code() == 200) {
                userList = response.body().getData();
                userList.forEach(e -> {
                    relationshipList.add(new RelationshipUser(e.getId(), e.getName(), e.getLastname(), enumRelationship));
                });
                return relationshipList;
            } else {
                Log.e("Error occured  : ", "error body : " + response.errorBody());
                Gson gson = new Gson();
                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                String errMsg = errorResponse.getMessage();
                if (errMsg != null) {
                    Log.e("Error  ", errMsg);
                } else {
                    Log.e("Error Message is empty : ", "no mesage");
                }
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


}
