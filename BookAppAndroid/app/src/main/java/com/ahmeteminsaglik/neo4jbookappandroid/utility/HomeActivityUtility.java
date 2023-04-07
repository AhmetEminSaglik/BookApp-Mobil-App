package com.ahmeteminsaglik.neo4jbookappandroid.utility;

import com.ahmeteminsaglik.neo4jbookappandroid.activities.HomeActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBook;

public class HomeActivityUtility {
    private final static HomeActivity homeActivity = new HomeActivity();

    public static HomeActivity getHomeAcitivity() {
        return homeActivity;
    }

    public static FragmentMyReadBook getFragmentMyReadBook() {
        return new FragmentMyReadBook(homeActivity);
    }
}
