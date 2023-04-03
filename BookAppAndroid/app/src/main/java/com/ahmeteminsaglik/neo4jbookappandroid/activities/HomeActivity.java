package com.ahmeteminsaglik.neo4jbookappandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.FragmentRecommends;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBook;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.StrictModePolicy;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottom_navigation;
    //    private User user = new User();
    private Fragment tempFragment = new FragmentMyReadBook(this);


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StrictModePolicy.enable();
//        getUserDataFromIntent();


        bottom_navigation = findViewById(R.id.bottom_navigation);
//        Fragment fragment = findViewById(R.id.base_fragment);

        activateFragment(R.id.base_fragment, tempFragment);

        bottom_navigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_my_read_book) {
                tempFragment = new FragmentMyReadBook(tempFragment, this);
            }
            if (item.getItemId() == R.id.action_recommends) {
                tempFragment = new FragmentRecommends(this);
            }
            if (item.getItemId() == R.id.action_logout) {
                SharedPreferenceUtility.clearSharedPreference(this);
                Toast.makeText(getApplicationContext(), "Log out Successfully", Toast.LENGTH_LONG).show();
                finish();
                return false;
            }
            activateFragment(R.id.base_fragment, tempFragment);

            return true;
        });
//        getRequest();
/*        getUserDataFromIntent();
        Log.e("User id : ", Long.toString(user.getId()));
        List<Book> bookList = getReadBookList();
        rv = findViewById(R.id.bookRecyleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookRVAdapter(this, bookList);
        rv.setAdapter(adapter);
        bookList.forEach(e -> Log.e("read book", e.toString()));*/

    }

    private void activateFragment(int base_fragment_id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(base_fragment_id, fragment)
                .commit();
    }

/*

    private void getUserDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        user.setId(bundle.getLong(EnumUser.ID.getName()));
        user.setName(bundle.getString(EnumUser.NAME.getName()));
        user.setLastname(bundle.getString(EnumUser.LASTNAME.getName()));
        user.setUsername(bundle.getString(EnumUser.USERNAME.getName()));
        user.setPassword(bundle.getString(EnumUser.PASSWORD.getName()));
        user.setTotalFollowers(bundle.getInt(EnumUser.TOTALFOLLOWERS.getName()));


    }


*/

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