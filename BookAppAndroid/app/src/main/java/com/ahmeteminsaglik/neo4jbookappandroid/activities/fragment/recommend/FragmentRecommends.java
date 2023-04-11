package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend;

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
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.RecommendedBookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter.AuthorRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter.RecommendedUserRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedAuthor;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedUser;

import java.util.ArrayList;
import java.util.List;

public class FragmentRecommends extends Fragment {
    private final AppCompatActivity activity;
    private RecyclerView rvRecommend;
    private BookRVAdapter bookAdapter;
    private AuthorRVAdapter authorAdapter;
    private RecommendedUserRVAdapter recommendedUserRVAdapter;
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
        List<RecommendedUser> recommendedUserList = getRecommendedUserList();
        List<RecommendedBook> allBookList = new ArrayList<>();
        allBookList.addAll(bookListFromFollowings);
        allBookList.addAll(bookList);

        bookAdapter = new RecommendedBookRVAdapter(activity, allBookList);//new BookRVAdapter(activity, allBookList);
        authorAdapter = new AuthorRVAdapter(activity, authorList);
        recommendedUserRVAdapter = new RecommendedUserRVAdapter(activity, recommendedUserList);
        ConcatAdapter concatAdapter = new ConcatAdapter(recommendedUserRVAdapter, bookAdapter, authorAdapter);
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

    private List<RecommendedUser> getRecommendedUserList() {
        return fragmentRecommendsProcess.getRelationshipUserList();
    }
}
