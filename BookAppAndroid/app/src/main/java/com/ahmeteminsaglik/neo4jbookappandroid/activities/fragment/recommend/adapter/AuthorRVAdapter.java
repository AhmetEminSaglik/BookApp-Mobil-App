package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter.BookRVAdapter;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;

import java.util.List;

public class AuthorRVAdapter extends RecyclerView.Adapter<AuthorRVAdapter.CardViewHolder> {
    private Context context;
    private List<Author> authorList;

    public AuthorRVAdapter(Context context, List<Author> authorList) {
        this.context = context;
        this.authorList = authorList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.author_card_design, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String name = authorList.get(position).getName();
        String lastname = authorList.get(position).getLastname();
        int totalBook = authorList.get(position).getTotalBook();
        double point = authorList.get(position).getPoint();

        holder.indexNo.setText(Integer.toString(position + 1));
        holder.fullName.setText(name + " " + lastname);
        holder.totalBook.setText(Integer.toString(totalBook));
        holder.point.setText(Double.toString(point));

    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView indexNo;
        public TextView fullName;
        public CardView cardView;
        public TextView totalBook;
        public TextView point;

        public CardViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.authorCardView);
            fullName = view.findViewById(R.id.txtVAuthorFullName);
            point = view.findViewById(R.id.txtVAuthorPointValue);
            totalBook = view.findViewById(R.id.txtVAuthorTotalBookNumber);
            indexNo = view.findViewById(R.id.authorCardIndexNo);
        }
    }


}
