package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.BookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter.AuthorRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;

import java.util.List;

public class FragmentRecommends extends Fragment {
    private final Activity activity;
    private RecyclerView rv;
//    public BookRVAdapter bookAdapter;
    public AuthorRVAdapter authorAdapter;

    public FragmentRecommends(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecycleView(view);
        List<Author> authorList = getAuthorList();
        authorAdapter = new AuthorRVAdapter(activity, authorList);
        rv.setAdapter(authorAdapter);
    }

    private void createRecycleView(View view) {
        rv = view.findViewById(R.id.recommendRecyleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private List<Author> getAuthorList() {
        FragmentRecommendsProcess fragmentRecommendsProcess = new FragmentRecommendsProcess(activity.getApplicationContext());
        List<Author> authorList = fragmentRecommendsProcess.getAuthorList();
        return authorList;
    }
}
