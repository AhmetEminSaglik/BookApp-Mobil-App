package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.MyReadBookItemFragment;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.CardViewHolder> {
    private AppCompatActivity activity;
    private List<RecommendedBook> recommendedBookList;
    //    private List<Book> bookList;
    private Fragment baseFragment;

    public BookRVAdapter(Fragment fragment, AppCompatActivity activity, List<RecommendedBook> recommendedBookList) {
        this.baseFragment = fragment;
        this.activity = activity;
        this.recommendedBookList = recommendedBookList;
    }

    public BookRVAdapter(AppCompatActivity activity, List<RecommendedBook> recommendedBookList) {
        this.baseFragment = null;
        this.activity =activity;
        this.recommendedBookList = recommendedBookList;
    }


//    public BookRVAdapter(Context context) {
//        this.context = context;
//    }

//    public List<RecommendedBook> getRecommendedBookList() {
//        return recommendedBookList;
//    }
//
//    public void setRecommendedBookList(List<RecommendedBook> recommendedBookList) {
//        this.recommendedBookList = recommendedBookList;
//    }
//
//    public List<Book> getBookList() {
//        return bookList;
//    }
//
//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(R.layout.book_card_design, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Book book = recommendedBookList.get(position).getBook();
        String whyRecommend = recommendedBookList.get(position).getWhyRecommend();
        String bookName = book.getName();
        double point = book.getPoint();
        int totalRead = book.getTotalRead();
        holder.txtVBookName.setText(bookName);
        holder.txtVBookPoint.setText(Double.toString(point));
        holder.txtVTotalRead.setText(Integer.toString(totalRead));
        holder.bookCardIndexNo.setText(Integer.toString(position + 1));
        holder.whyRecommend.setText(whyRecommend);
        holder.bookCardView.setBackgroundResource(CardUtility.getCardBackgroudColorByRecommendType(whyRecommend));
        holder.bookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity.getApplicationContext(), "tiklandi", Toast.LENGTH_LONG).show();
                Fragment fragment = new MyReadBookItemFragment();
                activateFragment(baseFragment.getId(),fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedBookList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView txtVBookName;
        public CardView bookCardView;
        public TextView txtVBookPoint;
        public TextView txtVTotalRead;
        public TextView bookCardIndexNo;
        public TextView whyRecommend;

        public CardViewHolder(@NonNull View view) {
            super(view);
            bookCardView = view.findViewById(R.id.bookCardView);
            txtVBookName = view.findViewById(R.id.txtVAuthorFullName);
            txtVBookPoint = view.findViewById(R.id.txtVAuthorPointValue);
            txtVTotalRead = view.findViewById(R.id.txtVAuthorTotalBookNumber);
            bookCardIndexNo = view.findViewById(R.id.authorCardIndexNo);
            whyRecommend = view.findViewById(R.id.txtBookWhyRecommend);
        }
    }

    private void activateFragment(int base_fragment_id, Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(base_fragment_id, fragment)
                .commit();
    }
}
