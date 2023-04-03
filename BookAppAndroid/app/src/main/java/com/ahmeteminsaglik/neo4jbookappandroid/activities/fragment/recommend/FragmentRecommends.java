package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend;

import android.app.Activity;
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
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.BookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter.AuthorRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRecommendReason;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedAuthor;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;

import java.util.ArrayList;
import java.util.List;

public class FragmentRecommends extends Fragment {
    private final AppCompatActivity activity;
    private RecyclerView rvRecommend;
    public BookRVAdapter bookAdapter;
    public AuthorRVAdapter authorAdapter;

    public FragmentRecommends(AppCompatActivity activity) {
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
        List<RecommendedAuthor> authorList = getAuthorList();
        List<RecommendedBook> bookList = getBookList();
        List<RecommendedBook> bookListFromFollowings = getBookListByFriendsRead();

        List<RecommendedBook> allBookList = new ArrayList<>();
        allBookList.addAll(bookList);
        allBookList.addAll(bookListFromFollowings);

        bookAdapter = new BookRVAdapter(activity, allBookList);
        authorAdapter = new AuthorRVAdapter(activity, authorList);
        ConcatAdapter concatAdapter = new ConcatAdapter(authorAdapter, bookAdapter);
        rvRecommend.setAdapter(concatAdapter);
    }

    private void createRecycleView(View view) {
        rvRecommend = view.findViewById(R.id.recommendRecyleView);
        rvRecommend.setHasFixedSize(true);
        rvRecommend.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private List<RecommendedAuthor> getAuthorList() {
        FragmentRecommendsProcess fragmentRecommendsProcess = new FragmentRecommendsProcess(activity.getApplicationContext());
        List<RecommendedAuthor> authorList = fragmentRecommendsProcess.getAuthorList();
        return authorList;
    }

    private List<RecommendedBook> getBookList() {
        FragmentRecommendsProcess fragmentRecommendsProcess = new FragmentRecommendsProcess(activity.getApplicationContext());
        List<RecommendedBook> bookList = fragmentRecommendsProcess.getBookList();
        return bookList;
    }

    //    Simdi burdan request aticam --> http://localhost:8080/books/recommend/friend/userId
//    sonrasinda veri otomatik islenecektir.
    private List<RecommendedBook> getBookListByFriendsRead() {
        FragmentRecommendsProcess fragmentRecommendsProcess = new FragmentRecommendsProcess(activity.getApplicationContext());
        List<RecommendedBook> bookList = fragmentRecommendsProcess.getBookListByFriendsRead();
        return bookList;
    }
}
