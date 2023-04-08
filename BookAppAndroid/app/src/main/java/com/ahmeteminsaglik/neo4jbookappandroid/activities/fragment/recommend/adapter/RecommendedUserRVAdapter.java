package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public class RecommendedUserRVAdapter extends RecyclerView.Adapter<RecommendedUserRVAdapter.CardViewHolder> {
    private Context context;
    private List<RecommendedUser> userRecommendedList;

    public RecommendedUserRVAdapter(Context context, List<RecommendedUser> userRecommendedList) {
        this.context = context;
        this.userRecommendedList = userRecommendedList;
    }

    @NonNull
    @Override
    public RecommendedUserRVAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.recommended_user_card_design, parent, false);
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        User user = userRecommendedList.get(position).getUser();
        String whyRecommend = userRecommendedList.get(position).getWhyRecommend();
        String name = user.getName();
        String lastname = user.getLastname();

        holder.indexNo.setText(Integer.toString(userRecommendedList.size() - position));
        holder.name.setText(name);
        holder.lastname.setText(lastname);
        holder.whyRecommend.setText(whyRecommend);
        holder.cardView.setBackgroundResource(CardUtility.getCardBackgroudColorByRecommendType(whyRecommend));
    }


    @Override
    public int getItemCount() {
        return userRecommendedList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView indexNo;
        public TextView name;
        public TextView lastname;
        public CardView cardView;
        public TextView whyRecommend;

        public CardViewHolder(@NonNull View view) {
            super(view);
            indexNo = view.findViewById(R.id.recUserIndexNo);
            name = view.findViewById(R.id.recUserName);
            lastname = view.findViewById(R.id.recUserLastname);
            cardView = view.findViewById(R.id.recUserCardView);
            whyRecommend = view.findViewById(R.id.recUserCardRecommendType);
        }
    }
}
