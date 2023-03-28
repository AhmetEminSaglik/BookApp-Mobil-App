package com.ahmeteminsaglik.neo4jbookappandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.BookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView rv;
    public BookRVAdapter adapter;

    public TestActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        rv = findViewById(R.id.recycleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        List<Book> bookList = getReadBookList();
        adapter = new BookRVAdapter(this, bookList);
        rv.setAdapter(adapter);
    }

    public List<Book> getReadBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1l, "Clean Code", 2.4, 10));
        bookList.add(new Book(2l, "Effective Java", 5.4, 0));
        return bookList;
    }
}