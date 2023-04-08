package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.HomeActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBookProcess;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;

import java.util.List;

public class MyReadBookPageBookRVAdapter extends BookRVAdapter {
    boolean btnFunctionInProgress = false;
    private final List<Book> bookList;
    private AppCompatActivity activity;
    private FragmentMyReadBookProcess readBookProcess;

    public MyReadBookPageBookRVAdapter(AppCompatActivity activity, List<Book> bookList) {
        super(activity, R.layout.read_book_card_design, bookList.size());
        this.activity = activity;
        this.bookList = bookList;
        readBookProcess = new FragmentMyReadBookProcess(activity.getApplicationContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Book book = bookList.get(position);
        String bookName = book.getName();
        double point = book.getPoint();
        int totalRead = book.getTotalRead();
        holder.txtVBookName.setText(bookName);
        holder.txtVBookPoint.setText(Double.toString(point));
        holder.txtVTotalRead.setText(Integer.toString(totalRead));
        holder.bookCardIndexNo.setText(Integer.toString((bookList.size() - position)));
        holder.removeBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View view) {
                System.out.println("btnFunctionInProgress " + btnFunctionInProgress);
                if (!btnFunctionInProgress) {
                    btnFunctionInProgress = true;
                    readBookProcess.removeUserReadBookConnection(book.getId());
                    Toast.makeText(activity, "Book is removed from read list", Toast.LENGTH_LONG).show();
//                HomeActivityUtility.getHomeAcitivity().refleshFragment(new FragmentMyReadBook(activity));
                    Intent intent = new Intent(activity.getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.getApplicationContext().startActivity(intent);
                    activity.finish();
                } else {
                    Toast.makeText(activity, "Please wait, Currently working on book remove process.", Toast.LENGTH_LONG).show();

                }
            }
        });
/*        holder.bookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity.getApplicationContext(), "tiklandi", Toast.LENGTH_LONG).show();
                Fragment fragment = new MyReadBookItemFragment();
                activateFragment(baseFragment.getId(),fragment);
            }
        });*/
    }

}
