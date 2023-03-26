package com.ahmeteminsaglik.neo4jbookappandroid.activities.myreadbook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MyReadBookActivityProcess {
    private Context context;

    public MyReadBookActivityProcess(Context context) {
        this.context = context;
    }

    public List<Book> getReadBookList(User user) {
        return sendBookReadListRequest(user);
    }

    private List<Book> sendBookReadListRequest(User user) {
        Call<RestApiResponse<List<Book>>> call = ManagerAll.getInstance().getReadBookList(user.getId());

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
                    Log.e("Error Message is empty : ","no mesage");
                }
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
            }
            return readBookList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
