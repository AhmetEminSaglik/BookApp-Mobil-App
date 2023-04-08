package com.ahmeteminsaglik.neo4jbookappandroid.utility;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBook;

public class FragmentUtility {

    public static void updateFragment(AppCompatActivity activity, Fragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.base_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
