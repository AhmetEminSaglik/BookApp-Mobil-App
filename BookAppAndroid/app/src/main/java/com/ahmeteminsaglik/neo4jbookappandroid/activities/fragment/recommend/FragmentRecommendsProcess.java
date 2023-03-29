package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentRecommendsProcess {

    private Context context;

    public FragmentRecommendsProcess(Context context) {
        this.context = context;
    }

    public List<Author> getAuthorList() {
        return sendRecommendAuthorByPointRequest();
    }

    private List<Author> sendRecommendAuthorByPointRequest() {
        Call<RestApiResponse<List<Author>>> call = ManagerAll.getInstance().getRecommendAuthorListByPoint();

        try {
            List<Author> authorList = null;
            Response<RestApiResponse<List<Author>>> response = call.execute();
            if (response.code() == 200) {
                authorList = response.body().getData();
            } else/* if (response.code() == 400) */ {
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
            return authorList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
