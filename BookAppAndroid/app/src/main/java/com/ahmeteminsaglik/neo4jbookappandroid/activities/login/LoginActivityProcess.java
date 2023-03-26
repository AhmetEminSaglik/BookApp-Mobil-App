package com.ahmeteminsaglik.neo4jbookappandroid.activities.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivityProcess {

    /*
     * get username,password
     * click login btn
     * if data is correct than change loginActivity to MyReadBookActivity
     * */
    private Context context;
    private User user;

    public LoginActivityProcess(Context context) {
        this.context = context;
    }

    public User getLoginUser(String username, String password) {
        sendLoginRequest(new User(username, password));
        return user;
    }


    private User sendLoginRequest(User userForLoginRequest) { // enqueue yerine execute kullanmaliyim. arastir.
        Call<LoginResponse> call = ManagerAll.getInstance().getUserByLoginRequest(userForLoginRequest);
        try {
            Response<LoginResponse> response = call.execute();
            if (response.code() == 200) {
                user = response.body().getData();
            } else/* if (response.code() == 400) */ {
                Gson gson = new Gson();
                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                String errMsg = errorResponse.getMessage();
                Log.e("Error : ",errMsg);
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                user = null;
            }

        } catch (IOException e) {
            Log.e("-AES-> Error : ", e.getMessage());
        }
        return user;

/*        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println("response.code()   :" + response.code());
                System.out.println("response  :" + response.toString());
                System.out.println("response.message()  :" + response.message());

                if (response.code() == 200) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                    user = response.body().getData();
                } else if (response.code() == 400) {
                    Gson gson = new Gson();
                    RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                    String errMsg = errorResponse.getMessage();
                    Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    user = null;
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // handle error
                user = null;
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/

    }
}

/*
        Call<User> call = ManagerAll.getInstance().getUserByLoginRequest(user);
            call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("---------------> AES >> response  >>>   ", response.toString());
                Log.i("---------------> AES >> response  >>>   ", response.toString());
//                TextView textView = findViewById(R.id.txtViewMyBookPageUserData);
//                textView.setText(response.body().toString());
                if (response.body() != null) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                    Log.i("-------------------> AES >>  ", response.body().toString());
                } else {
                    Toast.makeText(context, "response.body() is null", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("--> AES >> ERROR " + t.getMessage());
                Log.i("--> AES >>  ERROR ", t.getMessage());
            }
        });*/
