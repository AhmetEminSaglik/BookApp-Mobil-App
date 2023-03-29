package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook;

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
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;

import java.util.List;

public class FragmentMyReadBook extends Fragment {
    private final Activity activity;
    private RecyclerView rv;
    public BookRVAdapter adapter;

    public FragmentMyReadBook(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_read_book_layout, container, false); // is used to connect desing in layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecycleView(view);
        List<Book> bookList = getReadBookList();
        adapter = new BookRVAdapter(activity, bookList);
        rv.setAdapter(adapter);
    }

    private void createRecycleView(View view) {
        rv = view.findViewById(R.id.bookRecyleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
    private List<Book> getReadBookList() {
        FragmentMyReadBookProcess fragmentMyReadBookProcess = new FragmentMyReadBookProcess(activity.getApplicationContext());
        List<Book> bookList = fragmentMyReadBookProcess.getReadBookList();
        return bookList;
    }

}
