package com.ahmeteminsaglik.neo4jbookappandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.FragmentProfil;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.FragmentRecommends;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBook;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.StrictModePolicy;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottom_navigation;

    //    private User user = new User();


    private Fragment tempFragment = new FragmentMyReadBook(this);
    static int baseFragment = R.id.base_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StrictModePolicy.enable();
//        getUserDataFromIntent();


        bottom_navigation = findViewById(R.id.bottom_navigation);
//        Fragment fragment = findViewById(R.id.base_fragment);

        activateFragment(tempFragment);

        bottom_navigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_my_read_book) {
                tempFragment = new FragmentMyReadBook(this);
            }
            if (item.getItemId() == R.id.action_recommends) {
                tempFragment = new FragmentRecommends(this);
            }
            if (item.getItemId() == R.id.action_profil) {
                tempFragment = new FragmentProfil(this);
            }
            if (item.getItemId() == R.id.action_logout) {
                SharedPreferenceUtility.clearSharedPreference(this);
                Toast.makeText(getApplicationContext(), "Log out Successfully", Toast.LENGTH_LONG).show();
                finish();
                return false;
            }
            activateFragment(tempFragment);

            return true;
        });
    }

    public void activateFragment(Fragment fragment) {
        System.out.println("Gelen fragment : "+fragment.toString());
        System.out.println("baseFragment : "+baseFragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(baseFragment, fragment)
                .commit();
    }

//    public void refleshFragment(Fragment fragment) {
//        activateFragment(fragment);

//        if (clazz.getSimpleName().equals(FragmentMyReadBook.class.getSimpleName())) {
//            activateFragment(new FragmentMyReadBook(this));
//        }
//    }

}