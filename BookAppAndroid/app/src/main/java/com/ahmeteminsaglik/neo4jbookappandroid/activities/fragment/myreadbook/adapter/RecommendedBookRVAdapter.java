package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public class RecommendedBookRVAdapter extends BookRVAdapter {
    private final List<RecommendedBook> bookList;

    public RecommendedBookRVAdapter(AppCompatActivity activity, List<RecommendedBook> bookList) {
        super(activity, R.layout.recommend_book_card_design, bookList.size());
        this.bookList = bookList;
    }
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Book book = bookList.get(position).getBook();
        String whyRecommend = bookList.get(position).getWhyRecommend();
        String bookName = book.getName();
        double point = book.getPoint();
        int totalRead = book.getTotalRead();
        holder.txtVBookName.setText(bookName);
        holder.txtVBookPoint.setText(Double.toString(point));
        holder.txtVTotalRead.setText(Integer.toString(totalRead));
        holder.bookCardIndexNo.setText(Integer.toString((bookList.size() - position)));
        holder.whyRecommend.setText(whyRecommend);
//        holder.removeBookBtn.setOnClickListener();
        holder.bookCardView.setBackgroundResource(CardUtility.getCardBackgroudColorByRecommendType(whyRecommend));
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
