package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.adapter.RelationshipUserRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfil extends Fragment {
    private final AppCompatActivity activity;
    private RecyclerView rv;
    public RelationshipUserRVAdapter followingUserListAdapter;
    public RelationshipUserRVAdapter followerUserListAdapter;

    public FragmentProfil(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.standart_fragment_layout, container, false); // is used to connect desing in layout
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecycleView(view);
        List<RelationshipUser> followingUserList = getfollowingRelationshipList();
        List<RelationshipUser> followerUserList = getFollowerRelationshipList();
//        List<RecommendedBook> recommendedBookList = convertBookListToRecommedBookList(bookList);
        followingUserListAdapter = new RelationshipUserRVAdapter(activity, followingUserList);//new BookRVAdapter(fragment, activity, recommendedBookList);
        followerUserListAdapter = new RelationshipUserRVAdapter(activity, followerUserList);//new BookRVAdapter(fragment, activity, recommendedBookList);
        ConcatAdapter concatAdapter = new ConcatAdapter(followingUserListAdapter, followerUserListAdapter);
        rv.setAdapter(concatAdapter);
    }

    private void createRecycleView(View view) {
        rv = view.findViewById(R.id.standardRecyleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private List<RelationshipUser> getfollowingRelationshipList() {
        FragmentProfilProcess fragmentRecommendsProcess = new FragmentProfilProcess(activity.getApplicationContext());
        return fragmentRecommendsProcess.getfollowingList();
    }

    private List<RelationshipUser> getFollowerRelationshipList() {
        FragmentProfilProcess fragmentRecommendsProcess = new FragmentProfilProcess(activity.getApplicationContext());
        return fragmentRecommendsProcess.getFollowerList();
    }
}
