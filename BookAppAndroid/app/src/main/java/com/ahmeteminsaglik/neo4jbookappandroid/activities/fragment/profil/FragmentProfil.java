package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.adapter.RelationshipUserRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;

import java.util.List;

public class FragmentProfil extends Fragment {
    private final AppCompatActivity activity;
    private RecyclerView rv;
    public RelationshipUserRVAdapter adapter;
    public FragmentProfil(AppCompatActivity activity) {
        this.activity = activity;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_relationship_layout, container, false); // is used to connect desing in layout
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecycleView(view);
        List<RelationshipUser> relationshipList = getRelationshipUser();
//        List<RecommendedBook> recommendedBookList = convertBookListToRecommedBookList(bookList);
        adapter = new RelationshipUserRVAdapter(activity, relationshipList);//new BookRVAdapter(fragment, activity, recommendedBookList);
        rv.setAdapter(adapter);
    }
    private void createRecycleView(View view) {
        rv = view.findViewById(R.id.relationshipCardView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private List<RelationshipUser> getRelationshipUser() {
        FragmentProfilProcess fragmentRecommendsProcess = new FragmentProfilProcess(activity.getApplicationContext());
        List<RelationshipUser> relationshipList = fragmentRecommendsProcess.getFollowedList();
        return relationshipList;
    }
}
