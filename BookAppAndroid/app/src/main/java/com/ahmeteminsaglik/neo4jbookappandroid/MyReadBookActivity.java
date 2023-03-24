package com.ahmeteminsaglik.neo4jbookappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReadBookActivity extends AppCompatActivity {
    final String userListURL = "http://10.0.2.2:8080/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_read_book);
        getRequest();
    }

    private void getRequest() {
        Call<List<User>> callList = ManagerAll.getInstance().getAllUser();
        callList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println("--> AES >> "+response.body());
                Log.i("--> AES >>  ", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("--> AES >> ERROR "+t.getMessage().toString());
                Log.i("--> AES >>  ERROR ", t.getMessage());
            }
        });
    }
/*
    private void handleAPIResponse(String responseString) {
        try {
            String text = responseString;
            Toast.makeText(getApplicationContext(), "Data is retrieved", Toast.LENGTH_SHORT).show();
            TextView textId = findViewById(R.id.myBookPageUserData);
            textId.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR OCCURED : " + e.getMessage());
        }

    }

    private void handleAPIError(String error) {
        System.out.println("ERROR OCCURED : " + error);
        Toast.makeText(getApplicationContext(), "Fail to get response", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener getAllUserListBtnEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getDataJsonArray(userListURL);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Fail to get response", Toast.LENGTH_SHORT).show();
                    System.out.println("ERROR OCCURED : " + e.getMessage());
                }
            }
        };
    }

    private void getDataJsonArray(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        val request = JsonArrayRequest(Request.Method.GET, url, null,
                {response -> handleAPIResponse(response.toString())},
                {error -> handleAPIError(error.toString())})
        queue.add(request)

    }*/
}