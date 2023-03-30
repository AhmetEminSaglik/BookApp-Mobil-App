package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedAuthor;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public class AuthorRVAdapter extends RecyclerView.Adapter<AuthorRVAdapter.CardViewHolder> {
    private Context context;
    private List<RecommendedAuthor> authorRecommendList;

    public AuthorRVAdapter(Context context, List<RecommendedAuthor> authorRecommendList) {
        this.context = context;
        this.authorRecommendList = authorRecommendList;
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
        Author author = authorRecommendList.get(position).getAuthor();
        String whyRecommend = authorRecommendList.get(position).getWhyRecommend();

        String name = author.getName();
        String lastname = author.getLastname();
        int totalBook = author.getTotalBook();
        double point = author.getPoint();

        holder.indexNo.setText(Integer.toString(position + 1));
        holder.fullName.setText(name + " " + lastname);
        holder.totalBook.setText(Integer.toString(totalBook));
        holder.point.setText(Double.toString(point));
        holder.whyRecommend.setText(whyRecommend);
        holder.cardView.setBackgroundResource(CardUtility.getCardBackgroudColorByRecommendType(whyRecommend));
    }

    @Override
    public int getItemCount() {
        return authorRecommendList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView indexNo;
        public TextView fullName;
        public CardView cardView;
        public TextView totalBook;
        public TextView point;
        public TextView whyRecommend;

        public CardViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.authorCardView);
            fullName = view.findViewById(R.id.txtVAuthorFullName);
            point = view.findViewById(R.id.txtVAuthorPointValue);
            totalBook = view.findViewById(R.id.txtVAuthorTotalBookNumber);
            indexNo = view.findViewById(R.id.authorCardIndexNo);
            whyRecommend = view.findViewById(R.id.txtAuthorWhyRecommend);
        }
    }


}
