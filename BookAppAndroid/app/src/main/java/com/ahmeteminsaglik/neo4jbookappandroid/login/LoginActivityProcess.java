package com.ahmeteminsaglik.neo4jbookappandroid.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.concrete.LoginResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityProcess {

    /*
     * get username,password
     * click login btn
     * if data is correct than change loginActivity to MyReadBookActivity
     * */
    Context context;

    public LoginActivityProcess(Context context) {
        this.context = context;
    }

    public User login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        sendLoginRequest(user);
        return user;
    }

    private void sendLoginRequest(User user) {
        System.out.println("user : " + user.toString());
        Call<LoginResponse> call = ManagerAll.getInstance().getUserByLoginRequest(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.println("response.code()   :"+response.code());
                System.out.println("response  :"+response.toString());
                System.out.println("response.message()  :"+response.message());

                if (response.code() == 200) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                    User user = response.body().getData();
                    long id = user.getId();
                    String name = user.getName();
                    String lastname = user.getLastname();
                    String username = user.getUsername();
                    String password = user.getPassword();
                    int totalFollowers = user.getTotalFollowers();
                    Log.i("-------------------------id : ", Long.toString(id));
                    Log.i("-------------------------name : ", name);
                    Log.i("-------------------------lastname : ", lastname);
                    Log.i("-------------------------totalFollowers : ", Integer.toString(totalFollowers));
                    // do something with the user data
                } else if (response.code() == 400) {
                    Gson gson = new Gson();
                    RestApiErrorResponse errorResponse=gson.fromJson(response.errorBody().charStream(),RestApiErrorResponse.class);
                    String errMsg=errorResponse.getMessage();

                    System.out.println("response.AAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.println("response.response : "+response);
                    System.out.println("response.message : "+response.message());
                    System.out.println("response.code : "+response.code());
                    System.out.println("response.body : "+response.body());
                    System.out.println("response.headers : "+response.headers());
                    System.out.println("response.isSuccessful : "+response.isSuccessful());
                    System.out.println("response.errorBody : "+response.errorBody());
                    System.out.println("response.errorBody : "+response.errorBody());
                    System.out.println("response.raw : "+response.raw());

                    Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // handle error
                System.out.println("GELDIIIIIIIIIIIIIIIIIIIIIIIIIIIIII : "+t.getMessage());
            }
        });




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
    }
}
