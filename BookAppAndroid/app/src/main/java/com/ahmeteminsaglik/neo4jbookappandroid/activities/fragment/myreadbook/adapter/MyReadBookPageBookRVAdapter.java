package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBook;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.FragmentMyReadBookProcess;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.FragmentProfil;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.FragmentUtility;

import java.util.List;

public class MyReadBookPageBookRVAdapter extends BookRVAdapter {
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
        holder.removeBookBtn.setOnClickListener(view -> {


            readBookProcess.removeUserReadBookConnection(book.getId());
            Toast.makeText(activity, "Book is removed from read list", Toast.LENGTH_LONG).show();
//                HomeActivityUtility.getHomeAcitivity().refleshFragment(new FragmentMyReadBook(activity));
//                updateFragment();
            FragmentUtility.updateFragment(activity, new FragmentMyReadBook(activity));
        });
/*        holder.bookCardView.setOnClickListener(view -> {
            Toast.makeText(activity.getApplicationContext(), "tiklandi", Toast.LENGTH_LONG).show();
            FragmentUtility.updateFragment(activity,new FragmentProfil(activity));
        });*/
    }

}
