package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public abstract class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.CardViewHolder> {
    protected AppCompatActivity activity;
    protected int cardDesignId;
    protected int listSize;
//    private List<RecommendedBook> recommendedBookList;
    //    private List<Book> bookList;

    public BookRVAdapter(AppCompatActivity activity, int cardDesignId, int listSize) {
        this.activity = activity;
        this.cardDesignId = cardDesignId;
        this.listSize = listSize;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(cardDesignId, parent, false);

        return new CardViewHolder(itemView);
    }



    @Override
    public int getItemCount() {
        return listSize;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView txtVBookName;
        public CardView bookCardView;
        public TextView txtVBookPoint;
        public TextView txtVTotalRead;
        public TextView bookCardIndexNo;
        public TextView whyRecommend;
        public Button removeBookBtn;

        public CardViewHolder(@NonNull View view) {
            super(view);
            bookCardView = view.findViewById(R.id.bookCardView);
            txtVBookName = view.findViewById(R.id.txtVAuthorFullName);
            txtVBookPoint = view.findViewById(R.id.txtVAuthorPointValue);
            txtVTotalRead = view.findViewById(R.id.txtVAuthorTotalBookNumber);
            bookCardIndexNo = view.findViewById(R.id.authorCardIndexNo);
            whyRecommend = view.findViewById(R.id.txtBookWhyRecommend);
            removeBookBtn = view.findViewById(R.id.removeReadBookBtn);
        }
    }

}
