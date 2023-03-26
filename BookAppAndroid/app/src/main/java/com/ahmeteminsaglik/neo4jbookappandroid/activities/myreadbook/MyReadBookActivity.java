package com.ahmeteminsaglik.neo4jbookappandroid.activities.myreadbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.login.LoginActivityProcess;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.myreadbook.adapter.BookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.StrictModePolicy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReadBookActivity extends AppCompatActivity {
    private User user = new User();
    private RecyclerView rv;
    private BookRVAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_read_book);
        StrictModePolicy.enable();
//        getRequest();
        getUserDataFromIntent();
        Log.e("User id : ", Long.toString(user.getId()));
        List<Book> bookList = getReadBookList();
        rv = findViewById(R.id.bookRecyleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookRVAdapter(this, bookList);
        rv.setAdapter(adapter);
        bookList.forEach(e -> Log.e("read book", e.toString()));
    }

    private void getUserDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        user.setId(bundle.getLong(EnumUser.ID.getName()));
        user.setName(bundle.getString(EnumUser.NAME.getName()));
        user.setLastname(bundle.getString(EnumUser.LASTNAME.getName()));
        user.setUsername(bundle.getString(EnumUser.USERNAME.getName()));
        user.setPassword(bundle.getString(EnumUser.PASSWORD.getName()));
        user.setTotalFollowers(bundle.getInt(EnumUser.TOTALFOLLOWERS.getName()));


    }

    private List<Book> getReadBookList() {
        MyReadBookActivityProcess myReadBookActivityProcess = new MyReadBookActivityProcess(getApplicationContext());
        List<Book> bookList = myReadBookActivityProcess.getReadBookList(user);
        return bookList;
    }

/*    private void getRequest() {
        Call<List<User>> callList = ManagerAll.getInstance().getAllUser();
        callList.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                System.out.println("--> AES >> " + response.body());
//                TextView textView = findViewById(R.id.txtViewMyBookPageUserData);
//                textView.setText(response.body().toString());
                Log.i("--> AES >>  ", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("--> AES >> ERROR " + t.getMessage().toString());
                Log.i("--> AES >>  ERROR ", t.getMessage());
            }
        });
    }*/
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