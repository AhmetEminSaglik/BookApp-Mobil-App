package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentMyReadBookProcess {
    private Context context;

    public FragmentMyReadBookProcess(Context context) {
        this.context = context;
    }

    public List<Book> getReadBookList() {
        return sendBookReadListRequest();
    }

    public void removeUserReadBookConnection(long bookId) {
        sendRemoveUserReadBookConnectionRequest(bookId);
    }

    private List<Book> sendBookReadListRequest() {
        Call<RestApiResponse<List<Book>>> call = ManagerAll.getInstance().getReadBookList(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID)
        );

        try {
            List<Book> readBookList = null;
            Response<RestApiResponse<List<Book>>> response = call.execute();
            if (response.code() == 200) {
                readBookList = response.body().getData();
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
            return readBookList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void sendRemoveUserReadBookConnectionRequest(long bookId) {
//        Map<String, Long> requestBody = new HashMap<>();
//        requestBody.put("userId", SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID));
//        requestBody.put("bookId", bookId);
        Call<RestApiResponse<Object>> call = ManagerAll.getInstance().removeUserReadBookConection(
                SharedPreferenceUtility.getLongDataFromSharedPreference(context, EnumUser.ID),
                bookId
        );

        try {
            Response<RestApiResponse<Object>> response = call.execute();
            if (response.code() == 200) {
//                Toast.makeText(context, "response : " + response, Toast.LENGTH_LONG).show();
            } else/* if (response.code() == 400) */ {
                Log.e("Remove Connection Error : ", response.errorBody().toString());
//                Gson gson = new Gson();
//                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
//                String errMsg = errorResponse.getMessage();
//                if (errMsg != null) {
//                    Log.e("Error  ", errMsg);
//                } else {
//                    Log.e("Error Message is empty : ", "no mesage");
//                }
                Toast.makeText(context, "Remove Connection Error", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
