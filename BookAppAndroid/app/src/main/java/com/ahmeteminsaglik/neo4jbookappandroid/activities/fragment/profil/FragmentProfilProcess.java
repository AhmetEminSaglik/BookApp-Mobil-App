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
    private Context context;

    public FragmentProfilProcess(Context context) {
        this.context = context;
    }

    public List<RelationshipUser> getFollowedList() {

//        List list = new ArrayList<RelationshipUser>();
//        list.add(new RelationshipUser("Demo asd asd saasd a adsda sads ads a sd ", "Fake adsdas das ds adas ads asasdsadas dad ds d", EnumRelationship.FOLLOWED));
        return sendUserFollowedRelationshipListRequest();
    }

    private List<RelationshipUser> sendUserFollowedRelationshipListRequest() {
        List<RelationshipUser> relationshipList = new ArrayList();

        Call<RestApiResponse<List<User>>> call = ManagerAll.getInstance().getUserRelationshipFollowRequest(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID)
        );

        try {
            List<User> userList;
            Response<RestApiResponse<List<User>>> response = call.execute();
            if (response.code() == 200) {
                Log.e("200 ok : ","response : "+response.body());
                userList = response.body().getData();

                userList.forEach(e -> {
                    relationshipList.add(new RelationshipUser(e.getName(), e.getLastname(), EnumRelationship.FOLLOWED));
                });
            } else/* if (response.code() == 400) */ {
                Log.e("Error occured  : ","error body : "+response.errorBody());
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
            return relationshipList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
