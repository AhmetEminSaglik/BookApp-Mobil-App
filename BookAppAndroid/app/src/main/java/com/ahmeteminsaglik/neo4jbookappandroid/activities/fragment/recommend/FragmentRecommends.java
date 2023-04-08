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
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.RecommendedBookpageBookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.adapter.RelationshipUserRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter.AuthorRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRecommendReason;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedAuthor;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;

import java.util.ArrayList;
import java.util.List;

public class FragmentRecommends extends Fragment {
    private final AppCompatActivity activity;
    private RecyclerView rvRecommend;
    private BookRVAdapter bookAdapter;
    private AuthorRVAdapter authorAdapter;
    private RelationshipUserRVAdapter relationshipUserAdapter;
    private FragmentRecommendsProcess fragmentRecommendsProcess;

    public FragmentRecommends(AppCompatActivity activity) {
        this.activity = activity;
        fragmentRecommendsProcess = new FragmentRecommendsProcess(activity.getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.standart_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecycleView(view);
        List<RecommendedAuthor> authorList = getAuthorList();
        List<RecommendedBook> bookList = getBookList();
        List<RecommendedBook> bookListFromFollowings = getBookListByFriendsRead();
        List<RelationshipUser> relationshipUserList = getRelationshipUserList();
        List<RecommendedBook> allBookList = new ArrayList<>();
        allBookList.addAll(bookListFromFollowings);
        allBookList.addAll(bookList);

        bookAdapter = new RecommendedBookpageBookRVAdapter(activity, allBookList);//new BookRVAdapter(activity, allBookList);
        authorAdapter = new AuthorRVAdapter(activity, authorList);
        relationshipUserAdapter = new RelationshipUserRVAdapter(activity, relationshipUserList);
        ConcatAdapter concatAdapter = new ConcatAdapter(authorAdapter, bookAdapter, relationshipUserAdapter);
        rvRecommend.setAdapter(concatAdapter);
    }

    private void createRecycleView(View view) {
        rvRecommend = view.findViewById(R.id.standardRecyleView);
        rvRecommend.setHasFixedSize(true);
        rvRecommend.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private List<RecommendedAuthor> getAuthorList() {
        return fragmentRecommendsProcess.getAuthorList();
    }

    private List<RecommendedBook> getBookList() {
        return fragmentRecommendsProcess.getBookList();
    }

    private List<RecommendedBook> getBookListByFriendsRead() {
        return fragmentRecommendsProcess.getBookListByFriendsRead();
    }

    private List<RelationshipUser> getRelationshipUserList() {
        return fragmentRecommendsProcess.getRelationshipUserList();
    }
}
